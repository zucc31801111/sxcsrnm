package model;


public class CommodityInformation {
	public static final String[] tblCommodityTitle={"序号","名称","价格","vip价格","数量","规格","描述","销量"};
	public String getCell(int col){
		if(col==0) return ""+this.getCommodity_id();
		else if(col==1) return ""+this.getCommodity_name();
		else if(col==2) return ""+this.getCommodity_price();
		else if(col==3) return ""+this.getCommodity_vip_price();
		else if(col==4) return ""+this.getCommodity_number();
		else if(col==5) return ""+this.getCommodity_specifications();
		else if(col==6) return ""+this.getCommodity_describe();
		else if(col==7) return ""+this.getCommodity_salecount();
		else return "";
	}
	//public static CommodityInformation currentLoginUser=null;
	  private int commodity_id;
	  private String commodity_name;
	  private double commodity_price;
	  private double commodity_vip_price;
	  private int commodity_number;
	  private String commodity_specifications;
	  private String commodity_describe;
	  private int commodity_category_id;
	  private int commodity_salecount;
	  public int getCommodity_salecount() {
		return commodity_salecount;
	}
	public void setCommodity_salecount(int commodity_salecount) {
		this.commodity_salecount = commodity_salecount;
	}
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
	public double getCommodity_price() {
		return commodity_price;
	}
	public void setCommodity_price(double commodity_price) {
		this.commodity_price = commodity_price;
	}
	public double getCommodity_vip_price() {
		return commodity_vip_price;
	}
	public void setCommodity_vip_price(double commodity_vip_price) {
		this.commodity_vip_price = commodity_vip_price;
	}
	public int getCommodity_number() {
		return commodity_number;
	}
	public void setCommodity_number(int commodity_number) {
		this.commodity_number = commodity_number;
	}
	public String getCommodity_specifications() {
		return commodity_specifications;
	}
	public void setCommodity_specifications(String commodity_specifications) {
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
	
}
