package com.Food.api;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import com.Food.dto.DishDTO;
import com.Food.dto.RestaurantDTO;
import com.Food.service.RestaurantRegisterService;

public class RestaurantRegisterAPI {

private RestaurantRegisterService resRegisterService; 
private Environment environment;

public ResponseEntity<Integer> registerRestaurant (RestaurantDTO restaurant, Integer userId) throws Exception {
	//Your code goes here
	return null;
}

public ResponseEntity<List<RestaurantDTO>> viewRestaurantDetails (Integer userId) throws Exception{ 
	//Your code goes here
	return null;

}



 public ResponseEntity<List<DishDTO>> viewDishDetails (Integer restaurantId) throws Exception{ //Your code goes here

	return null;
}



public ResponseEntity<String> updateDish (DishDTO dish, Integer restaurantId) throws Exception {

//Your code goes here 
	return null;

}

public ResponseEntity<String> addDish (Integer restaurantId, DishDTO dish) throws Exception { //Your code goes here

return null;

} 
public ResponseEntity<String> deleteDish (Integer dishId, Integer restaurantId) throws Exception { //Your code goes here
	return null;
}
}
	
	
	