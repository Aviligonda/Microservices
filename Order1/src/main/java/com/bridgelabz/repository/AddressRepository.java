package com.bridgelabz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {

	List<AddressModel> findAllByUserId(Long userId);

}
