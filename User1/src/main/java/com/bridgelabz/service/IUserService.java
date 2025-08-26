package com.bridgelabz.service;

import com.bridgelabz.dto.UserDTO;
import com.bridgelabz.util.Response;

public interface IUserService {

	Response createUser(UserDTO user, String token);

	Response getUser(Long id, String token);

	Response getAll(String token);

	Response update(Long id, UserDTO userDTO,String token);

	Response delete(Long id,String token);

	Response login(String email, String password);

	Response verify(String token);

}
