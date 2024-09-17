package com.Food.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Food.dto.DishDTO;
import com.Food.dto.RestaurantDTO;
import com.Food.dto.RestaurantTransactionDTO;
import com.Food.entity.Dish;
import com.Food.entity.Restaurant;
import com.Food.exception.FoodException;
import com.Food.repository.RestaurantRepository;

@Service(value="adminService")
@Transactional
public class AdminServicesImpl implements AdminServices {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	

 
    @Override
	public String deleteRestaurant (Integer restaurantID) throws FoodException {

	Optional<Restaurant> optional = restaurantRepository.findById(restaurantID);

	Restaurant restaurant = optional.orElseThrow(()->new FoodException("AdminServices.RESTAURANT_DOES_NOT_EXIST"));
	restaurant.setApprovalStatus("Rejected");
	return restaurant.getRestaurantName();

    }

	@Override
	public String grantPermissionToRestaurant (String status, RestaurantDTO restaurant) throws FoodException {

	Optional<Restaurant> optional= restaurantRepository.findById(restaurant.getRestaurantId()); 
	Restaurant restaurantEntity = optional.orElseThrow(()->new FoodException("AdminServices.RESTAURANT_DOES_NOT_EXIST"));

	restaurantEntity.setRestaurantId(restaurant.getRestaurantId());

	restaurantEntity.setRestaurantName (restaurant.getRestaurantName());
	restaurantEntity.setRestaurantType(restaurant.getRestaurantType());

	restaurantEntity.setRestaurantContact (restaurant.getRestaurantContact());
	restaurantEntity.setAddressLine1 (restaurant.getAddressLine1());

	restaurantEntity.setArea (restaurant.getArea()); 
	restaurantEntity.setCity (restaurant.getCity());

	restaurantEntity.setResState (restaurant.getReState());
	restaurantEntity.setAvgRating (1.0); 
	restaurantEntity.setPincode(restaurant.getPincode());

	restaurantEntity.setApprovalStatus(status);

	restaurantEntity.setDishes (null);

	return restaurantEntity.getApprovalStatus();
	}
	
	 @Override
	public List<RestaurantDTO> getRestaurant() throws FoodException{

	Iterable<Restaurant> restaurants = restaurantRepository.findAll();

	List<RestaurantDTO> list1 = new ArrayList<>();

	if (restaurants != null) {

	for (Restaurant re: restaurants) {

	RestaurantDTO r = new RestaurantDTO(); r.setRestaurantId(re.getRestaurantId());

	r.setRestaurantName(re.getRestaurantName()); 
	r.setRestaurantContact (re.getRestaurantContact());

	r.setRestaurantType(re.getRestaurantType());

	r.setAddressLine1 (re.getAddressLine1());

	r.setArea(re.getArea());

	r.setCity(re.getCity());

	r.setReState(re.getResState());
	r.setPincode (re.getPincode());

	r.setApprovalStatus (re.getApprovalStatus()); 
	r.setAvRating (re.getAvgRating());

	String[] photos=re.getPhotoUrls().split("-");

	r.setPhotoUrls (Arrays.asList(photos));
	if(!re.getDishes().isEmpty()) {

	List <DishDTO> dishList=new ArrayList<>(); 
	for (Dish de: re.getDishes()) {

	DishDTO d=new DishDTO();

	d.setDishId(de.getDishId());

	d.setDishName (de.getDishName());

	d.setDishType(de.getDishType());
	d.setDishCuisine (de.getDishCuisine());

	d.setDishDescription (de.getDishDescription()); 
	d.setAvgRating (de.getAvgRating());

	d.setPrice(de.getPrice());

	d.setSpeciality (de.getSpeciality()); 
	d.setImageUrl(de.getImageUrl());

	dishList.add(d);
	}
	 r.setDishes (dishList);

	} 
	if(re.getTransaction() !=null) {

	RestaurantTransactionDTO rt=new RestaurantTransactionDTO();
	rt.setRestaurantTransactionId (re.getTransaction().getRestaurantTransactionId()); 
	rt.setRestaurantStatus(re.getTransaction().getRestaurantStatus());
	rt.setRestaurantApproxCost (re.getTransaction().getRestaurantApproxCost());
	rt.setRestaurantOrderCounter (re.getTransaction().getRestaurantOrderCounter());
	r.setTransaction(rt);

	}
	list1.add(r);

	}

	} 
	
	List<RestaurantDTO> acceptedRestaurants = list1.stream().filter(restaurant -> restaurant.getApprovalStatus().equals("Accepted"))
			                                  . collect (Collectors.toList());

	if(acceptedRestaurants.isEmpty()) {

	throw new FoodException("AdminService.NO_RESTAURANTS_FOUND");

	} 
	return acceptedRestaurants;
	}
	

