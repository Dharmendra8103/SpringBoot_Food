package com.Food.dto;

public class DishRatingDTO {

private Integer dishRatingId; private DishDTO dish;

private UsersDTO user;

private Integer rating;

public Integer getDishRatingId() { 
	return dishRatingId;

}

public void setDishRatingId (Integer dishRatingId) { 
	this.dishRatingId = dishRatingId;

}

public DishDTO getDish() {
	return dish;

} 
public void setDish (DishDTO dish) { 
	this.dish =dish;

}

public UsersDTO getUser() { 
	return user;

} 

public void setUser(UsersDTO user) { 
	this.user = user;

} 
public Integer getRating() {

return rating;

} 
public void setRating (Integer rating) { 
	this.rating =rating;
}
}