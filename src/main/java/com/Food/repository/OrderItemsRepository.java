package com.Food.repository;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.OrderItems;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer> {

}
