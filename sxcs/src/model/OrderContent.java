package model;


public class OrderContent {
	
	public static final String[] tblOrderContentTitle={"商品编号","商品数量","商品金额","折扣","满折编号"};
	public String getCell(int col){
	if(col==0) return ""+this.getContent_commodity_id();
	else if(col==1) return ""+this.getOrder_number();
	else if(col==2) return ""+this.getOrder_price();
	else if(col==3) return ""+this.getContent_discount_sum();
	else if(col==4) return ""+this.getContent_discount_id();
	else return "";
}   
	
	
	private int content_order_id;
	private int content_commodity_id;
	private int order_number;
	private double order_price;
	private double content_discount_sum;
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
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public double getContent_discount_sum() {
		return content_discount_sum;
	}
	public void setContent_discount_sum(double content_discount_sum) {
		this.content_discount_sum = content_discount_sum;
	}
	public int getContent_discount_id() {
		return content_discount_id;
	}
	public void setContent_discount_id(int content_discount_id) {
		this.content_discount_id = content_discount_id;
	}
	
}
