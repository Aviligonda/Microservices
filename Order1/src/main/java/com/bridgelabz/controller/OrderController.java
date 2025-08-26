package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.OrderDTO;
import com.bridgelabz.service.IOrderService;
import com.bridgelabz.util.Response;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	IOrderService service;

	@PostMapping("placeOrder/{cartId}")
	public ResponseEntity<Response> placeOrder(@RequestHeader String token, @RequestParam Long addressId,
			@PathVariable Long cartId) {
		Response response = service.placeOrder(token, addressId, cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("cancelOrder/{orderId}")
	public ResponseEntity<Response> cancelOrder(@RequestHeader String token, @PathVariable Long orderId) {
		Response response = service.cancelOrder(token, orderId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("getAllOrders")
	public ResponseEntity<Response> getAllOrdersForUser(@RequestHeader String token) {
		Response response = service.getAllOrdersForUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
