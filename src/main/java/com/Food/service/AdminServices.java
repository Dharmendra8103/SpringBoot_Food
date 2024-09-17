package com.Food.service;

import java.util.List;

import com.Food.dto.RestaurantDTO;

public interface AdminServices {
	public String deleteRestaurant(Integer restaurantId) throws Exception;
	
	public String grantPermissionToRestaurant(String status,RestaurantDTO restaurant) throws Exception;
	
	public List<RestaurantDTO> filterRestaurantOnRatings(Float ratings) throws Exception;

	public List<RestaurantDTO> getRestaurant() throws Exception;
	
	public List<RestaurantDTO> newlyAddedRestaurant() throws Exception;
}
