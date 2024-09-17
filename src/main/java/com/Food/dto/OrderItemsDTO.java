package com.Food.dto;

public class OrderItemsDTO {

private Integer orderItemsId;

private DishDTO dish;

private Integer qty;

public Integer getOrderItemsId() { 
	return orderItemsId;

}

public void setOrderItemsId (Integer orderItemsId) { 
	this.orderItemsId = orderItemsId;

}
public DishDTO getDish() { 
	return dish;

} 
public void setDish (DishDTO dish) {
	this.dish=dish;

} 
public Integer getQty() { 
	return qty;

} 
public void setQty (Integer qty) {

this.qty= qty;

}



}