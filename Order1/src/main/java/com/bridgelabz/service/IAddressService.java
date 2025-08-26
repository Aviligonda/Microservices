package com.bridgelabz.service;

import com.bridgelabz.dto.AddressDTO;
import com.bridgelabz.util.Response;

public interface IAddressService {

	Response addAddress(String token, AddressDTO addressDTO);

	Response updateAddress(String token, AddressDTO addressDTO, Long addressId);


	Response gettAllAddressForUser(String token);

	Response deleteAddress(String token, Long addressId);

}
