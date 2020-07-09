package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import itl.ICommodityManager;
import model.AdminInformation;
import model.CommodityInformation;
import model.FreshCategory;
import model.Promotion;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class CommodityManager implements ICommodityManager{
	@Override
	public List<CommodityInformation> loadCommodity(FreshCategory curPlan)throws BaseException{
		List<CommodityInformation> result=new ArrayList<CommodityInformation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select commodity_id,commodity_name,commodity_price,commodity_vip_price,commodity_number,commodity_specifications,commodity_describe,commodity_category_id from commodity_information "
					+ "where commodity_category_id = ? order by commodity_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,curPlan.getCategory_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 CommodityInformation p =new CommodityInformation();
			 p.setCommodity_id(rs.getInt(1));
			 p.setCommodity_name(rs.getString(2));
			 p.setCommodity_price(rs.getFloat(3));
			 p.setCommodity_vip_price(rs.getFloat(4));
			 p.setCommodity_number(rs.getInt(5));
			 p.setCommodity_specifications(rs.getString(6));
			 p.setCommodity_describe(rs.getString(7));
			 p.setCommodity_category_id(rs.getInt(8)); 
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
	public List<Promotion> loadPromotion()throws BaseException{
		List<Promotion> result=new ArrayList<Promotion>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select promotion_id,promotion_commodity_id,promotion_price,promotion_sum,promotion_start_time ,promotion_end_time from promotion order by promotion_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 Promotion p =new Promotion();
			 p.setPromotion_id(rs.getInt(1));
			 p.setPromotion_commodity_id(rs.getInt(2));
			 p.setPromotion_price(rs.getFloat(3));
			 p.setPromotion_sum(rs.getInt(4));
			 p.setPromotion_start_time(rs.getDate(5));
			 p.setPromotion_end_time(rs.getDate(6));
			 result.add(p);
		} 
		 }catch (SQLException ex) {
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
	public List<FreshCategory> loadFreshCategory()throws BaseException{
		List<FreshCategory> result=new ArrayList<FreshCategory>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select category_id,category_name,category_describe from fresh_category order by category_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 FreshCategory p =new FreshCategory();
			 p.setCategory_id(rs.getInt(1));
			 p.setCategory_name(rs.getString(2));
			 p.setCategory_describe(rs.getString(3));
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
	
	public CommodityInformation addCommodity(String commodityName,float commodityPrice,float vipPrice,String commodityspec,String commoditydesc,FreshCategory category)throws BaseException{
		if(commodityName==null || "".equals(commodityName) ){
			throw new BusinessException("商品名不能为空");
		}
		if(commodityPrice<=0 ){
			throw new BusinessException("价格应大于0");
		}
		if(vipPrice<=0){
			throw new BusinessException("vip价格应大于0");
		}
		if(commodityspec==null || "".equals(commodityspec) ){
			throw new BusinessException("商品规格不能为空");
		}
		if(commoditydesc==null || "".equals(commoditydesc) ){
			throw new BusinessException("商品描述不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from commodity_information where commodity_name =?" ;
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setString(1,commodityName);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("商品已存在");
				}
			rs.close();
			pst.close();
		   sql="insert into commodity_information(commodity_name,commodity_price,commodity_vip_price,commodity_number,commodity_specifications,commodity_describe,commodity_category_id) "
					+ "values(?,?,?,?,?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setString(1, commodityName);
			pst.setDouble(2, commodityPrice);
			pst.setDouble(3, vipPrice);
			pst.setInt(4, 0);
			pst.setString(5, commodityspec);
			pst.setString(6, commoditydesc);
			pst.setInt(7, category.getCategory_id());
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
	public void deleteCommodity(CommodityInformation commodity) throws BaseException {
		Connection conn=null;
		int commodityId =commodity.getCommodity_id();
			try {
				conn=DBUtil.getConnection();
		java.sql.Statement st=conn.createStatement();
		  String sql="delete from commodity_information where commodity_id = "+commodityId;
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
	public FreshCategory addCategory(String categoryName,String categoryDescribe) throws BaseException{
		if(categoryName==null || "".equals(categoryName) ){
			throw new BusinessException("类别名不能为空");
		}
		if(categoryDescribe==null || "".equals(categoryDescribe) ){
			throw new BusinessException("描述不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from fresh_category where category_name =?" ;
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setString(1,categoryName);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("类别已存在");
				}
			rs.close();
			pst.close();
		 sql="insert into fresh_category(category_name,category_describe)values(?,?)";
		  pst=conn.prepareStatement(sql);
			 pst.setString(1,categoryName);
			 pst.setString(2,categoryDescribe);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}
	@Override
	public void deleteCategory(FreshCategory category) throws BaseException{
		Connection conn=null;
		   int categoryId =category.getCategory_id();
			try {
				conn=DBUtil.getConnection();
				String sql="select count(*) from commodity_information where commodity_category_id = "+categoryId;
				java.sql.Statement st=conn.createStatement();
				java.sql.ResultSet rs=st.executeQuery(sql);
				if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					st.close();
					throw new BusinessException("存在商品，不能删除");
				}
		     rs.close();
		     sql="delete from fresh_category where category_id = "+categoryId;
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
	public void purchaseCommodity(CommodityInformation commodity, int purchase,String purchaseState) throws BaseException{
		if(purchase<=0  ){
			throw new BusinessException("采购数应大于0");
		}
		if(purchaseState==null || "".equals(purchaseState) ){
			throw new BusinessException("状态不能为空");
		}
		Connection conn=null;
		   int commodityId;
		   commodityId=commodity.getCommodity_id();
			try {
				
		    conn=DBUtil.getConnection();
		    String sql="insert into commodity_purchase(purchase_commodity_id,purchase_admin_id,purchase_number,purchase_state)values(?,?,?,?)";
		    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityId);
			 pst.setString(2, AdminInformation.currentLoginUser.getAdmin_id());
			 pst.setInt(3,purchase);
			 pst.setString(4, purchaseState);
			 pst.execute();
			 pst.close();
			 
		   sql="update commodity_information set commodity_number=commodity_number + ? where commodity_id = ?";
		    pst=conn.prepareStatement(sql);
		    pst.setInt(1, purchase);
		    pst.setInt(2, commodityId);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}
	@Override
	public void addPromotion(int commodityid, float price , int sum, String starttime, String endtime) throws BaseException{
		
		Connection conn=null;
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
		int number=0;
		try {
			conn=DBUtil.getConnection();
			Date timeone=time.parse(starttime);
		    Date timetwo=time.parse(endtime);
		    
		String sql="select commodity_number from commodity_information where commodity_id = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,commodityid);
		pst.execute();
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			number=rs.getInt(1);
			rs.close();
		   pst.close();
		}
		else {
			 rs.close();
			 pst.close();
			throw new BusinessException("商品不存在");
		}
			
		if(number<sum) {
			throw new BusinessException("促销数量大于商品数量");
		}
	
	    
		 sql="insert into promotion(promotion_commodity_id,promotion_price,promotion_sum,promotion_start_time,promotion_end_time)values(?,?,?,?,?)";
		     pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityid);
			 pst.setFloat(2,price);
			 pst.setInt(3,sum);
			 pst.setDate(4,new java.sql.Date(timeone.getTime()));
			 pst.setDate(5,new java.sql.Date(timetwo.getTime()));
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
	public void deletePromotion(Promotion promotion) throws BaseException{
		Connection conn=null;
		int promotionId =promotion.getPromotion_id();
			try {
				conn=DBUtil.getConnection();
		java.sql.Statement st=conn.createStatement();
		  String sql="delete from promotion where promotion_id = "+promotionId;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
