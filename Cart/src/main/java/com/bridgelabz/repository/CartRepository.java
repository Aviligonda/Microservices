package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.CartModel;

public interface CartRepository extends JpaRepository<CartModel, Long>{

}
