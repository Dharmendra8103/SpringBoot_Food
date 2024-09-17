package com.Food.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Food.dto.DishDTO;
import com.Food.dto.OrderItemsDTO;
import com.Food.dto.OrdersDTO;
import com.Food.dto.Role;
import com.Food.dto.RolesDTO;
import com.Food.dto.UserAddressDTO;
import com.Food.dto.UsersDTO;
import com.Food.entity.Dish;
import com.Food.entity.OrderItems;
import com.Food.entity.Orders;
import com.Food.entity.Roles;
import com.Food.entity.UserAddress;
import com.Food.entity.Users;
import com.Food.exception.FoodException;
import com.Food.repository.UserAddressRepository;
import com.Food.repository.UsersRepository;
import com.Food.utility.HashingUtility;

@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired

	private UserAddressRepository userAddressRepository;

	@Override
	public UsersDTO authenticateUser(String contactNumber, String password) throws FoodException, NoSuchAlgorithmException {

	UsersDTO user = null;

	List<Users> userEntities = userRepository.findByContactNumber(contactNumber);
	if (userEntities!=null && !userEntities.isEmpty()) {

	Users userEntity =userEntities.get(0);
	user = new UsersDTO();
	user.setUserId(userEntity.getUserId());
	user.setEmailId(userEntity.getEmailId()); 
	user.setContactNumber(userEntity.getContactNumber());
	user.setUserName (userEntity.getUserName());
	user.setPassword(userEntity.getPassword());

	List<RolesDTO> roles=new ArrayList<>();
	for (Roles re: userEntity.getRoles()) {

	RolesDTO r=new RolesDTO();
    r.setRoleId(re.getRoleId());
	r.setRoleType(re.getRoleType());

	roles.add(r);

	}

	user.setRoles (roles);

	List<UserAddressDTO> userAddressList = new ArrayList<UserAddressDTO>();
	for (UserAddress uae: userEntity.getAddressList()) { 
		UserAddressDTO userAddress = new UserAddressDTO();

	userAddress.setAddressLine1 (uae.getAddressLine1());

	userAddress.setAddressLine2 (uae.getAddressLine2());

	userAddress.setArea (uae.getArea());

	userAddress.setCity (uae.getCity());

	userAddress.setPincode (uae.getPincode()); 
	userAddress.setUserAddressId(uae.getUserAddressId()); 
	userAddress.setUserAddressName(uae.getUserAddressName());

	userAddress.setUserState(uae.getUserState());

	userAddressList.add(userAddress);

	}

	user.setAddressList (userAddressList);

	List <OrdersDTO> orderList = new ArrayList<OrdersDTO>(); 
	for (Orders oe: userEntity.getOrdersList()) {

	OrdersDTO orders = new OrdersDTO();

	List <OrderItemsDTO> orderItemsList = new ArrayList<OrderItemsDTO>();

	for (OrderItems oie: oe.getOrderItemsList()) { 
		OrderItemsDTO orderItems = new OrderItemsDTO();

	Dish dishEntity =new Dish();

	dishEntity =oie.getDish();

	DishDTO dish = new DishDTO(); 
	dish.setDishCuisine (dishEntity.getDishCuisine());
	orderItems.setDish (dish);
	orderItemsList.add(orderItems);
	}

	orders.setOrderItemsList (orderItemsList);
	}
	user.setOrdersList(orderList);

	}

	if (user == null)

	throw new FoodException("UserService.INVALID_CREDENTIALS"); 
	String passwordFromDB= user.getPassword();

	if (passwordFromDB != null) {

	String hashedPassword = HashingUtility.getHashValue (password);

	if (hashedPassword.equals(passwordFromDB)) { 
		user.setPassword(null);
		return user;
	}
	 else

	throw new FoodException("UserService.INVALID_CREDENTIALS");

	} else
		throw new FoodException("UserService.INVALID_CREDENTIALS");

	}
	

	@Override
	public String registerNewUser (UsersDTO user) throws FoodException, NoSuchAlgorithmException{ 
		
		Role roleType = user.getRoles().get(0).getRoleType();

	if (roleType==null || !(roleType.equals (Role. CUSTOMER) | roleType.equals (Role. VENDOR) | roleType.equals (Role. ADMIN))) { 
		throw new FoodException("UserValidator.INVALID_ROLE_TYPE");
	}

	List<RolesDTO> roles= user.getRoles(); 
	Users userFromDB = null; 
	List<Users> userEntities = userRepository.findByContactNumber (user.getContactNumber()); 
	if (userEntities!=null && userEntities.isEmpty()) { 
		userFromDB = userEntities.get(0);
	}

	Integer checkUser;

	if(userFromDB == null) {

	checkUser = 0;

	}

	else {

	checkUser = userFromDB.getUserId();

	} if(userFromDB!=null) {

	for (Roles r: userFromDB.getRoles()) { 
		if (r.getRoleType().equals (roles.get(0).getRoleType())) {
			throw new FoodException("UserService.USER_ALREADY_EXISTS");
		}
	}
}

	String hashedPassword =HashingUtility.getHashValue(user.getPassword());
	user.setPassword (hashedPassword);

	Optional <Users> optional = userRepository.findById(checkUser); 
	if (optional.isEmpty()) {

	Users userEntity = new Users();
	userEntity.setUserName (user.getUserName()); 
	userEntity.setEmailId(user.getEmailId());
	userEntity.setContactNumber(user.getContactNumber()); 
	userEntity.setPassword(user.getPassword());
	List<Roles> rolesList = new ArrayList<Roles>(); 
	
	for (RolesDTO r: user.getRoles()) {
		Roles roleEntity=new Roles();
		roleEntity.setRoleType(r.getRoleType()); 
		rolesList.add(roleEntity);
	}

	

	userEntity.setRoles(rolesList);

	UserAddress userAddressEntity = new UserAddress(); 
	UserAddressDTO address= user.getAddressList().get(0);

	userAddressEntity.setAddressLine1 (address.getAddressLine1()); 
	userAddressEntity.setAddressLine2 (address.getAddressLine2());
	userAddressEntity.setArea (address.getArea()); 
	userAddressEntity.setCity (address.getCity()); 
	userAddressEntity.setPincode (address.getPincode());
	userAddressEntity.setUserAddressName (address.getUserAddressName());
	userAddressEntity.setUserState (address.getUserState());

	List<UserAddress> addressEntityList = new ArrayList<UserAddress>();

	addressEntityList.add(userAddressEntity); 
	userEntity.setAddressList(addressEntityList); 
	userRepository.save(userEntity); 
	return userEntity.getUserName();

	}else {

	Users ue = optional.get();
	List<Roles> rolesList= ue.getRoles();
	
	for (RolesDTO r: user.getRoles()) { 
		Roles roleEntity=new Roles();
		roleEntity.setRoleType(r.getRoleType());
		rolesList.add(roleEntity);
	}

     ue.setRoles (rolesList);
    if(user.getAddressList()!=null && !user.getAddressList().isEmpty()) {
    	addNewAddress(user.getAddressList().get(0), ue.getUserId());
    }
	return ue.getUserName();
	}
	}

	@Override
	public Integer addNewAddress (UserAddressDTO address, Integer userId) throws FoodException {

	Optional<Users> optional = userRepository.findById(userId);

	Users user = optional.get();

	for (UserAddress ue: user.getAddressList()) {

	if (ue.getUserAddressName().equalsIgnoreCase(address.getUserAddressName())) {

	throw new FoodException("UserService.ADDRESS_NAME_ALREADY_EXISTS");

	}
	}

	UserAddress userAddress = new UserAddress();

	userAddress.setAddressLine1 (address.getAddressLine1());
	userAddress.setAddressLine2 (address.getAddressLine2()); 
	userAddress.setArea (address.getArea()); 
	userAddress.setCity (address.getCity()); 
	userAddress.setPincode (address.getPincode());
	userAddress.setUserAddressName (address.getUserAddressName());
	userAddress.setUserState (address.getUserState()); 
	userAddressRepository.save(userAddress);
	List<UserAddress> addressEntityList =user.getAddressList();

	addressEntityList.add(userAddress);

	return userAddress.getUserAddressId();
	}
	

	@Override
	public String updateAddress(UserAddressDTO address, Integer userId) throws FoodException {

	Optional<UserAddress> optional = userAddressRepository.findById(address.getUserAddressId());
	UserAddress userAddressEntity = optional.orElseThrow(()->new FoodException("UserService.ADDRESS_NOT_FOUND"));

	userAddressEntity.setAddressLine1 (address.getAddressLine1()); 
	userAddressEntity.setAddressLine2(address.getAddressLine2());
	userAddressEntity.setArea(address.getArea()); 
	userAddressEntity.setCity (address.getCity()); 
	userAddressEntity.setPincode (address.getPincode()); 
	userAddressEntity.setUserAddressName (address.getUserAddressName());
	userAddressEntity.setUserState (address.getUserState());
	return "UserService.UPDATE_ADDRESS";
	}

	@Override
	public void deleteAddress(Integer addressId, Integer userId) throws FoodException {

	Optional<Users> ue = userRepository.findById(userId);
	Users user = ue.get();
	List<UserAddress> userAddressList = user.getAddressList(); 
	Optional <UserAddress> uae = userAddressRepository.findById(addressId);
	UserAddress userAddress = uae.get(); 
	userAddressList.remove(userAddress); 
	userAddressRepository.delete(userAddress);
	}
}
