package model;


public class Menu {
	public static final String[] tblMenuTitle={"菜谱编号","推荐菜谱名","菜谱材料","菜谱步骤","图片"};
	public String getCell(int col){
		if(col==0) return ""+this.getMenu_id();
		else if(col==1) return ""+this.getMenu_name();
		else if(col==2) return ""+this.getMenu_materials();
		else if(col==3) return ""+this.getMenu_step();
		else if(col==4) return ""+this.getMenu_picture();
		else return "";
	}
	
	private int menu_id;
	private String menu_name;
	private String menu_materials;
	private String menu_step;
	private String menu_picture;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_materials() {
		return menu_materials;
	}
	public void setMenu_materials(String menu_materials) {
		this.menu_materials = menu_materials;
	}
	public String getMenu_step() {
		return menu_step;
	}
	public void setMenu_step(String menu_step) {
		this.menu_step = menu_step;
	}
	public String getMenu_picture() {
		return menu_picture;
	}
	public void setMenu_picture(String menu_picture) {
		this.menu_picture = menu_picture;
	}
}
