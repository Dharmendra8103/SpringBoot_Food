package com.Food.service;

import com.Food.dto.UserAddressDTO;
import com.Food.dto.UsersDTO;

public interface UserService {

	public UsersDTO authenticateUser(String contactNumber,String password) throws Exception;
	
	public String registerNewUser(UsersDTO user) throws Exception;
	
	public Integer addNewAddress(UserAddressDTO address, Integer userId) throws Exception;
	
	public String updateAddress(UserAddressDTO address, Integer userId) throws Exception;
	
	public void deleteAddress(Integer addressId, Integer userId) throws Exception;
}
