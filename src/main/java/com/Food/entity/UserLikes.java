package com.Food.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_likes")
public class UserLikes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer likeId;
	private String vegNonVeg;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dish_id",unique=true)
	private Dish dish;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="restaurant_id",unique=true)
	private Restaurant restaurant;
	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public String getVegNonVeg() {
		return vegNonVeg;
	}
	public void setVegNonVeg(String vegNonVeg) {
		this.vegNonVeg = vegNonVeg;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
