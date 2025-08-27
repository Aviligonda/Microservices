package com.bridgelabz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.dto.CartDTO;
import com.bridgelabz.exception.CartException;
import com.bridgelabz.model.CartModel;
import com.bridgelabz.repository.CartRepository;
import com.bridgelabz.util.BookResponse;
import com.bridgelabz.util.Response;
import com.bridgelabz.util.UserRepsonse;

@Service
public class CartService implements ICartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Response addToCart(String userToken, CartDTO cartDTO, Long bookId) {
		UserRepsonse userRepsonse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserRepsonse.class);
		if (userRepsonse.getCode() == 200) {
			BookResponse bookResponse = restTemplate.getForObject("http://Book:8082/book/verify/" + bookId,
					BookResponse.class);
			if (bookResponse.getCode() == 200) {
				CartModel cartModel = new CartModel(cartDTO);
				cartModel.setBookId(bookId);
				cartModel.setUserId(userRepsonse.getObject().getId());

				if (bookResponse.getObject().getBookQuantity() >= cartDTO.getQuantity()) {
					cartModel.setQunatity(cartDTO.getQuantity());

				} else {
					throw new CartException(400, cartDTO.getQuantity() + " books are not avilible only "
							+ bookResponse.getObject().getBookQuantity() + " are there");
				}
				cartModel.setTotalPrice(cartDTO.getQuantity() * bookResponse.getObject().getBookPrice());
				cartRepository.save(cartModel);
				return new Response(200, "Success", cartModel);

			}
			throw new CartException(400, "Book id is not found");
		}
		throw new CartException(400, "user id is not found");
	}

	@Override
	public Response removingToCart(String userToken, Long cartId) {
		UserRepsonse userRepsonse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserRepsonse.class);
		if (userRepsonse.getCode() == 200) {
			Optional<CartModel> isCartItem = cartRepository.findById(cartId);
			if (isCartItem.isPresent()) {
				if (isCartItem.get().getUserId() == userRepsonse.getObject().getId()) {

					cartRepository.delete(isCartItem.get());
					return new Response(200, "Succes", isCartItem.get());
				}
				throw new CartException(400, "You don't have to remove the cart item");
			}
			throw new CartException(400, "cart id is not found");
		}
		throw new CartException(400, "user id is not found");
	}

	@Override
	public Response updateQuantity(String userToken, Long qunatity, Long cartId) {

		UserRepsonse userRepsonse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserRepsonse.class);
		if (userRepsonse.getCode() == 200) {
			Optional<CartModel> isCartItem = cartRepository.findById(cartId);
			if (isCartItem.isPresent()) {
				BookResponse bookResponse = restTemplate.getForObject(
						"http://localhost:8082/book/verify/" + isCartItem.get().getBookId(), BookResponse.class);
				if (bookResponse.getCode() == 200) {
					if (isCartItem.get().getQunatity() > qunatity) {
						isCartItem.get().setQunatity(qunatity);
						isCartItem.get().setTotalPrice(qunatity * bookResponse.getObject().getBookPrice());
						cartRepository.save(isCartItem.get());
						return new Response(200, "Success", isCartItem.get());
					} else {
						if (bookResponse.getObject().getBookQuantity() >= qunatity) {
							isCartItem.get().setQunatity(qunatity);
							isCartItem.get().setTotalPrice(qunatity * bookResponse.getObject().getBookPrice());
							cartRepository.save(isCartItem.get());
							return new Response(200, "Success", isCartItem.get());
						}
						throw new CartException(400, "Books Are not avilible");
					}
				}
				throw new CartException(400, "Book Id is not found ");
			}
			throw new CartException(400, "Cart Id is not found ");
		}
		throw new CartException(400, "User Id is not found ");
	}

	@Override
	public Response getAllCartItemsForUser(String userToken) {
		// TODO Auto-generated method stub
		UserRepsonse userRepsonse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserRepsonse.class);
		if (userRepsonse.getCode() == 200) {
			java.util.List<CartModel> list = cartRepository.findAll();
			if (list.size() > 0) {
				return new Response(200, "Succes", list);
			}
			throw new CartException(400, "No Cart items ");
		}
		throw new CartException(400, "user id is not found");
	}

	@Override
	public Response verifyCartItem(Long cartId) {
		// TODO Auto-generated method stub
		Optional<CartModel> isCartItem = cartRepository.findById(cartId);
		if (isCartItem.isPresent()) {
			return new Response(200, "Success", isCartItem.get());
		}
		throw new CartException(400, "No Cart items ");
	}

	@Override
	public Response remove(Long cartId) {
		Optional<CartModel> isCartItem = cartRepository.findById(cartId);
		if (isCartItem.isPresent()) {
			cartRepository.delete(isCartItem.get());
			return new Response(200, "Succes", isCartItem.get());
		}
		throw new CartException(400, "cartId is Not Found");

	}
}
