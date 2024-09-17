package com.Food.dto;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class DishDTO {

private Integer dishId;

@NotBlank(message = "{Dish. INVALID_DISH_NAME}")
@Pattern(regexp = "([A-Za-z])+(\\s[A-Za-z]+)", message = "{Dish. INVALID_DISH_NAME}")
private String dishName;

 @Size(min =4, message ="{Dish. INVALID DISH_CUISINE}")
private String dishCuisine;

@NotBlank (message = "{Dish.INVALID_DISH_TYPE}")
@Pattern(regexp = "(Veg | Nonveg | Jain | Egg|Any)", message = "{Dish. INVALID_DISH_TYPE}")
private String dishType;

@NotBlank (message = "{Dish. INVALID_DISH_DESCRIPTION}")
@Size (min = 3, message = "{Dish. INVALID_DISH_DESCRIPTION}") 
private String dishDescription;

@Min(value = (long) 0.0, message = "{Dish. INVALID_DISH_PRICE}")
private Double price;

private Double avgRating;

@Size (min = 3, message = "{Dish. INVALID_DISH_SPECIALTY}")
private String speciality;

private String imageUrl;

public Integer getDishId() {

return dishId;

}

public void setDishId (Integer dishId) { 
	this.dishId=dishId;
}

public String getDishName() { 
	return dishName;
}

public void setDishName (String dishName) { 
	this.dishName = dishName;
}

public String getDishCuisine () { 
	return dishCuisine;
}

public void setDishCuisine (String dishCuisine) { 
	this.dishCuisine = dishCuisine;
}

public String getDishType() { 
	return dishType;
}

 public void setDishType(String dishType) { 
	 this.dishType = dishType;
 }



public String getDishDescription() { 
	return dishDescription;
}



public void setDishDescription(String dishDescription) { 
	this.dishDescription = dishDescription;
}

public Double getPrice() {
	return price;
}

public void setPrice (Double price) { 
	this.price= price;
}

 public Double getAvgRating() {
	 return avgRating;
 }

 public void setAvgRating (Double avgRating) { 
	 this.avgRating= avgRating;
 }

public String getSpeciality() { 
	return speciality;
}

 public void setSpeciality(String speciality) {
	 this.speciality= speciality;
 }

 public String getImageUrl() { 
	 return imageUrl;
 }

 public void setImageUrl(String imageUrl) { 
	 this.imageUrl = imageUrl;
 }

}