package com.bridgelabz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long>{

	List<OrderModel> findAllByUserId(Long userId);

}
