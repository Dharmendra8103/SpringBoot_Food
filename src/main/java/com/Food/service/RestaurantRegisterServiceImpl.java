package com.Food.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Food.dto.DishDTO;
import com.Food.dto.RestaurantDTO;
import com.Food.entity.Dish;
import com.Food.entity.DishRating;
import com.Food.entity.Restaurant;
import com.Food.entity.RestaurantTransaction;
import com.Food.entity.Users;
import com.Food.exception.FoodException;
import com.Food.repository.DishRatingRepository;
import com.Food.repository.DishRepository;
import com.Food.repository.RestaurantRepository;
import com.Food.repository.UsersRepository;

@Service(value="restaurantService")
@Transactional
public class RestaurantRegisterServiceImpl implements  RestaurantRegisterService{

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private DishRatingRepository dishRatingRepository;

	@Override
	public Integer registerNewRestaurant (RestaurantDTO restaurant, Integer userId) throws FoodException {

	//RestaurantValidator.validateRestaurant(restaurant);

	Optional<Restaurant> optional = restaurantRepository.findByRestaurantNameAndRestaurantContact (restaurant.getRestaurantName(),

	restaurant.getRestaurantContact());

	if (optional.isPresent())

	throw new FoodException ("RestaurantRegisterService.RESTAURANT CANNOT_BE_REGISTERED");

	else {

	Optional<Users> ue = userRepository.findById(userId);

	Users user = ue.orElseThrow(()-> new FoodException("RestaurantRegisterService.NO_USER_FOUND"));

	Restaurant restaurantEntity = new Restaurant(); 
	restaurantEntity.setRestaurantName (restaurant.getRestaurantName());

	restaurantEntity.setRestaurantType(restaurant.getRestaurantType()); 
	restaurantEntity.setRestaurantContact (restaurant.getRestaurantContact());

	restaurantEntity.setAddressLine1 (restaurant.getAddressLine1());

	restaurantEntity.setArea (restaurant.getArea());

	restaurantEntity.setCity (restaurant.getCity());
	restaurantEntity.setResState (restaurant.getReState());

	restaurantEntity.setAvgRating (2.5);

	restaurantEntity.setApprovalStatus ("Pending"); 
	restaurantEntity.setPincode (restaurant.getPincode());

	restaurantEntity.setPhotoUrls("/assets/defaut_rest.jpg");

	restaurantEntity.setDishes (null);

	RestaurantTransaction rte = new RestaurantTransaction();

	rte.setRestaurantApproxCost(200);

	rte.setRestaurantOrderCounter(0);

	rte.setRestaurantStatus(false);

	restaurantEntity.setTransaction(rte);

	List<Restaurant> l1 = user.getRestaurants();

	l1.add(restaurantEntity);

	user.setRestaurants (l1);

	Optional<Restaurant> optional1 = restaurantRepository.findByRestaurantNameAndRestaurantContact (restaurant.getRestaurantName(), restaurant.getRestaurantContact());
		
    	Restaurant re1 = new Restaurant();
	  

	if (optional.isPresent())
		re1 =optional1.get();

	return re1.getRestaurantId();
	}
	}

	@Override
	public Integer addDish (Integer restaurantId, DishDTO dish) throws FoodException {

	//RestaurantValidator.validateDish(dish);

	String name =dish.getDishName();
	String description = dish.getDishDescription();
	Double price= dish.getPrice();

     Optional <Dish> opt= dishRepository.findByDishNameAndDishDescriptionAndPrice (name, description, price);

	if (opt.isPresent()) {

	throw new FoodException ("RestaurantRegisterService.DISH_CANNOT_BE_ADDED");

	}

	Optional <Restaurant> optional1 = restaurantRepository.findById(restaurantId);
	Restaurant restaurantEntity = optional1.orElseThrow(()-> new FoodException("Restaurant RegisterService. RESTAURANT_DOES_NOT_EXIST"));

	Dish newDishEntity = new Dish();

	newDishEntity.setDishName (dish.getDishName());
	newDishEntity.setDishCuisine (dish.getDishCuisine());

	newDishEntity.setDishDescription (dish.getDishDescription());

	newDishEntity.setDishType(dish.getDishType());

	newDishEntity.setPrice(dish.getPrice());
	newDishEntity.setSpeciality (dish.getSpeciality());

	newDishEntity.setAvgRating (2.5);

	newDishEntity.setImageUrl("assets/default_food.jpg");

	List<Dish> d1= restaurantEntity.getDishes();

	d1.add(newDishEntity);

	restaurantEntity.setDishes (d1);

	Optional <Dish> optional= dishRepository.findByDishNameAndDishDescriptionAndPrice (name, description, price);

	Dish de = new Dish();

	if (optional.isPresent()) 
		de =optional.get();

	return de.getDishId();

	}

