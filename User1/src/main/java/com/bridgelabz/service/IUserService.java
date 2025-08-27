package com.bridgelabz.service;

import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.util.Response;

public interface IUserService {

	Response createUser(UserDTO user, String adminToken);

	Response getUser(Long id, String adminToken);

	Response getAll(String adminToken);

	Response update(Long id, UserDTO userDTO,String adminToken);

	Response delete(Long id,String token);

	Response login(String email, String password);

	Response verify(String userToken);

	Response search(String name);


	Response verificationUser(String userToken, Long otp);

	Response reSendOtp(String userToken);

}
