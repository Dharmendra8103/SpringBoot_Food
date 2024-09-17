package com.Food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Food.dto.DishDTO;
import com.Food.dto.OrderItemsDTO;
import com.Food.dto.OrdersDTO;
import com.Food.entity.Dish;
import com.Food.entity.OrderItems;
import com.Food.entity.Orders;
import com.Food.entity.Restaurant;
import com.Food.entity.Users;
import com.Food.exception.FoodException;
import com.Food.repository.DishRepository;
import com.Food.repository.OrdersRepository;
import com.Food.repository.RestaurantRepository;
import com.Food.repository.UsersRepository;

@Service(value="orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private OrdersRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	 @Override

	public List<OrdersDTO> viewOrders (Integer userId) throws Exception{

	Optional<Users> ue = userRepository.findById(userId);

	Users user = ue.orElseThrow(()-> new FoodException("orderService.NO_USER_FOUND"));

	List<OrdersDTO> orders = new ArrayList<>();
	List<Orders> ordersEntity = user.getOrdersList();

	if(!ordersEntity.isEmpty()){

	for (Orders oe: ordersEntity) {
		OrdersDTO o=new OrdersDTO(); 
		o.setOrderBill(oe.getOrderBill());
		o.setOrderId(oe.getOrderId());
		o.setOrderStatus (oe.getOrderStatus());
	

	if(!oe.getOrderItemsList().isEmpty()) {

	List <OrderItemsDTO> orderItemsList =new ArrayList<>();

	for (OrderItems oie: oe.getOrderItemsList() ) {

	OrderItemsDTO oi=new OrderItemsDTO();

	DishDTO d=new DishDTO();
	d.setDishDescription (oie.getDish().getDishDescription());

	d.setAvgRating (oie.getDish().getAvgRating());
	d.setDishCuisine (oie.getDish().getDishCuisine());

	d.setDishId(oie.getDish().getDishId());

	d.setDishName (oie.getDish().getDishName());

	d.setDishType (oie.getDish().getDishType());

	d.setPrice(oie.getDish().getPrice());

	d.setSpeciality(oie.getDish().getSpeciality());

	oi.setDish(d);

	oi.setOrderItemsId(oie.getOrderItemsId());

	oi.setQty (oie.getQty());

	orderItemsList.add(oi);

	}

	o.setOrderItemsList (orderItemsList);

	}

	orders.add(o);

	} 
	}
	if (orders.isEmpty()) {

	throw new FoodException("orderService.NO_ORDER_FOUND");

	}

	return orders;

	}

	@Override

	public Integer placeAnOrder (Integer id, OrdersDTO order) throws Exception{

	Optional<Users> ue = userRepository.findById(id); 
	Users user = ue.orElseThrow(()-> new FoodException("orderService.NO_USER_FOUND"));

	if(order==null) { 
		throw new FoodException("orderService.NO_ORDER_FOUND");
	}

	List<Orders> ordersList = user.getOrdersList(); 
	Orders orderEntity = new Orders();
	orderEntity.setOrderBill (order.getOrderBill());
	orderEntity.setOrderStatus (order.getOrderStatus());
	List <OrderItemsDTO> orderItems = order.getOrderItemsList();
	List<OrderItems> orderItemsEntityList = new ArrayList<>();
	orderEntity.setOrderItemsList (orderItemsEntityList); 
	OrderItems orderItemsEntity=null;
	Dish dishEntity=null;
	DishDTO dish =null;

	for (OrderItemsDTO orderItem: orderItems) { 
		orderItemsEntity = new OrderItems();
		dish =orderItem.getDish();
	

	dishEntity = dishRepository.findById(dish.getDishId()).get();

	orderItemsEntity.setDish (dishEntity);
	orderItemsEntity.setQty (orderItem.getQty());
	orderItemsEntityList.add(orderItemsEntity);
	}
	
	orderEntity = orderRepository.save(orderEntity);
	orderEntity.setOrderItemsList (orderItemsEntityList); 
	ordersList.add(orderEntity); 
	user.setOrdersList (ordersList); 
	Integer orderID = orderEntity.getOrderId();
	return orderID;
	}
	

	@Override
	public List<String> restaurantNames (List<Integer> dishid) {

	Iterable<Restaurant> allRestaurants = restaurantRepository.findAll();
	List<String> rnames=new ArrayList<>(); 
	
	for (Integer id:dishid) { 
	for(Restaurant r: allRestaurants) { 
		for(Dish d: r.getDishes()) {
		if(d.getDishId().equals(id)) { 
			rnames.add(r.getRestaurantName());
		}
	}
	}
	}
	return rnames;
	}
}
