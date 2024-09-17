package com.Food.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UsersDTO {

	private Integer userId;
	@NotBlank(message="{User.INVALID_USERNAME_FORMAT}")
	@Pattern(regexp= "[A-Za-z]+([A-Za-Z]+)*", message="{User.INVALID_USERNAME_FORMAT}")
	private String userName;
	@NotBlank(message="{User.INVALID_CONTACT_NUMBER_FORMAT}")
	@Pattern(regexp= "[6-9][0-9]{9}", message="{User.INVALID_CONTACT_NUMBER_FORMAT}")
	private String contactNumber;
	@NotBlank(message="{User.INVALID_EMAILID_FORMAT}")
	@Pattern(regexp= "[A-Za-z]+[0-9]*[A-Za-z]*@[A-Za-z]+[.](com|in)", message="{User.INVALID_EMAILID_FORMAT}")
	private String emailId;
	@NotBlank(message="{User.PASSWORD_ABSENT}")
	@Pattern(regexp= ".*[A-Z]+.*", message="{User.INVALID_PASSWORD_FORMAT1}")
	@Pattern(regexp= ".*[a-z]+.*", message="{User.INVALID_PASSWORD_FORMAT2}")
	@Pattern(regexp= ".*[0-9]+.*", message="{User.INVALID_PASSWORD_FORMAT3}")
	@Pattern(regexp= ".*[!@#$%^&*]+.*", message="{User.INVALID_PASSWORD_FORMAT4}")
	private String password;
	@NotEmpty(message="{User.INVALID_ROLE_TYPE}")
	private List<RolesDTO> roles;
	private List<RestaurantDTO> restaurants;
	private List<UserAddressDTO> addressList;
	private WalletDTO wallet;
	private List<UserLikesDTO> userLikesList;
	private List<OrdersDTO> ordersList;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RolesDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesDTO> roles) {
		this.roles = roles;
	}
	public List<RestaurantDTO> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantDTO> restaurants) {
		this.restaurants = restaurants;
	}
	public List<UserAddressDTO> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<UserAddressDTO> addressList) {
		this.addressList = addressList;
	}
	public WalletDTO getWallet() {
		return wallet;
	}
	public void setWallet(WalletDTO wallet) {
		this.wallet = wallet;
	}
	public List<UserLikesDTO> getUserLikesList() {
		return userLikesList;
	}
	public void setUserLikesList(List<UserLikesDTO> userLikesList) {
		this.userLikesList = userLikesList;
	}
	public List<OrdersDTO> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<OrdersDTO> ordersList) {
		this.ordersList = ordersList;
	}
	
}
