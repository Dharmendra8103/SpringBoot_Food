package com.Food.repository;

import org.springframework.data.repository.CrudRepository;

import com.Food.entity.UserLikes;



public interface UserLikesRepository extends CrudRepository<UserLikes, Integer> {

}
