package model;

import java.util.Date;


public class UserInf {
	public static UserInf currentLoginUser=null;
	private String user_id;
	private String user_name;
	private String user_sex;
	private String user_pwd;
	private String user_phone;
	private String user_mail;
	private String user_city;
	private Date user_registration_time;
	private Boolean user_vip;
	private Date user_vip_end_time;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getUser_registration_time() {
		return user_registration_time;
	}
	public void setUser_registration_time(Date user_registration_time) {
		this.user_registration_time = user_registration_time;
	}
	public Boolean getUser_vip() {
		return user_vip;
	}
	public void setUser_vip(Boolean user_vip) {
		this.user_vip = user_vip;
	}
	public Date getUser_vip_end_time() {
		return user_vip_end_time;
	}
	public void setUser_vip_end_time(Date user_vip_end_time) {
		this.user_vip_end_time = user_vip_end_time;
	}
	
}