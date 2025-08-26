package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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

	@Override
	public Response createUser(UserDTO user, String token) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + token,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {

			// TODO Auto-generated method stub
			UserModel userModel = new UserModel(user);
			userModel.setCreatdTime(LocalDateTime.now());
			userModel.setAdminEmpId(isAdmin.getObject().getEmpId());
			repository.save(userModel);
			String subject = "Registrarions Done ";
			String body = "Successfully registerd details is \n" + userModel;
			MailService.send(userModel.getEmail(), body, subject);
			return new Response(200, "Success", userModel);
		}
		throw new UserException(400, "Admin is not found");
	}

	@Override
	public Response getUser(Long id, String token) {
		// TODO Auto-generated method stub
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + token,
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
	public Response getAll(String token) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + token,
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
	public Response update(Long id, UserDTO userDTO, String token) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + token,
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
	public Response delete(Long id, String token) {
		// TODO Auto-generated method stub
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + token,
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
			if (isUser.get().getPassword().equals(password)) {
				String token = tokenUtil.createToken(isUser.get().getId());
				return new Response(200, "Success", token);
			}
		}
		throw new UserException(400, "UserId is not Found");
	}

	@Override
	public Response verify(String token) {
		// TODO Auto-generated method stub
		Long userId = tokenUtil.decodeToken(token);
		Optional<UserModel> isUser = repository.findById(userId);
		if (isUser.isPresent()) {
			return new Response(200, "Succes", isUser.get());
		}
		throw new UserException(400, "UserId is not Found");
	}

}
