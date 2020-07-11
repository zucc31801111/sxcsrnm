package model;

import java.sql.Timestamp;
import java.util.Date;


public class DiscountCommodity {
	
	public static final String[] tblDiscountCommodityTitle={"满折序号","商品序号","开始时间","结束时间"};
	public String getCell(int col){
	if(col==0) return ""+this.getList_discount_id();
	else if(col==1) return ""+this.getList_commodity_id();
	else if(col==2) return ""+this.getList_discount_start_time();
	else if(col==3) return ""+this.getList_discount_end_time();
	else return "";
}   
	
	private int list_discount_id;
	private int list_commodity_id;
	private Timestamp list_discount_start_time;
	private Timestamp list_discount_end_time;
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
	public Timestamp getList_discount_start_time() {
		return list_discount_start_time;
	}
	public void setList_discount_start_time(Timestamp list_discount_start_time) {
		this.list_discount_start_time = list_discount_start_time;
	}
	public Timestamp getList_discount_end_time() {
		return list_discount_end_time;
	}
	public void setList_discount_end_time(Timestamp list_discount_end_time) {
		this.list_discount_end_time = list_discount_end_time;
	}
	
}
