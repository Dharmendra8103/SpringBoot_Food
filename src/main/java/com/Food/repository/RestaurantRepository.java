package com.Food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.Restaurant;



public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

	List<Restaurant> findByArea(String area);
	Optional<Restaurant> findByRestaurantNameAndRestaurantContact(String restaurantName, String restaurantContact);
}
