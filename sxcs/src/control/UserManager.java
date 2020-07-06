package control;

import java.sql.Connection;
import java.sql.SQLException;

import itl.IUserManager;
import model.UserInf;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class UserManager implements IUserManager{
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
	@Override
	public UserInf loadUser(String userid)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_sex,user_pwd,user_phone,user_mail,user_city,user_registration_time,user_vip,user_vip_end_time "
					+ "from user_inf where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("账号不存在");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
