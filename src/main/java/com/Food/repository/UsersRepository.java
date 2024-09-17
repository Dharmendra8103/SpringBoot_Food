package com.Food.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.Users;



public interface UsersRepository extends CrudRepository<Users, Integer> {

	List<Users> findByContactNumber(String contactNumber);
}
