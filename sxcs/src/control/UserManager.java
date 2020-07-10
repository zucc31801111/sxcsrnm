package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import itl.IUserManager;
import model.CommodityInformation;
import model.DeliveryAddressList;
import model.FreshCategory;
import model.Promotion;
import model.UserInf;
import model.UserShopcar;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class UserManager implements IUserManager{
	
	@Override
	public UserShopcar addUserShopcar(int sum,CommodityInformation commodity)throws BaseException{
		if(sum<0 ){
			throw new BusinessException("数量应大于0");
		}
		if(commodity.getCommodity_number()<sum) {
				throw new BusinessException("选购数量大于库存");
			}
		int allsum=sum;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			
			String  sql="select sum(shopcar_commodity_sum) from user_shopcar where shopcar_commodity_id = ? and shopcar_user_id=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,commodity.getCommodity_id());
		pst.setString(2,UserInf.currentLoginUser.getUser_id());
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			allsum=rs.getInt(1)+sum;
			rs.close();
			pst.close();
			if(commodity.getCommodity_number()<allsum) {
				throw new BusinessException("选购数量大于库存");
			}
			sql="update user_shopcar set shopcar_commodity_sum = ? where shopcar_commodity_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,allsum);
			pst.setInt(2,commodity.getCommodity_id());
			pst.execute();
			pst.close();
		}
		else {
			rs.close();
			pst.close();
			sql="insert into user_shopcar(shopcar_user_id,shopcar_commodity_id,shopcar_commodity_name,shopcar_commodity_sum,shopcar_commodity_price,shopcar_commodity_vipprice) "
					+ "values(?,?,?,?,?,?)";
		    pst=conn.prepareStatement(sql);
			pst.setString(1, UserInf.currentLoginUser.getUser_id());
			pst.setInt(2, commodity.getCommodity_id());
			pst.setString(3, commodity.getCommodity_name());
			pst.setInt(4, allsum);
			pst.setFloat(5,commodity.getCommodity_price());
			pst.setFloat(6, commodity.getCommodity_vip_price());
			pst.execute();
			pst.close();
		}
		    
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
	@Override
	public List<UserShopcar> loadShopcar()throws BaseException{
		List<UserShopcar> result=new ArrayList<UserShopcar>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shopcar_commodity_id,shopcar_commodity_name,shopcar_commodity_sum,shopcar_commodity_price,shopcar_commodity_vipprice"
					+ " from user_shopcar where shopcar_user_id = ? order by shopcar_commodity_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserInf.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 UserShopcar p =new UserShopcar();
			 p.setShopcar_commodity_id(rs.getInt(1));
			 p.setShopcar_commodity_name(rs.getString(2));
			 p.setShopcar_commodity_sum(rs.getInt(3));
			 p.setShopcar_commodity_price(rs.getFloat(4));
			 p.setShopcar_commodity_vipprice(rs.getFloat(5));
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
	public UserInf login(String userid, String pwd) throws BaseException {
		if("".equals(userid) ||userid==null){
			throw new BaseException("账号不能为空");
		}
		if("".equals(pwd)||pwd==null){
			throw new BaseException("密码不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_pwd from user_inf where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("账号不存在");
			UserInf u=new UserInf();
			u.setUser_id(rs.getString(1));
			u.setUser_pwd(rs.getString(2));
			if(!pwd.equals(u.getUser_pwd())) throw new BaseException("密码不正确");
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
	public  List<UserInf>loadUser(String userid)throws BaseException{
		List<UserInf> result=new ArrayList<UserInf>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_sex,user_pwd,user_phone,user_mail,user_city,user_registration_time,user_vip,user_vip_end_time "
					+ "from user_inf where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			UserInf u=new UserInf();
			u.setUser_id(rs.getString(1));
			u.setUser_name(rs.getString(2));
			u.setUser_sex(rs.getString(3));
			u.setUser_pwd(rs.getString(4));
			u.setUser_phone(rs.getString(5));
			u.setUser_mail(rs.getString(6));
			u.setUser_city(rs.getString(7));
			u.setUser_registration_time(rs.getDate(8));
			u.setUser_vip(rs.getBoolean(9));
			u.setUser_vip_end_time(rs.getDate(10));
			result.add(u);
			}
			
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
		return result;
	}
	
	
	@Override
	public UserInf changeUserPwd(String userid,String oldPwd,String newPwd1,String newPwd2)throws BaseException{
		if(oldPwd==null||"".equals(oldPwd)) throw new BaseException("原密码不能为空");
		if(newPwd1==null||"".equals(newPwd1)||newPwd2==null||"".equals(newPwd2))throw new BaseException("新密码不能为空");
		if(!newPwd1.equals(newPwd2)) throw new BaseException("两次密码不一样");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from user_inf where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update user_inf set user_pwd=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd1);
			pst.setString(2, userid);
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
	@Override
	public UserInf createUser(UserInf user)throws BaseException{
		if(user.getUser_id()==null || "".equals(user.getUser_id()) ){
			throw new BusinessException("账号不能为空");
		}
		if(user.getUser_name()==null || "".equals(user.getUser_name()) ){
			throw new BusinessException("姓名不能为空");
		}
		if(user.getUser_sex()==null || "".equals(user.getUser_sex()) ){
			throw new BusinessException("性别不能为空");
		}
		if(user.getUser_pwd()==null || "".equals(user.getUser_pwd()) ){
			throw new BusinessException("密码不能为空");
		}
		if(user.getUser_phone()==null || "".equals(user.getUser_phone()) ){
			throw new BusinessException("手机号不能为空");
		}
		if(user.getUser_mail()==null || "".equals(user.getUser_mail()) ){
			throw new BusinessException("邮箱不能为空");
		}
		if(user.getUser_city()==null || "".equals(user.getUser_city()) ){
			throw new BusinessException("城市不能为空");
		}
		if(user.getUser_city()==null || "".equals(user.getUser_city()) ){
			throw new BusinessException("不能为空");
		}
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_inf where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			sql="insert into user_inf(user_id,user_name,user_sex,user_pwd,user_phone,user_mail,user_city,user_registration_time,user_vip,user_vip_end_time) values(?,?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getUser_name());
			pst.setString(3, user.getUser_sex());
			pst.setString(4, user.getUser_pwd());
			pst.setString(5, user.getUser_phone());
			pst.setString(6, user.getUser_mail());
			pst.setString(7, user.getUser_city());
			pst.setTimestamp(8,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setBoolean(9, false);
			pst.setString(10,null);
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
		return user;
	}
	@Override
	public List<DeliveryAddressList> loadAddress()throws BaseException{
		List<DeliveryAddressList> result=new ArrayList<DeliveryAddressList>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select delivery_id,delivery_province,delivery_city,delivery_area,delivery_address,delivery_name,delivery_phone"
					+ " from delivery_address_list where delivery_user_id = ? order by delivery_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserInf.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 DeliveryAddressList p =new DeliveryAddressList();
			 p.setDelivery_id(rs.getInt(1));
			 p.setDelivery_province(rs.getString(2));
			 p.setDelivery_city(rs.getString(3));
			 p.setDelivery_area(rs.getString(4));
			 p.setDelivery_address(rs.getString(5));
			 p.setDelivery_name(rs.getString(6));
			 p.setDelivery_phone(rs.getString(7));
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
	public DeliveryAddressList addAddress(String province,String city,String area,String address,String name,String phone)throws BaseException{
		if(province==null || "".equals(province) ){
			throw new BusinessException("省份不能为空");
		}
		if(city==null || "".equals(city)){
			throw new BusinessException("市不能为空");
		}
		if(area==null || "".equals(area) ){
			throw new BusinessException("区/县不能为空");
		}
		if(address==null || "".equals(address) ){
			throw new BusinessException("详细地址不能为空");
		}
		if(name==null || "".equals(name) ){
			throw new BusinessException("收货人不能为空");
		}
		if(phone==null || "".equals(phone)){
			throw new BusinessException("手机号不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();

		 String  sql="insert into delivery_address_list(delivery_user_id,delivery_province,delivery_city,delivery_area,delivery_address,delivery_name,delivery_phone) "
					+ "values(?,?,?,?,?,?,?)";
		 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		    pst.setString(1, UserInf.currentLoginUser.getUser_id());
			pst.setString(2,province);
			pst.setString(3,city);
			pst.setString(4,area);
			pst.setString(5,address);
			pst.setString(6,name);
			pst.setString(7,phone);
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
	@Override
	public void deleteAddress(DeliveryAddressList address) throws BaseException{
		Connection conn=null;
		int addressId =address.getDelivery_id();
			try {
				conn=DBUtil.getConnection();
		java.sql.Statement st=conn.createStatement();
		  String sql="delete from delivery_address_list where delivery_id = "+addressId;
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
						
						e.printStackTrace();
					}
			}
		
	}
	@Override
	public void changeAddress(DeliveryAddressList address) throws BaseException{
		if(address.getDelivery_province()==null || "".equals(address.getDelivery_province()) ){
			throw new BusinessException("省份不能为空");
		}
		if(address.getDelivery_city()==null || "".equals(address.getDelivery_city())){
			throw new BusinessException("市不能为空");
		}
		if(address.getDelivery_area()==null || "".equals(address.getDelivery_area()) ){
			throw new BusinessException("区/县不能为空");
		}
		if(address.getDelivery_address()==null || "".equals(address.getDelivery_address()) ){
			throw new BusinessException("详细地址不能为空");
		}
		if(address.getDelivery_name()==null || "".equals(address.getDelivery_name()) ){
			throw new BusinessException("收货人不能为空");
		}
		if(address.getDelivery_phone()==null || "".equals(address.getDelivery_phone())){
			throw new BusinessException("手机号不能为空");
		}
		Connection conn=null;
		int deliveryId =address.getDelivery_id();
		try {
			conn=DBUtil.getConnection();
			String  sql="update delivery_address_list set  delivery_province = ?,delivery_city = ?,delivery_area = ?,delivery_address = ?,delivery_name = ?,delivery_phone = ? where delivery_id=?";
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			   pst.setString(1,address.getDelivery_province());
				pst.setString(2,address.getDelivery_city());
				pst.setString(3,address.getDelivery_area());
				pst.setString(4,address.getDelivery_address());
				pst.setString(5,address.getDelivery_name());
				pst.setString(6,address.getDelivery_phone());
				pst.setInt(7, deliveryId);
				pst.execute();
				pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
			
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	public static void main(String[] args) {
		

	}

}
