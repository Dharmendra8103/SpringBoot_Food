package com.Food.repository;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.RestaurantTransaction;


public interface RestaurantTransactionRepository extends CrudRepository<RestaurantTransaction, Integer>{

}
