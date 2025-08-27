package com.bridgelabz.service;

import com.bridgelabz.dto.AdminDTO;
import com.bridgelabz.util.Response;

public interface IAdminService {

	Response create(AdminDTO model);

	Response update(Long id, AdminDTO model);

	Response getAdmin(Long id);

	Response getAllAdmins();

	Response delete(Long id);

	Response login(String email, String password);

	Response verify(String AdminToken);

	Response changePassword(String email, String oldPassword, String newPassword);

	Response forgotpassword(String email, String password);

}
