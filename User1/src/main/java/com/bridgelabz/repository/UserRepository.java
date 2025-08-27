package com.bridgelabz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findByName(String name);

	Optional<UserModel> findByEmail(String email);

	List<UserModel> findAllByName(String name);

	List<UserModel> findByNameContainsIgnoreCase(String name);

}

