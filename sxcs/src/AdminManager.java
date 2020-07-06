

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
	public AdminInformation login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select admin_id,admin_pwd from admin_information where admin_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("用户名不存在");
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
