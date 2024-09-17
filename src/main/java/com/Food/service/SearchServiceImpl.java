package com.Food.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.Food.dto.DishDTO;
import com.Food.dto.RestaurantDTO;
import com.Food.dto.RestaurantTransactionDTO;
import com.Food.entity.Dish;
import com.Food.entity.Restaurant;
import com.Food.exception.FoodException;
import com.Food.repository.RestaurantRepository;

public class SearchServiceImpl implements SearchService {

	 @Autowired
	private RestaurantRepository restaurantRepository;

	@Override

	public List<RestaurantDTO> viewAllRestaurants() throws FoodException { 
		Iterable<Restaurant> restaurants = restaurantRepository.findAll(); 
		List<RestaurantDTO> list1 = new ArrayList<>();
	

	if (restaurants != null) {

	for (Restaurant re: restaurants) {

	RestaurantDTO r = new RestaurantDTO();

	r.setRestaurantId(re.getRestaurantId());

	r.setRestaurantName (re.getRestaurantName());

	r.setRestaurantContact (re.getRestaurantContact());

	r.setRestaurantType(re.getRestaurantType());
	r.setAddressLine1 (re.getAddressLine1());

	r.setArea (re.getArea());

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

	List<RestaurantDTO> list2 = list1.stream().filter(restaurant -> restaurant.getApprovalStatus().equals("Accepted")).collect(Collectors.toList());

	if(list2.isEmpty()) {

	throw new FoodException("SearchService. NO_RESTAURANTS_FOUND");

	} 
	return list2;
	}

	@Override
	public RestaurantDTO viewRestaurantDetails (Integer restaurantId) throws Exception {

	Optional<Restaurant> optional = restaurantRepository.findById(restaurantId);
	Restaurant re = optional.orElseThrow(()->new FoodException("SearchService.RESTAURANT_DOES_NOT_EXIST"));

	RestaurantDTO r = new RestaurantDTO();

	r.setRestaurantId(re.getRestaurantId());
	r.setRestaurantName (re.getRestaurantName());

	r.setRestaurantType(re.getRestaurantType());
	r.setRestaurantContact (re.getRestaurantContact());

	r.setAddressLine1 (re.getAddressLine1());

	r.setArea(re.getArea());

	r.setCity(re.getCity());

	r.setReState(re.getResState());

	r.setPincode(re.getPincode());

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

	return r;

	}
}