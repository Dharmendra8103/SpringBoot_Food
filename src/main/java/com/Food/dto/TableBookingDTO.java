package com.Food.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class TableBookingDTO {

	
	private Integer bookingId;
	private Date bookingDate;
	private LocalDateTime timeOfBooking;
	private Integer noOfPeople;
	private RestaurantDTO restaurant;
	private UsersDTO user;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public RestaurantDTO getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}
	public UsersDTO getUser() {
		return user;
	}
	public void setUser(UsersDTO user) {
		this.user = user;
	}
	
}
