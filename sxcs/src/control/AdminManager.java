package control;

import java.sql.Connection;
import java.sql.SQLException;

import itl.IAdminManager;
import model.AdminInformation;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class AdminManager implements IAdminManager{
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
	
	
	@Override
	public AdminInformation loadAdmin(String userid)throws BaseException{
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_id,admin_name,admin_pwd "
					+ "from admin_information where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			AdminInformation u=new AdminInformation();
			u.setAdmin_id(rs.getString(1));
			u.setAdmin_name(rs.getString(2));
			u.setAdmin_pwd(rs.getString(3));
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
