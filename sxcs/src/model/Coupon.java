package model;

import java.util.Date;

public class Coupon {
	private int coupon_id;
	private String coupon_content;
	private int coupon_amount;
	private Date coupon_start_time;
	private Date coupon_end_time;
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_content() {
		return coupon_content;
	}
	public void setCoupon_content(String coupon_content) {
		this.coupon_content = coupon_content;
	}
	public int getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(int coupon_amount) {
		this.coupon_amount = coupon_amount;
	}
	public Date getCoupon_start_time() {
		return coupon_start_time;
	}
	public void setCoupon_start_time(Date coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}
	public Date getCoupon_end_time() {
		return coupon_end_time;
	}
	public void setCoupon_end_time(Date coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}
}
