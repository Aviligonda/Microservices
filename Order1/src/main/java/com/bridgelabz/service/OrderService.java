package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.exception.OrderException;
import com.bridgelabz.model.AddressModel;
import com.bridgelabz.model.OrderModel;
import com.bridgelabz.repository.AddressRepository;
import com.bridgelabz.repository.OrderRepository;
import com.bridgelabz.util.BookResponse;
import com.bridgelabz.util.CartResponse;
import com.bridgelabz.util.Response;
import com.bridgelabz.util.TokenUtil;
import com.bridgelabz.util.UserResponse;

@Service
public class OrderService implements IOrderService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	OrderRepository repository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public Response placeOrder(String userToken, Long addressId, Long cartId) {

		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			CartResponse cartResponse = restTemplate.getForObject("http://Cart:8083/cart/verify/" + cartId,
					CartResponse.class);
			if (cartResponse.getCode() == 200) {
				if (userResponse.getObject().getId() == cartResponse.getObject().getUserId()) {

					OrderModel orderModel = new OrderModel();
					orderModel.setBookId(cartResponse.getObject().getBookId());
					orderModel.setOrderDate(LocalDateTime.now());
					orderModel.setQuantity(cartResponse.getObject().getQunatity());
					orderModel.setPrice(cartResponse.getObject().getTotalPrice());
					orderModel.setUserId(userResponse.getObject().getId());
					orderModel.setCancel(false);
					Optional<AddressModel> isAddress = addressRepository.findById(addressId);
					if (isAddress.isPresent()) {
						if (isAddress.get().getUserId() == userResponse.getObject().getId()) {
							orderModel.setAddressModel(isAddress.get());

						} else {
							throw new OrderException(400, "userId Didn't match");
						}
					} else {

						throw new OrderException(400, "Address Id not found");
					}
					repository.save(orderModel);
					CartResponse response = restTemplate.getForObject("http://Cart:8083/cart/remove/" + cartId,
							CartResponse.class);
					BookResponse bookResponse = restTemplate.getForObject("http://Book:8082/book/decresingBookQuantity/"
							+ orderModel.getBookId() + "/" + orderModel.getQuantity(), BookResponse.class);
					return new Response(200, "Success", orderModel);
				}

				throw new OrderException(400, "userId Didn't match");
			}
			throw new OrderException(400, "cart not found");
		}
		// TODO Auto-generated method stub
		throw new OrderException(400, "user not found");
	}

	@Override
	public Response cancelOrder(String userToken, Long orderId) {
		// TODO Auto-generated method stub

		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			Optional<OrderModel> isOrder = repository.findById(orderId);
			if (isOrder.isPresent()) {
				if (isOrder.get().getUserId() == userResponse.getObject().getId()) {
					isOrder.get().setCancel(true);
					repository.save(isOrder.get());
					BookResponse bookResponse = restTemplate.getForObject("http://Book:8082/book/incresingBookQuantity/"
							+ isOrder.get().getBookId() + "/" + isOrder.get().getQuantity(), BookResponse.class);
					return new Response(200, "Success", isOrder.get());
				}
				throw new OrderException(400, "userId not match");
			}
			throw new OrderException(400, "OrderId not found");
		}
		throw new OrderException(400, "user not found");
	}

	@Override
	public Response getAllOrdersForUser(String userToken) {

		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + userToken,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			Long userId = tokenUtil.decodeToken(userToken);
			List<OrderModel> orders = repository.findAllByUserId(userId);
			if (orders.size() > 0) {
				return new Response(200, "Success", orders);
			}
			throw new OrderException(400, "No Orders Found");
		}

		throw new OrderException(400, "user not found");
	}

}