	@Override
	public List<RestaurantDTO> filterRestaurantOnRatings (Float ratings) throws FoodException{

	Iterable<Restaurant> restaurants = restaurantRepository.findAll();

	List<RestaurantDTO> list1 = new ArrayList<>();

	if (restaurants != null) {

	for (Restaurant re: restaurants) {

	RestaurantDTO r = new RestaurantDTO();

	r.setRestaurantName (re.getRestaurantName());

	r.setRestaurantId(re.getRestaurantId()); 
	r.setRestaurantContact (re.getRestaurantContact());
	r.setRestaurantType(re.getRestaurantType());
	
	r.setRestaurantType(re.getRestaurantType()); 
	r.setAddressLine1 (re.getAddressLine1());

	r.setArea(re.getArea());

	r.setCity(re.getCity());

	r.setReState (re.getResState());

	r.setPincode (re.getPincode());

	r.setApprovalStatus (re.getApprovalStatus());
	r.setAvRating (re.getAvgRating());

	String[] photos=re.getPhotoUrls().split("-");
	r.setPhotoUrls (Arrays.asList(photos));

	if(!re.getDishes().isEmpty()) {

	List<DishDTO> dishList=new ArrayList<>();

	for (Dish de: re.getDishes()) {

	DishDTO d=new DishDTO();

	d.setDishId(de.getDishId());

	d.setDishName (de.getDishName());

	d.setDishType (de.getDishType());

	d.setDishCuisine (de.getDishCuisine());

	d.setDishDescription (de.getDishDescription());

	d.setAvgRating (de.getAvgRating()); 
	d.setPrice(de.getPrice());

	d.setSpeciality (de.getSpeciality());

	d.setImageUrl(de.getImageUrl());

	dishList.add(d);

	}

	r.setDishes (dishList);
}
	if(re.getTransaction() !=null) {

	RestaurantTransactionDTO rt=new RestaurantTransactionDTO();
	rt.setRestaurantTransactionId (re.getTransaction().getRestaurantTransactionId());

	rt.setRestaurantStatus (re.getTransaction().getRestaurantStatus());
	rt.setRestaurantApproxCost (re.getTransaction().getRestaurantApproxCost());
	rt.setRestaurantOrderCounter (re.getTransaction().getRestaurantOrderCounter());
	r.setTransaction(rt);
	}
	list1.add(r);
	}
}
	Double parsedRating = (double) ratings;

	List<RestaurantDTO> filteredRestaurant = new ArrayList<>();

	filteredRestaurant = list1.stream().filter(restaurant ->

	restaurant.getAvRating() <parsedRating && restaurant.getApprovalStatus().equals("Accepted"))

	.collect(Collectors.toList());

	if(filteredRestaurant.isEmpty()) throw new FoodException("AdminService.NO RESTAURANT_FOUND_UNDER RATINGS"); 
	return filteredRestaurant;

	}

	@Override

	public List<RestaurantDTO> newlyAddedRestaurant() throws FoodException{

	Iterable<Restaurant> restaurants = restaurantRepository.findAll();

	List<RestaurantDTO> list1 =new ArrayList<>();

	if(restaurants != null) {

	for (Restaurant re: restaurants) {

	RestaurantDTO r = new RestaurantDTO();

	r.setRestaurantId(re.getRestaurantId());

	r.setRestaurantName (re.getRestaurantName()); 
	r.setRestaurantContact (re.getRestaurantContact());

	r.setRestaurantType(re.getRestaurantType());

	r.setAddressLine1(re.getAddressLine1());

	r.setArea(re.getArea());

	r.setCity(re.getCity());

	r.setReState(re.getResState());

	r.setPincode(re.getPincode());

	r.setApprovalStatus(re.getApprovalStatus());

	r.setAvRating(re.getAvgRating()); 
	String[] photos=re.getPhotoUrls().split("-");

	r.setPhotoUrls (Arrays.asList(photos));

	if(!re.getDishes().isEmpty()) {
		List <DishDTO> dishList=new ArrayList<>();
	

	for (Dish de: re.getDishes()) {

	DishDTO d=new DishDTO();

	d.setDishId(de.getDishId());

	d.setDishName (de.getDishName());

	d.setDishType(de.getDishType());

	d.setDishCuisine (de.getDishCuisine());
	d.setDishDescription(de.getDishDescription());

	d.setAvgRating (de.getAvgRating()); d.setPrice(de.getPrice());

	d.setSpeciality(de.getSpeciality());

	d.setImageUrl(de.getImageUrl());

	dishList.add(d);
	}

	r.setDishes (dishList);

	}
	if(re.getTransaction()!=null) {

	RestaurantTransactionDTO rt = new RestaurantTransactionDTO(); 
	rt.setRestaurantTransactionId(re.getTransaction().getRestaurantTransactionId());
	rt.setRestaurantStatus(re.getTransaction().getRestaurantStatus()); 
	rt.setRestaurantApproxCost (re.getTransaction().getRestaurantApproxCost());
	rt.setRestaurantOrderCounter(re.getTransaction().getRestaurantOrderCounter()); 
	r.setTransaction(rt);
	}
	list1.add(r);
	}
	}

	List<RestaurantDTO> newRestaurants= new ArrayList<>();
	newRestaurants =list1.stream().
	filter(restaurant ->restaurant.getApprovalStatus().equals("Pending"))

	.collect(Collectors.toList()); 
	if(newRestaurants.isEmpty()) throw new FoodException("AdminService.NO_NEW_RESTAURANT");

	return newRestaurants;
	}
	}


