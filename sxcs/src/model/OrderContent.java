package model;


public class OrderContent {
	private int content_order_id;
	private int content_commodity_id;
	private int order_number;
	private float order_price;
	private int content_discount_sum;
	private int content_discount_id;
	public int getContent_order_id() {
		return content_order_id;
	}
	public void setContent_order_id(int content_order_id) {
		this.content_order_id = content_order_id;
	}
	public int getContent_commodity_id() {
		return content_commodity_id;
	}
	public void setContent_commodity_id(int content_commodity_id) {
		this.content_commodity_id = content_commodity_id;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public float getOrder_price() {
		return order_price;
	}
	public void setOrder_price(float order_price) {
		this.order_price = order_price;
	}
	public int getContent_discount_sum() {
		return content_discount_sum;
	}
	public void setContent_discount_sum(int content_discount_sum) {
		this.content_discount_sum = content_discount_sum;
	}
	public int getContent_discount_id() {
		return content_discount_id;
	}
	public void setContent_discount_id(int content_discount_id) {
		this.content_discount_id = content_discount_id;
	}
	
}
