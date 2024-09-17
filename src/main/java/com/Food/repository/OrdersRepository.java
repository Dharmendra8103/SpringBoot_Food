package com.Food.repository;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.Orders;



public interface OrdersRepository extends CrudRepository<Orders, Integer>{

}
