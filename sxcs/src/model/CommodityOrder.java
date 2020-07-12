package model;

import java.sql.Timestamp;
import java.util.Date;


public class CommodityOrder {
	
	
	public static final String[] tblCommodityOrderTitle={"订单序号","原价格","结算金额","送达时间","地址编号","配送状态","使用优惠券编号"};
	public String getCell(int col){
	if(col==0) return ""+this.getOrder_id();
	else if(col==1) return ""+this.getOrder_begin_price();
	else if(col==2) return ""+this.getOrder_end_price();
	else if(col==3) return ""+this.getOrder_gettime();
	else if(col==4) return ""+this.getOrder_delivery_id();
	else if(col==5) return ""+this.getOrder_state();
	else if(col==6) return ""+this.getOrder_coupon_id();
	else return "";
}   
	
	private int order_id;
	private String order_user_id;
	private double order_begin_price;
	private double order_end_price;
	private Timestamp order_gettime;
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
	public double getOrder_begin_price() {
		return order_begin_price;
	}
	public void setOrder_begin_price(double order_begin_price) {
		this.order_begin_price = order_begin_price;
	}
	public double getOrder_end_price() {
		return order_end_price;
	}
	public void setOrder_end_price(double order_end_price) {
		this.order_end_price = order_end_price;
	}
	public Timestamp getOrder_gettime() {
		return order_gettime;
	}
	public void setOrder_gettime(Timestamp order_gettime) {
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
