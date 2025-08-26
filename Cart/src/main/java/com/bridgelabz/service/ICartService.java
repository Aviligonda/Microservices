package com.bridgelabz.service;

import com.bridgelabz.dto.CartDTO;
import com.bridgelabz.util.Response;

public interface ICartService {

	Response addToCart(String token, CartDTO cartDTO, Long bookId);

	Response removingToCart(String token, Long cartId);

	Response updateQuantity(String token, Long qunatity, Long cartId);

	Response getAllCartItemsForUser(String token);

	Response verifyCartItem(Long cartId);

}
