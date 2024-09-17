package com.Food.api;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import com.Food.dto.UserAddressDTO;
import com.Food.dto.UsersDTO;
import com.Food.service.RestaurantRecommendationService;
import com.Food.service.UserService;


public class UserAPI { 
	
	
	
 private UserService userService;

private Environment environment;

private RestaurantRecommendationService recommendationService;

public ResponseEntity<String> registerUser (UsersDTO user) throws Exception { //Your code goes here

return null;

}

public ResponseEntity<UsersDTO> authenticateUser (UsersDTO user) throws Exception {

//Your code goes here 
	return null;
}

public ResponseEntity<Integer> addAddress (UserAddressDTO address, Integer userId) throws Exception { //Your code goes here
	return null;
}

public ResponseEntity<String> updateAddress (UserAddressDTO address, Integer userId) throws Exception { //Your code goes here 
	return null;

}

public ResponseEntity<String> deleteAddress (Integer addressId, Integer userId) throws Exception { //Your code goes here 
	return null;
}

public ResponseEntity<UsersDTO> getAddressList(Integer userId) throws Exception{ //Your code goes here 
	return null;

}

}