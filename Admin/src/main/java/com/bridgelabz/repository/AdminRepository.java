package com.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.AdminModel;
/**
 * Purpose : AdminRepository Are Used to Store the Data into Database
 * @author : Aviligonda Sreenivasulu
 * */
public interface AdminRepository extends JpaRepository<AdminModel, Long>{

	Optional<AdminModel> findByEmail(String email);

}
