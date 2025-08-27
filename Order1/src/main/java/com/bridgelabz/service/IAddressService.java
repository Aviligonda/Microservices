package com.bridgelabz.service;

import com.bridgelabz.dto.AddressDTO;
import com.bridgelabz.util.Response;

public interface IAddressService {

	Response addAddress(String userToken, AddressDTO addressDTO);

	Response updateAddress(String userToken, AddressDTO addressDTO, Long addressId);


	Response gettAllAddressForUser(String userToken);

	Response deleteAddress(String userToken, Long addressId);

}