	@Override
	public Integer updateDish (DishDTO dish, Integer restaurantId) throws FoodException {

	if(dish.getDishId() ==null) 
		throw new FoodException("Restaurant RegisterService.DISH_CANNOT_BE_UPDATED");
	//  RestaurantValidator.validateDish (dish);

	Optional <Dish> optional= dishRepository.findById(dish.getDishId()); 
	Dish dishEntity = optional.orElseThrow(()->new FoodException("Restaurant RegisterService.NO_DISH_FOUND"));

	dishEntity.setDishName (dish.getDishName()); 
	dishEntity.setDishCuisine (dish.getDishCuisine());

	dishEntity.setDishDescription (dish.getDishDescription()); 
	dishEntity.setDishType (dish.getDishType());
	dishEntity.setPrice(dish.getPrice()); 
	dishEntity.setSpeciality (dish.getSpeciality());
	
	return dish.getDishId();
	}
	
	
	@Override
	public void deleteDish (Integer restaurantId, Integer dishId) 	throws Exception {

	Optional <Restaurant> optional1= restaurantRepository.findById(restaurantId);

	Restaurant restaurantEntity = optional1.orElseThrow(()-> new FoodException("RestaurantRegisterService. RESTAURANT_DOES_NOT_EXIST"));

	List<Dish> dishEntities = restaurantEntity.getDishes(); 
	Optional <Dish> optional =dishRepository.findById(dishId);

	Dish dishEntity = optional.orElseThrow(()->new FoodException("Restaurant RegisterService.NO_DISH_FOUND"));

	dishEntities.remove(dishEntity);

	List<DishRating> deL=dishRatingRepository.findByDish(dishEntity);
	for (DishRating dishRatingEntity: deL) {

	dishRatingEntity.setDish (null);

	}

	dishRepository.delete(dishEntity);
	}
	

	@Override
	public List<DishDTO> viewDishes (Integer restaurantId) throws Exception {

	List<DishDTO> dishList = new ArrayList<>();

	Optional <Restaurant> optional1 = restaurantRepository.findById(restaurantId);

	Restaurant restaurantEntity = optional1.orElseThrow(()-> new FoodException("Restaurant RegisterService. RESTAURANT DOES_NOT_EXIST"));

	for (Dish de: restaurantEntity.getDishes()) {

	DishDTO d= new DishDTO();

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

	if(dishList.isEmpty())

	throw new FoodException("RestaurantRegisterService.NO_DISH_FOUND");

	return dishList;

	}

	@Override
	public List<RestaurantDTO> viewRestaurants (Integer userId) throws Exception {

	List<RestaurantDTO> restaurantList = new ArrayList<>();
	Optional<Users> ue = userRepository.findById(userId);

	Users user = ue.orElseThrow(()-> new FoodException("RestaurantRegisterService.NO_USER_FOUND"));

	for (Restaurant re: user.getRestaurants()) { 
		RestaurantDTO r = new RestaurantDTO();
	
	r.setRestaurantId(re.getRestaurantId());

	r.setRestaurantName (re.getRestaurantName());

	r.setRestaurantType(re.getRestaurantType());

	r.setRestaurantContact (re.getRestaurantContact());

	r.setAddressLine1 (re.getAddressLine1());

	r.setArea (re.getArea());

	r.setCity(re.getCity());

	r.setReState(re.getResState());

	r.setPincode (re.getPincode());

	r.setApprovalStatus (re.getApprovalStatus());

    r.setPincode (re.getPincode());

	r.setApprovalStatus (re.getApprovalStatus());

	String[] photos = re.getPhotoUrls().split("-"); r.setPhotoUrls(Arrays.asList(photos));

	r.setAvRating (re.getAvgRating());

	r.setDishes (null);

	restaurantList.add(r);

	}

	if (restaurantList.isEmpty())

	throw new FoodException("RestaurantRegisterService.NO_RESTAURANT_FOUND");

	

	return restaurantList;
	}
}
