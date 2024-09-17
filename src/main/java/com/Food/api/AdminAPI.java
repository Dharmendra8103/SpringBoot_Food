package com.Food.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Food.dto.RestaurantDTO;
import com.Food.service.AdminServices;

@CrossOrigin
@RestController
@RequestMapping("AdminAPI")
public class AdminAPI {

	

		@Autowired
		private AdminServices adminService;

		static Logger logger = LogManager.getLogger (AdminAPI.class.getName());

		@GetMapping(value = "/allRestaurants")
		public ResponseEntity<?> showAllRestaurants() throws Exception{

		logger.info("Fetch all the restaurants currently operating"); List<RestaurantDTO> restaurantList = adminService.getRestaurant(); 
		return new ResponseEntity<List<RestaurantDTO>>(restaurantList, HttpStatus.OK);

		}

		@GetMapping(value="/newlyAddedRestaurants") 
		public ResponseEntity<?> restaurantsNeedApproval() throws Exception{

		logger.info("Fetch all the restaurants which need approval"); List<RestaurantDTO> restaurantList = adminService.newlyAddedRestaurant();
		return new ResponseEntity<List<RestaurantDTO>> (restaurantList, HttpStatus.OK);

		}

		@GetMapping(value = "/ratingsBasedRestaurant/(rating)")
		public ResponseEntity<?> ratingsBasedRestaurant (@PathVariable("rating") Float rating) throws Exception{

		logger.info("Fetch all the restaurants according to the ratings provided"); List<RestaurantDTO> restaurantList = adminService.filterRestaurantOnRatings (rating);
		return new ResponseEntity<List<RestaurantDTO>> (restaurantList, HttpStatus.OK);
		}
		
		@DeleteMapping(value="/deleteRestaurant/{restaurantId}")
		public ResponseEntity<String> deleteRestaurant (@PathVariable Integer restaurantId) throws Exception{

			logger.info("Delete the restaurant");

			String resultedValue = adminService.deleteRestaurant (restaurantId); 
			return new ResponseEntity<String>(resultedValue, HttpStatus.OK);

		}

	   @PutMapping(value = "/grantPermission/{status}") 
	   public ResponseEntity<String> grantorRejectPermission (@PathVariable String status, @RequestBody RestaurantDTO restaurant) throws Exception{

		logger.info("Reject/Accept the restaurant");

		String resultedValue = adminService.grantPermissionToRestaurant (status, restaurant); 
		return new ResponseEntity<String>(resultedValue, HttpStatus.OK);

			}

}

