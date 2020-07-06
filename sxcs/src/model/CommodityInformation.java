package model;

public class CommodityInformation {
	  private int commodity_id;
	  private String commodity_name;
	  private int commodity_price;
	  private int commodity_vip_price;
	  public int getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(int commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getCommodity_name() {
		return commodity_name;
	}
	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}
	public int getCommodity_price() {
		return commodity_price;
	}
	public void setCommodity_price(int commodity_price) {
		this.commodity_price = commodity_price;
	}
	public int getCommodity_vip_price() {
		return commodity_vip_price;
	}
	public void setCommodity_vip_price(int commodity_vip_price) {
		this.commodity_vip_price = commodity_vip_price;
	}
	public int getCommodity_number() {
		return commodity_number;
	}
	public void setCommodity_number(int commodity_number) {
		this.commodity_number = commodity_number;
	}
	public float getCommodity_specifications() {
		return commodity_specifications;
	}
	public void setCommodity_specifications(float commodity_specifications) {
		this.commodity_specifications = commodity_specifications;
	}
	public String getCommodity_describe() {
		return commodity_describe;
	}
	public void setCommodity_describe(String commodity_describe) {
		this.commodity_describe = commodity_describe;
	}
	public int getCommodity_category_id() {
		return commodity_category_id;
	}
	public void setCommodity_category_id(int commodity_category_id) {
		this.commodity_category_id = commodity_category_id;
	}
	private int commodity_number;
	  private float commodity_specifications;
	  private String commodity_describe;
	  private int commodity_category_id;
}
