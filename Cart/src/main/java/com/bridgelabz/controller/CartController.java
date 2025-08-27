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

import com.bridgelabz.dto.CartDTO;
import com.bridgelabz.service.ICartService;
import com.bridgelabz.util.Response;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	ICartService service;

	@PostMapping("addToCart")
	public ResponseEntity<Response> addToCartBook(@RequestHeader String userToken, @RequestBody CartDTO cartDTO,
			@RequestParam Long bookId) {
		Response response = service.addToCart(userToken, cartDTO, bookId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("removingToCart/{cartId}")
	public ResponseEntity<Response> removingToCart(@RequestHeader String userToken, @PathVariable Long cartId) {
		Response response = service.removingToCart(userToken, cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("updateQunatity/{cartId}")
	public ResponseEntity<Response> updateQuantity(@RequestHeader String userToken, @PathVariable Long cartId,
			@RequestParam Long qunatity) {
		Response response = service.updateQuantity(userToken, qunatity, cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllCartItemsForUser")
	public ResponseEntity<Response> getAllCartItemsForUser(@RequestHeader String userToken) {
		Response response = service.getAllCartItemsForUser(userToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("verify/{cartId}")
	public ResponseEntity<Response> verifyCartItem(@PathVariable Long cartId) {
		Response response = service.verifyCartItem(cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@GetMapping("remove/{cartId}")
	public ResponseEntity<Response> remove(@PathVariable Long cartId) {
		Response response = service.remove(cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
