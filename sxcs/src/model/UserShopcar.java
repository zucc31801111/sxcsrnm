package model;

public class UserShopcar {
	public static final String[] tblShopcarTitle={"商品编号","商品名称","数量","价格","vip价格"};
	public String getCell(int col){
		if(col==0) return ""+this.getShopcar_commodity_id();
		else if(col==1) return ""+this.getShopcar_commodity_name();
		else if(col==2) return ""+this.getShopcar_commodity_sum();
		else if(col==3) return ""+this.getShopcar_commodity_price();
		else if(col==4) return ""+this.getShopcar_commodity_vipprice();
		else return "";
	}
	String shopcar_user_id;
	int shopcar_commodity_id;
	String shopcar_commodity_name;
	int shopcar_commodity_sum;
	float shopcar_commodity_price;
	float shopcar_commodity_vipprice;
	public String getShopcar_user_id() {
		return shopcar_user_id;
	}
	public void setShopcar_user_id(String shopcar_user_id) {
		this.shopcar_user_id = shopcar_user_id;
	}
	public int getShopcar_commodity_id() {
		return shopcar_commodity_id;
	}
	public void setShopcar_commodity_id(int shopcar_commodity_id) {
		this.shopcar_commodity_id = shopcar_commodity_id;
	}
	public String getShopcar_commodity_name() {
		return shopcar_commodity_name;
	}
	public void setShopcar_commodity_name(String shopcar_commodity_name) {
		this.shopcar_commodity_name = shopcar_commodity_name;
	}
	public int getShopcar_commodity_sum() {
		return shopcar_commodity_sum;
	}
	public void setShopcar_commodity_sum(int shopcar_commodity_sum) {
		this.shopcar_commodity_sum = shopcar_commodity_sum;
	}
	public float getShopcar_commodity_price() {
		return shopcar_commodity_price;
	}
	public void setShopcar_commodity_price(float shopcar_commodity_price) {
		this.shopcar_commodity_price = shopcar_commodity_price;
	}
	public float getShopcar_commodity_vipprice() {
		return shopcar_commodity_vipprice;
	}
	public void setShopcar_commodity_vipprice(float shopcar_commodity_vipprice) {
		this.shopcar_commodity_vipprice = shopcar_commodity_vipprice;
	}
	
	
}
