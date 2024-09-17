package com.Food.repository;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.UserAddress;



public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {

}
