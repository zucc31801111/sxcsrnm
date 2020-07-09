package model;


public class CommodityPurchase {
	private int purchase_id;
	private int purchase_commodity_id;
	private int purchase_number;
	private String purchase_state;
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getPurchase_commodity_id() {
		return purchase_commodity_id;
	}
	public void setPurchase_commodity_id(int purchase_commodity_id) {
		this.purchase_commodity_id = purchase_commodity_id;
	}
	public int getPurchase_number() {
		return purchase_number;
	}
	public void setPurchase_number(int purchase_number) {
		this.purchase_number = purchase_number;
	}
	public String getPurchase_state() {
		return purchase_state;
	}
	public void setPurchase_state(String purchase_state) {
		this.purchase_state = purchase_state;
	}
	
}
