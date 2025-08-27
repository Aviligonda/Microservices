package com.bridgelabz.service;

import com.bridgelabz.util.Response;

public interface IOrderService {

	Response placeOrder(String userToken, Long addressId, Long cartId);

	Response cancelOrder(String userToken, Long orderId);

	Response getAllOrdersForUser(String userToken);

}
