package model;


public class DeliveryAddressList {
	public static final String[] tblAddressTitle={"地址序号","省","市","区","详细地址","收货人名","收货手机号"};
	public String getCell(int col){
		if(col==0) return ""+this.getDelivery_id();
		else if(col==1) return ""+this.getDelivery_province();
		else if(col==2) return ""+this.getDelivery_city();
		else if(col==3) return ""+this.getDelivery_area();
		else if(col==4) return ""+this.getDelivery_address();
		else if(col==5) return ""+this.getDelivery_name();
		else if(col==6) return ""+this.getDelivery_phone();
		else return "";
	}
	
	private int delivery_id;
	private String delivery_user_id;
	private String delivery_province;
	private String delivery_city;
	private String delivery_area;
	private String delivery_address;
	private String delivery_name;
	private String delivery_phone;
	public int getDelivery_id() {
		return delivery_id;
	}
	public void setDelivery_id(int delivery_id) {
		this.delivery_id = delivery_id;
	}
	public String getDelivery_user_id() {
		return delivery_user_id;
	}
	public void setDelivery_user_id(String delivery_user_id) {
		this.delivery_user_id = delivery_user_id;
	}
	public String getDelivery_province() {
		return delivery_province;
	}
	public void setDelivery_province(String delivery_province) {
		this.delivery_province = delivery_province;
	}
	public String getDelivery_city() {
		return delivery_city;
	}
	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}
	public String getDelivery_area() {
		return delivery_area;
	}
	public void setDelivery_area(String delivery_area) {
		this.delivery_area = delivery_area;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getDelivery_name() {
		return delivery_name;
	}
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	public String getDelivery_phone() {
		return delivery_phone;
	}
	public void setDelivery_phone(String delivery_phone) {
		this.delivery_phone = delivery_phone;
	}
	
}
