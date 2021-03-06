package itl;

import java.util.List;

import model.AdminInformation;
import model.Coupon;
import util.BaseException;

public interface IAdminManager { 
	
	public AdminInformation login(String userid, String pwd) throws BaseException;
	
	public AdminInformation changeAdminPwd(String adminid,String oldPwd,String newPwd1,String newPwd2) throws BaseException;

	public List<Coupon> loadCoupon()throws BaseException;
	
	public void deleteCoupon(Coupon coupon) throws BaseException;
	
	public void addCoupon( String coupon_content , double coupon_price, double coupon_pricedel, String coupon_start_time,String coupon_end_time) throws BaseException;
	
	
}