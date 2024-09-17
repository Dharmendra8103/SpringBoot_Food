package com.Food.service;

import java.util.List;

import com.Food.dto.DishDTO;
import com.Food.dto.RestaurantDTO;

public interface RestaurantRegisterService {

	public Integer registerNewRestaurant(RestaurantDTO restaurant, Integer userId) throws Exception;
	
	public Integer addDish(Integer restaurantId, DishDTO dish) throws Exception;
	
	public Integer updateDish(DishDTO dish, Integer restaurantId) throws Exception;
	
	public void deleteDish(Integer restaurantId, Integer dishId) throws Exception;
	
	public List<DishDTO> viewDishes (Integer restaurantId) throws Exception;
	
	public List<RestaurantDTO> viewRestaurants(Integer userId) throws Exception;
	
	
}
