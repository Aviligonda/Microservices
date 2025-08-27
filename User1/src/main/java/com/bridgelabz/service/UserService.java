package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.model.UserModel;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.util.AdminResponse;
import com.bridgelabz.util.Response;
import com.bridgelabz.util.TokenUtil;

@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository repository;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Response createUser(UserDTO user, String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {

			// TODO Auto-generated method stub
			UserModel userModel = new UserModel(user);
			userModel.setCreatdTime(LocalDateTime.now());
			userModel.setAdminEmpId(isAdmin.getObject().getEmpId());
			userModel.setVerifyUser(false);
			int OTP = (int) (Math.random() * (999999 - 100000) + 100000);
			userModel.setOTP(OTP);
			userModel.setPassword(passwordEncoder.encode(user.getPassword()));
			repository.save(userModel);
			String subject = "Registrarions Done ";
			String body = "Successfully registerd details is \n" + userModel;
			MailService.send(userModel.getEmail(), body, subject);
			String subject1 = "One time Password";
			String body1 = "Do not share any one use this otp to verify your profile OTP= " + OTP;
			MailService.send(userModel.getEmail(), body1, subject1);
			return new Response(200, "Success", userModel);
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response getUser(Long id, String adminToken) {
		// TODO Auto-generated method stub
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			Optional<UserModel> isUser = repository.findById(id);
			if (isUser.isPresent()) {
				return new Response(200, "Success", isUser.get());
			}
			throw new UserException(400, "UserId is not Found");
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response getAll(String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			java.util.List<UserModel> isUser = repository.findAll();
			if (isUser.size() > 0) {
				return new Response(200, "Success", isUser);
			}
			throw new UserException(400, "Users is not Found");
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response update(Long id, UserDTO userDTO, String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			// TODO Auto-generated method stub
			Optional<UserModel> isUser = repository.findById(id);
			if (isUser.isPresent()) {
				isUser.get().setName(userDTO.getName());
				isUser.get().setGender(userDTO.getGender());
				isUser.get().setAge(userDTO.getAge());
				isUser.get().setEmail(userDTO.getEmail());
				isUser.get().setPassword(userDTO.getPassword());
				isUser.get().setUpdatedTime(LocalDateTime.now());
				repository.save(isUser.get());
				String subject = "Your Details Edited Succesully";
				String body = "Your Updated Details is " + isUser.get();
				MailService.send(isUser.get().getEmail(), body, subject);
				return new Response(200, "Success", isUser.get());
			}
			throw new UserException(400, "UserId is not Found");
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response delete(Long id, String adminToken) {
		// TODO Auto-generated method stub
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			Optional<UserModel> isUser = repository.findById(id);
			if (isUser.isPresent()) {
				repository.delete(isUser.get());
				String subject = "User Deleted";
				String body = "User Details Deleted";
				MailService.send(isUser.get().getEmail(), body, subject);
				return new Response(200, "Success", isUser.get());
			}
			throw new UserException(400, "UserId is not Found");
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response login(String email, String password) {
		// TODO Auto-generated method stub

		Optional<UserModel> isUser = repository.findByEmail(email);
		if (isUser.isPresent()) {
			if(passwordEncoder.matches(password, isUser.get().getPassword())) {
				String token = tokenUtil.createToken(isUser.get().getId());
				return new Response(200, "Success", token);
			}
			throw new UserException(400, "password is  wrong");

		}
		throw new UserException(400, "UserId is not Found");
	}

	@Override
	public Response verify(String userToken) {
		// TODO Auto-generated method stub
		Long userId = tokenUtil.decodeToken(userToken);
		Optional<UserModel> isUser = repository.findById(userId);
		if (isUser.isPresent()) {
			return new Response(200, "Succes", isUser.get());
		}
		throw new UserException(400, "UserId is not Found");
	}

//	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Response search(String name) {
		List<UserModel> isUser = repository.findByNameContainsIgnoreCase(name);
		if (isUser.size() > 0) {
			return new Response(200, isUser.size() + " results found matching the UserName ", isUser);
		}
		throw new UserException(400, "not found with word ");
	}

	@Override
	public Response verificationUser(String userToken, Long otp) {
		// TODO Auto-generated method stub
		Long userId = tokenUtil.decodeToken(userToken);
		Optional<UserModel> isUser = repository.findById(userId);
		if (isUser.isPresent()) {
			if (isUser.get().getOTP() == otp) {
				isUser.get().setVerifyUser(true);
				repository.save(isUser.get());
				return new Response(200, "Succes", isUser.get());
			}
			throw new UserException(400, "otp  not match ");
		}
		throw new UserException(400, "UserId is not Found");
	}

	@Override
	public Response reSendOtp(String userToken) {
		// TODO Auto-generated method stub
		int OTP = (int) (Math.random() * (999999 - 100000) + 100000);
		Long userId = tokenUtil.decodeToken(userToken);
		Optional<UserModel> isUser = repository.findById(userId);
		if (isUser.isPresent()) {
			isUser.get().setOTP(OTP);
			isUser.get().setVerifyUser(false);
			repository.save(isUser.get());
			String subject1 = "One time Password";
			String body1 = "Do not share any one use this otp to verify your profile OTP= " + OTP;
			MailService.send(isUser.get().getEmail(), body1, subject1);
		}
		throw new UserException(400, "UserId is not Found");
	}

}
