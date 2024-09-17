package com.Food.api;



import java.util.List;

import org.springframework.http. ResponseEntity;

import com.Food.dto.RestaurantDTO;
import com.Food.service.RestaurantRecommendationService;


public class RestaurantRecommendationAPI {

private RestaurantRecommendationService recommendationService;

public ResponseEntity<List<RestaurantDTO>> getRecommendationByArea(String area) throws Exception{

//Your code goes here 
	return null;
}
}
