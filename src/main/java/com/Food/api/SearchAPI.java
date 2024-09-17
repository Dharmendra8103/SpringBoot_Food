package com.Food.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Food.dto.RestaurantDTO;
import com.Food.service.SearchService;

@CrossOrigin
@RestController
@RequestMapping("searchAPI")
public class SearchAPI {
	

    @Autowired
    private SearchService searchService;

  @RequestMapping(value = "/viewRestaurant/{restaurantId}", method = RequestMethod.GET) 
   public ResponseEntity<RestaurantDTO> viewRestaurantDetails (@PathVariable Integer restaurantId) throws Exception{

 RestaurantDTO restaurant =searchService.viewRestaurantDetails(restaurantId);

 return new ResponseEntity<RestaurantDTO> (restaurant, HttpStatus.OK);

 }

@RequestMapping (value = "/viewAllRestaurants", method = RequestMethod.GET)
public ResponseEntity<List<RestaurantDTO>> viewAllRestaurants() throws Exception{

List<RestaurantDTO> restaurants = searchService.viewAllRestaurants();
return new ResponseEntity<List<RestaurantDTO>>(restaurants, HttpStatus.OK);
}
}