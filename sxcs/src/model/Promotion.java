package model;

import java.util.Date;
public class Promotion {
	public static final String[] tblPromotionTitle={"序号","商品编号","商品名称","促销价格","数量","开始时间","结束时间"};
	public String getCell(int col){
		if(col==0) return ""+this.getPromotion_id();
		else if(col==1) return ""+this.getPromotion_commodity_id();
		else if(col==2) return ""+this.getPromotion_commodity_name();
		else if(col==3) return ""+this.getPromotion_price();
		else if(col==4) return ""+this.getPromotion_sum();
		else if(col==5) return ""+this.getPromotion_start_time();
		else if(col==6) return ""+this.getPromotion_end_time();
		else return "";
	}
	public String getPromotion_commodity_name() {
		return promotion_commodity_name;
	}
	public void setPromotion_commodity_name(String promotion_commodity_name) {
		this.promotion_commodity_name = promotion_commodity_name;
	}
	private int promotion_id;
	private int promotion_commodity_id;
	private String promotion_commodity_name;
	private float promotion_price;
	private int promotion_sum;
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
	public float getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(float promotion_price) {
		this.promotion_price = promotion_price;
	}
	public int getPromotion_sum() {
		return promotion_sum;
	}
	public void setPromotion_sum(int promotion_sum) {
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
