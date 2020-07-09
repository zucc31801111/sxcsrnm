package model;

import java.util.Date;


public class DiscountInformation {
	private int discount_id;
	private String discount_content;
	private int discount_number;
	private int discount_sum;
	private Date discount_start_time;
	private Date discount_end_time;
	public int getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
	}
	public String getDiscount_content() {
		return discount_content;
	}
	public void setDiscount_content(String discount_content) {
		this.discount_content = discount_content;
	}
	public int getDiscount_number() {
		return discount_number;
	}
	public void setDiscount_number(int discount_number) {
		this.discount_number = discount_number;
	}
	public int getDiscount_sum() {
		return discount_sum;
	}
	public void setDiscount_sum(int discount_sum) {
		this.discount_sum = discount_sum;
	}
	public Date getDiscount_start_time() {
		return discount_start_time;
	}
	public void setDiscount_start_time(Date discount_start_time) {
		this.discount_start_time = discount_start_time;
	}
	public Date getDiscount_end_time() {
		return discount_end_time;
	}
	public void setDiscount_end_time(Date discount_end_time) {
		this.discount_end_time = discount_end_time;
	}
}
