package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.AdminDTO;
import com.bridgelabz.exception.AdminException;
import com.bridgelabz.model.AdminModel;
import com.bridgelabz.repository.AdminRepository;
import com.bridgelabz.util.Response;
import com.bridgelabz.util.TokenUtil;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminRepository repository;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Response create(AdminDTO model) {
		List<AdminModel> isAdmin = repository.findAll();
		if (isAdmin.size() < 3) {

			AdminModel adminModel = new AdminModel(model);
			adminModel.setCreateDateTime(LocalDateTime.now());
			adminModel.setPassword(passwordEncoder.encode(model.getPassword()));
			adminModel.setEmpId((int) (Math.random() * 1000));
			Optional<AdminModel> isAdminEmail = repository.findByEmail(model.getEmail());
			if (isAdminEmail.isEmpty()) {
				adminModel.setEmail(model.getEmail());
				repository.save(adminModel);
				String subject = "Admin Registration Success";
				String body = "Your Details is " + adminModel;
				MailService.send(adminModel.getEmail(), body, subject);
				return new Response(200, "Success", adminModel);
			}
			throw new AdminException(400, "This Email alredy registred");
		}
		throw new AdminException(400, "Admins are full");
	}

	@Override
	public Response update(Long id, AdminDTO model) {
		// TODO Auto-generated method stub
		Optional<AdminModel> isAdmin = repository.findById(id);
		if (isAdmin.isPresent()) {
			isAdmin.get().setName(model.getName());
			isAdmin.get().setAge(model.getAge());
			isAdmin.get().setEmail(model.getEmail());
			isAdmin.get().setPassword(model.getPassword());
			isAdmin.get().setUpdatedDateTime(LocalDateTime.now());
			repository.save(isAdmin.get());
			return new Response(200, "Success", isAdmin.get());
		}
		throw new AdminException(400, "Admin not found with this id");
	}

	@Override
	public Response getAdmin(Long id) {
		// TODO Auto-generated method stub
		Optional<AdminModel> isAdmin = repository.findById(id);
		if (isAdmin.isPresent()) {
			return new Response(200, "Success", isAdmin.get());
		}
		throw new AdminException(400, "Admin not found with this id");
	}

	@Override
	public Response getAllAdmins() {
		// TODO Auto-generated method stub
		List<AdminModel> isAdmin = repository.findAll();
		if (isAdmin.size() > 0) {
			return new Response(200, "Success", isAdmin);
		}
		throw new AdminException(400, "Admins not found ");
	}

	@Override
	public Response delete(Long id) {
		Optional<AdminModel> isAdmin = repository.findById(id);
		if (isAdmin.isPresent()) {
			repository.delete(isAdmin.get());
			return new Response(200, "Success", isAdmin.get());
		}
		throw new AdminException(400, "Admin not found with this id");
	}

	@Override
	public Response login(String email, String password) {
		// TODO Auto-generated method stub
		Optional<AdminModel> isAdmin = repository.findByEmail(email);
		if (isAdmin.isPresent()) {
			String token = tokenUtil.createToken(isAdmin.get().getId());
			return new Response(200, "Success", token);
		}
		throw new AdminException(400, "Admin not found with this id");

	}

	@Override
	public Response verify(String adminToken) {
		// TODO Auto-generated method stub
		Long userId = tokenUtil.decodeToken(adminToken);
		Optional<AdminModel> isAdmin = repository.findById(userId);
		if (isAdmin.isPresent()) {
			return new Response(200, "Success", isAdmin.get());
		}
		throw new AdminException(400, "Admin is not found with this token");
	}

	@Override
	public Response changePassword(String email, String oldPassword, String newPassword) {
		Optional<AdminModel> isAdmin = repository.findByEmail(email);
		if (isAdmin.isPresent()) {
			if (isAdmin.get().getPassword().equals(passwordEncoder.matches(oldPassword, isAdmin.get().getPassword()))) {
				isAdmin.get().setPassword(newPassword);
				return new Response(200, "Success", isAdmin.get());
			}
			throw new AdminException(400, " password didn't match");

		}
		throw new AdminException(400, "Admin not found with this id");
	}

	@Override
	public Response forgotpassword(String email, String password) {
		Optional<AdminModel> isAdmin = repository.findByEmail(email);
		if (isAdmin.isPresent()) {
			isAdmin.get().setPassword(passwordEncoder.encode(password));
			return new Response(200, "Success", isAdmin.get());
		}
		throw new AdminException(400, "Admin not found with this id");
	}

}
