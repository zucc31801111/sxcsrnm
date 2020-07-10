package model;


public class CommodityPurchase {
	
	public static final String[] tblPurchaseTitle={"采购序号","商品编号","采购员编号","采购数量","采购状态"};
	public String getCell(int col){
		if(col==0) return ""+this.getPurchase_id();
		else if(col==1) return ""+this.getPurchase_commodity_id();
		else if(col==2) return ""+this.getPurchase_admin_id();
		else if(col==3) return ""+this.getPurchase_number();
		else if(col==4) return ""+this.getPurchase_state();
		else return "";
	}
	private int purchase_id;
	private int purchase_commodity_id;
	private String purchase_admin_id;
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
	public String getPurchase_admin_id() {
		return purchase_admin_id;
	}
	public void setPurchase_admin_id(String purchase_admin_id) {
		this.purchase_admin_id = purchase_admin_id;
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
