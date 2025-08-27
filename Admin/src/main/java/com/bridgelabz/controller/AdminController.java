package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.AdminDTO;
import com.bridgelabz.model.AdminModel;
import com.bridgelabz.service.IAdminService;
import com.bridgelabz.util.Response;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	IAdminService service;

	@PostMapping("/create")
	public ResponseEntity<Response> create(@RequestBody AdminDTO model) {
		Response response = service.create(model);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody AdminDTO model) {
		Response response = service.update(id, model);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getAdmin(@PathVariable Long id) {
		Response response = service.getAdmin(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllAdmins() {
		Response response = service.getAllAdmins();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id) {
		Response response = service.delete(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String email, @RequestParam String password) {
		Response response = service.login(email, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/verify/{adminToken}")
	public Response adminVerification(@PathVariable String adminToken) {
		return service.verify(adminToken);
	}

	@PutMapping("changePassword")
	public ResponseEntity<Response> changePassword(@RequestParam String email, @RequestParam String oldPassword,String newPassword) {
		Response response = service.changePassword(email, oldPassword,newPassword);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestParam String email, @RequestParam String password) {
		Response response = service.forgotpassword(email, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
