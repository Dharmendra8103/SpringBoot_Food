package com.Food.dto;

import java.util.List;

public class RestaurantDTO {

	private Integer restaurantId;
	private String restaurantName;
	private String restaurantContact;
	private String restaurantType;
	private String addressLine1;
	private String area;
	private String city;
	private String reState;
	private Integer pincode;
	private String approvalStatus;
	private double avRating;
	private List<DishDTO> dishes;
	private List<String> photoUrls;
	private RestaurantTransactionDTO transaction;
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantContact() {
		return restaurantContact;
	}
	public void setRestaurantContact(String restaurantContact) {
		this.restaurantContact = restaurantContact;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getReState() {
		return reState;
	}
	public void setReState(String reState) {
		this.reState = reState;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public double getAvRating() {
		return avRating;
	}
	public void setAvRating(double avRating) {
		this.avRating = avRating;
	}
	public List<DishDTO> getDishes() {
		return dishes;
	}
	public void setDishes(List<DishDTO> dishes) {
		this.dishes = dishes;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public RestaurantTransactionDTO getTransaction() {
		return transaction;
	}
	public void setTransaction(RestaurantTransactionDTO transaction) {
		this.transaction = transaction;
	}
	
}
