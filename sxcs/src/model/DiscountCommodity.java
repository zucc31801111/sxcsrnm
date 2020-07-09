package model;

import java.util.Date;


public class DiscountCommodity {
	private int list_discount_id;
	private int list_commodity_id;
	private Date list_discount_start_time;
	private Date list_discount_end_time;
	public int getList_discount_id() {
		return list_discount_id;
	}
	public void setList_discount_id(int list_discount_id) {
		this.list_discount_id = list_discount_id;
	}
	public int getList_commodity_id() {
		return list_commodity_id;
	}
	public void setList_commodity_id(int list_commodity_id) {
		this.list_commodity_id = list_commodity_id;
	}
	public Date getList_discount_start_time() {
		return list_discount_start_time;
	}
	public void setList_discount_start_time(Date list_discount_start_time) {
		this.list_discount_start_time = list_discount_start_time;
	}
	public Date getList_discount_end_time() {
		return list_discount_end_time;
	}
	public void setList_discount_end_time(Date list_discount_end_time) {
		this.list_discount_end_time = list_discount_end_time;
	}
	
}
