package model;


public class FreshCategory {
	
	public static final String[] tblCategoryTitle={"序号","名称","描述"};
	public String getCell(int col){
	if(col==0) return ""+this.getCategory_id();
	else if(col==1) return ""+this.getCategory_name();
	else if(col==2) return ""+this.getCategory_describe();
	else return "";
}
  private int category_id;
  private String category_name;
  private String category_describe;
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public String getCategory_name() {
	return category_name;
}
public void setCategory_name(String category_name) {
	this.category_name = category_name;
}
public String getCategory_describe() {
	return category_describe;
}
public void setCategory_describe(String category_describe) {
	this.category_describe = category_describe;
}


}
