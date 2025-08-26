package com.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, Long>{

	Optional<AdminModel> findByEmail(String email);

}
