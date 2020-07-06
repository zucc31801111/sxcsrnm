package model;

import java.util.Date;

public class CommodityEvaluation {
	private int eval_commodity_id;
	private String eval_user_id;
	private String eval_content;
	private Date eval_date;
	private int eval__star;
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
	public Date getEval_date() {
		return eval_date;
	}
	public void setEval_date(Date eval_date) {
		this.eval_date = eval_date;
	}
	public int getEval__star() {
		return eval__star;
	}
	public void setEval__star(int eval__star) {
		this.eval__star = eval__star;
	}
	public String getEval_picture() {
		return eval_picture;
	}
	public void setEval_picture(String eval_picture) {
		this.eval_picture = eval_picture;
	}
	
}
