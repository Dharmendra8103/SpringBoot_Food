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
import com.Food.dto.RolesDTO;
import com.Food.dto.UsersDTO;
import com.Food.entity.Dish;
import com.Food.entity.Restaurant;
import com.Food.entity.Roles;
import com.Food.entity.Users;
import com.Food.exception.FoodException;
import com.Food.repository.RestaurantRepository;
import com.Food.repository.UsersRepository;

@Service(value="restaurantRecommendationService")
@Transactional
public class RestaurantRecommendationServiceImpl  implements RestaurantRecommendationService {

    @Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private UsersRepository userRepository;

	@Override
	public List<RestaurantDTO> getRecommendationByArea(String area) throws Exception {

	//Your code goes here

	List<Restaurant> restaurantList = restaurantRepository.findByArea(area);

	

	List<RestaurantDTO> list = new ArrayList<>();

	if (restaurantList != null) {

	for (Restaurant re: restaurantList) {

	RestaurantDTO r = new RestaurantDTO();
	r.setRestaurantId (re.getRestaurantId());

	r.setRestaurantName (re.getRestaurantName());

	r.setRestaurantContact (re.getRestaurantContact());

	r.setRestaurantType(re.getRestaurantType());

	r.setAddressLine1 (re.getAddressLine1());
	r.setArea (re.getArea());

	r.setCity(re.getCity());

	r.setReState(re.getResState()); 
	r.setPincode (re.getPincode());

	r.setApprovalStatus (re.getApprovalStatus()); 
	r.setAvRating (re.getAvgRating());

	String[] photos=re.getPhotoUrls().split("-");

	r.setPhotoUrls (Arrays.asList(photos));
	if(!re.getDishes().isEmpty()) {

	List<DishDTO> dishList=new ArrayList<>();
	for (Dish de:re.getDishes()) {

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

	list.add(r);

	}

	} 
	List<RestaurantDTO> list2 = list.stream().filter(restaurant -> restaurant.getApprovalStatus().equals("Accepted")).collect(Collectors.toList());

	if (restaurantList.isEmpty()) {
		throw new FoodException("RecommendationService.NO_RESTAURANT_FOUND");
	}
	return list2;
	}

	@Override
	public UsersDTO getUser(Integer userId) throws Exception {

	//Your code goes here

	Optional<Users> usero=userRepository.findById(userId);
	Users u=usero.orElseThrow(()->new Exception("User does not Exist"));

	UsersDTO udto=new UsersDTO();
	udto.setUserId(u.getUserId());

	udto.setUserName (u.getUserName());
	udto.setEmailId (u.getEmailId());

	udto.setPassword(u.getPassword());

	 List<RolesDTO> roles=new ArrayList<>();

	for (Roles rdto: u.getRoles()) {
		RolesDTO e=new RolesDTO();
	    e.setRoleType(rdto.getRoleType());
	    roles.add(e);

	}

	udto.setRoles (roles);

	return udto;
	}

}
