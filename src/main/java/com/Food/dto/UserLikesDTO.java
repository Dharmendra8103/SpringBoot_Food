package com.Food.dto;

public class UserLikesDTO {

	private Integer likeId;
	private String vegNonVeg;
	private DishDTO dish;
	private RestaurantDTO restaurant;
	
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
	public DishDTO getDish() {
		return dish;
	}
	public void setDish(DishDTO dish) {
		this.dish = dish;
	}
	public RestaurantDTO getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}
}
	