package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import itl.IAdminManager;
import model.AdminInformation;
import model.CommodityInformation;
import model.Coupon;
import model.Promotion;
import model.UserInf;
import model.UserShopcar;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class AdminManager implements IAdminManager{
	
	@Override
 public void addCoupon( String coupon_content ,double coupon_price , double coupon_pricedel, String coupon_start_time,String coupon_end_time) throws BaseException{
		Connection conn=null;
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(coupon_pricedel<=0||coupon_price<=0) {
			throw new BusinessException("价格应大于0");
		}
		if("".equals(coupon_start_time) ||coupon_start_time==null) {
			throw new BaseException("开始时间不能为空");
		}
		if("".equals(coupon_end_time) ||coupon_end_time==null) {
			throw new BaseException("结束时间不能为空");
		}
		try {
			conn=DBUtil.getConnection();
			Date timeone=time.parse(coupon_start_time);
		    Date timetwo=time.parse(coupon_end_time);
	    
		String  sql="insert into coupon(coupon_content,coupon_price,coupon_pricedel,coupon_start_time,coupon_end_time)values(?,?,?,?,?)";
		 java.sql.PreparedStatement   pst=conn.prepareStatement(sql);
			 pst.setString(1,coupon_content);
			 pst.setDouble(2,coupon_price);
			 pst.setDouble(3,coupon_pricedel);
			 pst.setTimestamp(4,new java.sql.Timestamp(timeone.getTime()));
			 pst.setTimestamp(5,new java.sql.Timestamp(timetwo.getTime()));
			 pst.execute();
			 pst.close();
		} catch (SQLException | ParseException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public void deleteCoupon(Coupon coupon) throws BaseException {
		Connection conn=null;
		int couponId =coupon.getCoupon_id();
			try {
		conn=DBUtil.getConnection();
		java.sql.Statement st=conn.createStatement();
		  String sql="delete from coupon where coupon_id = "+couponId;
		  st.execute(sql);
		     st.close();
		    
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DbException(ex);
				
			}
			finally{
				if(conn!=null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}	
	}
	
	@Override
	public List<Coupon> loadCoupon()throws BaseException{
		List<Coupon> result=new ArrayList<Coupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select coupon_id,coupon_content,coupon_price,coupon_pricedel,coupon_start_time,coupon_end_time from coupon";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 Coupon p =new Coupon();
			 p.setCoupon_id(rs.getInt(1));
			 p.setCoupon_content(rs.getString(2));
			 p.setCoupon_price(rs.getDouble(3));
			 p.setCoupon_pricedel(rs.getDouble(4));
			 p.setCoupon_start_time(rs.getTimestamp(5));
			 p.setCoupon_end_time(rs.getTimestamp(6));
			 result.add(p);
		 }
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}
	
	@Override
	public AdminInformation login(String adminid, String pwd) throws BaseException {
		
		if("".equals(adminid) ||adminid==null){
			throw new BaseException("账号不能为空");
		}
		if("".equals(pwd)||pwd==null){
			throw new BaseException("密码不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_id,admin_pwd from admin_information where admin_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,adminid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("账号不存在");
			AdminInformation u=new AdminInformation();
			u.setAdmin_id(rs.getString(1));
			u.setAdmin_pwd(rs.getString(2));
			if(!pwd.equals(u.getAdmin_pwd())) throw new BaseException("密码不正确");
			rs.close();
			pst.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	@Override
	public AdminInformation changeAdminPwd(String adminid,String oldPwd,String newPwd1,String newPwd2) throws BaseException{
		if(oldPwd==null||"".equals(oldPwd)) throw new BaseException("原密码不能为空");
		if(newPwd1==null||"".equals(newPwd1)||newPwd2==null||"".equals(newPwd2))throw new BaseException("新密码不能为空");
		if(!newPwd1.equals(newPwd2)) throw new BaseException("两次密码不一样");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_pwd from admin_information where admin_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,adminid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update admin_information set admin_pwd=? where admin_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd1);
			pst.setString(2, adminid);
			pst.execute();
			pst.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
 		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}
	


}
