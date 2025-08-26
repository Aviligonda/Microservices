package com.bridgelabz.service;

import com.bridgelabz.util.Response;

public interface IOrderService {

	Response placeOrder(String token, Long addressId, Long cartId);

	Response cancelOrder(String token, Long orderId);

	Response getAllOrdersForUser(String token);

}
