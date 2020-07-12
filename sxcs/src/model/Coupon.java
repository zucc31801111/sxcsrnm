package model;

import java.sql.Timestamp;
import java.util.Date;


public class Coupon {
	public static final String[] tblCouponTitle={"优惠券编号","优惠券内容","要求达到金额","优惠金额","开始时间","结束时间"};
	public String getCell(int col){
		if(col==0) return ""+this.getCoupon_id();
		else if(col==1) return ""+this.getCoupon_content();
		else if(col==2) return ""+this.getCoupon_price();
		else if(col==3) return ""+this.getCoupon_pricedel();
		else if(col==4) return ""+this.getCoupon_start_time();
		else if(col==5) return ""+this.getCoupon_end_time();
		else return "";
	}
	private int coupon_id;
	private String coupon_content;
	private double coupon_price;
	private double coupon_pricedel;
	private Timestamp coupon_start_time;
	private Timestamp coupon_end_time;
	public double getCoupon_pricedel() {
		return coupon_pricedel;
	}
	public void setCoupon_pricedel(double coupon_pricedel) {
		this.coupon_pricedel = coupon_pricedel;
	}
	public double getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(double coupon_price) {
		this.coupon_price = coupon_price;
	}
	
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
	
	public Timestamp getCoupon_start_time() {
		return coupon_start_time;
	}
	public void setCoupon_start_time(Timestamp coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}
	public Timestamp getCoupon_end_time() {
		return coupon_end_time;
	}
	public void setCoupon_end_time(Timestamp coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}
}
