package com.Food.service;

import java.util.List;

import com.Food.dto.RestaurantDTO;
import com.Food.dto.UsersDTO;

public interface RestaurantRecommendationService {
 
	public List<RestaurantDTO> getRecommendationByArea(String area) throws Exception;
	
	public UsersDTO getUser(Integer userId) throws Exception;
}
