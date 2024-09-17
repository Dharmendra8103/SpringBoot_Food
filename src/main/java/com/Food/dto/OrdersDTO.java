package com.Food.dto;

import java.util.List;

public class OrdersDTO {

private Integer orderId;

private Integer orderBill;
private List<OrderItemsDTO> orderItemsList;

private String orderStatus;

public Integer getOrderId() { 
	return orderId;

}

public void setOrderId(Integer orderId) { 
	this.orderId = orderId;

}

public String getOrderStatus() {
	return orderStatus;

}
public void setOrderStatus (String orderStatus) { 
	this.orderStatus = orderStatus;

}

public Integer getOrderBill() {
	return orderBill;

}

public void setOrderBill (Integer orderBill) { 
	this.orderBill = orderBill;

} 
public List<OrderItemsDTO> getOrderItemsList() {
	return orderItemsList;

}

public void setOrderItemsList (List<OrderItemsDTO> orderItemsList) { 
	this.orderItemsList= orderItemsList;

}
}