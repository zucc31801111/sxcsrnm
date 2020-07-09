package model;

import java.util.Date;


public class CommodityOrder {
	private int order_id;
	private String order_user_id;
	private float order_begin_price;
	private float order_end_price;
	private Date order_gettime;
	private int order_delivery_id;
	private String order_state;
	private int order_coupon_id;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_user_id() {
		return order_user_id;
	}
	public void setOrder_user_id(String order_user_id) {
		this.order_user_id = order_user_id;
	}
	public float getOrder_begin_price() {
		return order_begin_price;
	}
	public void setOrder_begin_price(float order_begin_price) {
		this.order_begin_price = order_begin_price;
	}
	public float getOrder_end_price() {
		return order_end_price;
	}
	public void setOrder_end_price(float order_end_price) {
		this.order_end_price = order_end_price;
	}
	public Date getOrder_gettime() {
		return order_gettime;
	}
	public void setOrder_gettime(Date order_gettime) {
		this.order_gettime = order_gettime;
	}
	public int getOrder_delivery_id() {
		return order_delivery_id;
	}
	public void setOrder_delivery_id(int order_delivery_id) {
		this.order_delivery_id = order_delivery_id;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public int getOrder_coupon_id() {
		return order_coupon_id;
	}
	public void setOrder_coupon_id(int order_coupon_id) {
		this.order_coupon_id = order_coupon_id;
	}
	
}
