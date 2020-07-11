package model;


public class Recommended {
	public static final String[] tblRecommendedTitle={"推荐商品名","菜谱编号","描述"};
	public String getCell(int col){
		if(col==0) return ""+this.getrec_commodity_name();
		else if(col==1) return ""+this.getRec_menu_id();
		else if(col==2) return ""+this.getRec_describe();
		
		else return "";
	}
	private String rec_commodity_name;
	private int rec_menu_id;
	private String rec_describe;
	
	public String getrec_commodity_name() {
		return rec_commodity_name;
	}
	public void setrec_commodity_name(String rec_commodity_name) {
		this.rec_commodity_name = rec_commodity_name;
	}
	public int getRec_menu_id() {
		return rec_menu_id;
	}
	public void setRec_menu_id(int rec_menu_id) {
		this.rec_menu_id = rec_menu_id;
	}
	public String getRec_describe() {
		return rec_describe;
	}
	public void setRec_describe(String rec_describe) {
		this.rec_describe = rec_describe;
	}

	
	
}
