package model;

import java.util.Date;

public class Promotion {
	private int promotion_id;
	private int promotion_commodity_id;
	private int promotion_price;
	private float promotion_sum;
	private Date promotion_start_time;
	private Date promotion_end_time;
	public int getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}
	public int getPromotion_commodity_id() {
		return promotion_commodity_id;
	}
	public void setPromotion_commodity_id(int promotion_commodity_id) {
		this.promotion_commodity_id = promotion_commodity_id;
	}
	public int getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(int promotion_price) {
		this.promotion_price = promotion_price;
	}
	public float getPromotion_sum() {
		return promotion_sum;
	}
	public void setPromotion_sum(float promotion_sum) {
		this.promotion_sum = promotion_sum;
	}
	public Date getPromotion_start_time() {
		return promotion_start_time;
	}
	public void setPromotion_start_time(Date promotion_start_time) {
		this.promotion_start_time = promotion_start_time;
	}
	public Date getPromotion_end_time() {
		return promotion_end_time;
	}
	public void setPromotion_end_time(Date promotion_end_time) {
		this.promotion_end_time = promotion_end_time;
	}
	
}
