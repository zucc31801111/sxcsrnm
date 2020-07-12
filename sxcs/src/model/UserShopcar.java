package model;

public class UserShopcar {
	public static final String[] tblShopcarTitle={"商品编号","商品名称","数量","价格"};
	public String getCell(int col){
		if(col==0) return ""+this.getShopcar_commodity_id();
		else if(col==1) return ""+this.getShopcar_commodity_name();
		else if(col==2) return ""+this.getShopcar_commodity_sum();
		else if(col==3) return ""+this.getShopcar_commodity_price();
		else return "";
	}
	String shopcar_user_id;
	int shopcar_commodity_id;
	String shopcar_commodity_name;
	int shopcar_commodity_sum;
	double shopcar_commodity_price;
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
	public double getShopcar_commodity_price() {
		return shopcar_commodity_price;
	}
	public void setShopcar_commodity_price(double shopcar_commodity_price) {
		this.shopcar_commodity_price = shopcar_commodity_price;
	}

	
	
}
