package com.bridgelabz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.dto.AddressDTO;
import com.bridgelabz.exception.OrderException;
import com.bridgelabz.model.AddressModel;
import com.bridgelabz.repository.AddressRepository;
import com.bridgelabz.util.Response;
import com.bridgelabz.util.TokenUtil;
import com.bridgelabz.util.UserResponse;

@Service
public class AddressService implements IAddressService {

	@Autowired
	AddressRepository repository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public Response addAddress(String token, AddressDTO addressDTO) {

		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + token,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			AddressModel addressModel = new AddressModel(addressDTO);
			addressModel.setUserId(userResponse.getObject().getId());
			repository.save(addressModel);
			return new Response(200, "Sucess", addressModel);
		}
		// TODO Auto-generated method stub
		throw new OrderException(400, "User is not found");
	}

	@Override
	public Response updateAddress(String token, AddressDTO addressDTO, Long addressId) {
		// TODO Auto-generated method stub
		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + token,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			Optional<AddressModel> isAddress = repository.findById(addressId);
			if (isAddress.isPresent()) {
				if (isAddress.get().getUserId() == userResponse.getObject().getId()) {
					isAddress.get().setName(addressDTO.getName());
					isAddress.get().setPhoneNumber(addressDTO.getPhoneNumber());
					repository.save(isAddress.get());
					return new Response(200, "Success", isAddress.get());
				}
				throw new OrderException(400, "you don't have a permission to upate");
			}
			throw new OrderException(400, "Adddress id is not found");
		}
		throw new OrderException(400, "User id is not found");
	}

	@Override
	public Response gettAllAddressForUser(String token) {
		// TODO Auto-generated method stub
		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + token,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			Long userId = tokenUtil.decodeToken(token);
			List<AddressModel> addressModels = repository.findAllByUserId(userId);
			if (addressModels.size() > 0) {
				return new Response(200, "Success", addressModels);
			}
			throw new OrderException(400, "no Addresss found");
		}
		throw new OrderException(400, "User is not found");
	}

	@Override
	public Response deleteAddress(String token, Long addressId) {
		// TODO Auto-generated method stub
		UserResponse userResponse = restTemplate.getForObject("http://User:8081/user/verify/" + token,
				UserResponse.class);

		if (userResponse.getCode() == 200) {
			Optional<AddressModel> isAddress = repository.findById(addressId);
			if (isAddress.isPresent()) {
				if (isAddress.get().getUserId() == userResponse.getObject().getId()) {
					repository.delete(isAddress.get());
					return new Response(200, "Success", isAddress.get());
				}
				throw new OrderException(400, "you don't have a permission to delete");
			}
			throw new OrderException(400, "Adddress id is not found");
		}
		throw new OrderException(400, "User id is not found");
	}
}
