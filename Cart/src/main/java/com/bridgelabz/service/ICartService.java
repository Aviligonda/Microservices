package com.bridgelabz.service;

import com.bridgelabz.dto.CartDTO;
import com.bridgelabz.util.Response;

public interface ICartService {

	Response addToCart(String userToken, CartDTO cartDTO, Long bookId);

	Response removingToCart(String userToken, Long cartId);

	Response updateQuantity(String userToken, Long qunatity, Long cartId);

	Response getAllCartItemsForUser(String userToken);

	Response verifyCartItem(Long cartId);

	Response remove(Long cartId);

}
