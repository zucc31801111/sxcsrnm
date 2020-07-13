package model;

import java.sql.Timestamp;
import java.util.Date;


public class CommodityEvaluation {
	public static final String[] tblCommodityEvaluationTitle={"内容","评价日期","评价星级","图片URL"};
     public String getCell(int col){
	if(col==0) return ""+this.getEval_content();
	else if(col==1) return ""+this.getEval_date();
	else if(col==2) return ""+this.getEval__star();
	else if(col==3) return ""+this.getEval_picture();
	else return "";
}
	private int eval_commodity_id;
	private String eval_user_id;
	private String eval_content;
	private Timestamp eval_date;
	private double eval__star;
	private String eval_picture;
	public int getEval_commodity_id() {
		return eval_commodity_id;
	}
	public void setEval_commodity_id(int eval_commodity_id) {
		this.eval_commodity_id = eval_commodity_id;
	}
	public String getEval_user_id() {
		return eval_user_id;
	}
	public void setEval_user_id(String eval_user_id) {
		this.eval_user_id = eval_user_id;
	}
	public String getEval_content() {
		return eval_content;
	}
	public void setEval_content(String eval_content) {
		this.eval_content = eval_content;
	}
	public Timestamp getEval_date() {
		return eval_date;
	}
	public void setEval_date(Timestamp eval_date) {
		this.eval_date = eval_date;
	}
	public double getEval__star() {
		return eval__star;
	}
	public void setEval__star(double eval__star) {
		this.eval__star = eval__star;
	}
	public String getEval_picture() {
		return eval_picture;
	}
	public void setEval_picture(String eval_picture) {
		this.eval_picture = eval_picture;
	}
	
}
