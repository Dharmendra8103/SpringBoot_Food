package com.Food.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.Dish;
import com.Food.entity.DishRating;

public interface DishRatingRepository extends CrudRepository<DishRating, Integer> {

	List<DishRating> findByDish(Dish dish);
}
