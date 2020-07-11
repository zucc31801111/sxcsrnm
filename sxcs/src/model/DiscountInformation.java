package model;

import java.sql.Timestamp;
import java.util.Date;


public class DiscountInformation {
	
	public static final String[] tblDiscountInformationTitle={"满折序号","满折内容","适用数量","折扣","开始时间","结束时间"};
	public String getCell(int col){
	if(col==0) return ""+this.getDiscount_id();
	else if(col==1) return ""+this.getDiscount_content();
	else if(col==2) return ""+this.getDiscount_number();
	else if(col==3) return ""+this.getDiscount_sum();
	else if(col==4) return ""+this.getDiscount_start_time();
	else if(col==5) return ""+this.getDiscount_end_time();
	else return "";
}    
	
	private int discount_id;
	private String discount_content;
	private int discount_number;
	private float discount_sum;
	private Timestamp discount_start_time;
	private Timestamp discount_end_time;
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
	public float getDiscount_sum() {
		return discount_sum;
	}
	public void setDiscount_sum(float discount_sum) {
		this.discount_sum = discount_sum;
	}
	public Timestamp getDiscount_start_time() {
		return discount_start_time;
	}
	public void setDiscount_start_time(Timestamp discount_start_time) {
		this.discount_start_time = discount_start_time;
	}
	public Timestamp getDiscount_end_time() {
		return discount_end_time;
	}
	public void setDiscount_end_time(Timestamp discount_end_time) {
		this.discount_end_time = discount_end_time;
	}
}
