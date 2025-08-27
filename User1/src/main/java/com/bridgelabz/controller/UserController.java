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

import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.service.IUserService;
import com.bridgelabz.util.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/getMessage")
	public String getString() {
		return "Hello welcome to springboot";
	}

	@PostMapping("/add")
	public ResponseEntity<Response> addUser(@Valid @RequestBody UserDTO user, @RequestHeader String adminToken) {
		Response response = userService.createUser(user, adminToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getUser(@PathVariable Long id, @RequestHeader String admintoken) {
		Response response = userService.getUser(id, admintoken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Response> getAll(@RequestHeader String adminToken) {
		Response response = userService.getAll(adminToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody UserDTO userDTO,
			@RequestHeader String adminToken) {
		Response response = userService.update(id, userDTO, adminToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id, @RequestHeader String adminToken) {
		Response response = userService.delete(id, adminToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String email, @RequestParam String password) {
		Response response = userService.login(email, password);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/verify/{userToken}")
	public ResponseEntity<Response> verify(@PathVariable String userToken) {
		Response response = userService.verify(userToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("search")
	public ResponseEntity<Response> search(@RequestParam String name) {
		Response response = userService.search(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("verificationUser")
	public ResponseEntity<Response> verificationUser(@RequestHeader String userToken, @RequestParam Long otp) {
		Response response = userService.verificationUser(userToken,otp);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping("sendOtp")
	public ResponseEntity<Response> reSendOtp(@RequestHeader String userToken) {
		Response response = userService.reSendOtp(userToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
