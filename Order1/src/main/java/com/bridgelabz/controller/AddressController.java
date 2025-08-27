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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.AddressDTO;
import com.bridgelabz.model.AddressModel;
import com.bridgelabz.service.IAddressService;
import com.bridgelabz.util.Response;

@RestController
@RequestMapping("address")
public class AddressController {

	@Autowired
	IAddressService service;

	@PostMapping("add")
	public ResponseEntity<Response> addAddress(@RequestHeader String userToken, @RequestBody AddressDTO addressDTO) {
		Response response = service.addAddress(userToken, addressDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("update/{addressId}")
	public ResponseEntity<Response> updateAddress(@RequestHeader String userToken, @RequestBody AddressDTO addressDTO,
			@PathVariable Long addressId) {
		Response response = service.updateAddress(userToken, addressDTO,addressId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}


	@GetMapping("getAllAddresssForUser")
	public ResponseEntity<Response> getAllAddressForUser(@RequestHeader String userToken) {
		Response response = service.gettAllAddressForUser(userToken);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("delete/{addressId}")
	public ResponseEntity<Response> deleteAddress(@RequestHeader String userToken,@PathVariable Long addressId) {
		Response response = service.deleteAddress(userToken, addressId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
