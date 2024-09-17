package com.Food.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.Dish;


public interface DishRepository extends CrudRepository<Dish, Integer> {

	Optional<Dish> findByDishNameAndDishDescriptionAndPrice(String dishName,String dishDescription,Double price);
}
