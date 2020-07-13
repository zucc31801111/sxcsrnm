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
import model.CommodityPurchase;
import model.DiscountCommodity;
import model.DiscountInformation;
import model.FreshCategory;
import model.Menu;
import model.Promotion;
import model.Recommended;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class CommodityManager implements ICommodityManager{
	@Override
	public void deleteDiscountInformation(DiscountInformation curdiscountInformation) throws BaseException{
		Connection conn=null;
		   int id =curdiscountInformation.getDiscount_id();
			try {
				conn=DBUtil.getConnection();
				String sql="select count(*) from discount_commodity where list_discount_id = "+id;
				java.sql.Statement st=conn.createStatement();
				java.sql.ResultSet rs=st.executeQuery(sql);
				if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					st.close();
					throw new BusinessException("存在满折商品，不能删除");
				}
		     rs.close();
		     sql="delete from discount_information where discount_id = "+id;
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
	public void deleteDiscountCommodity(DiscountCommodity discountCommodity) throws BaseException{
		Connection conn=null;
		int commodityId =discountCommodity.getList_commodity_id();
		int discountId=discountCommodity.getList_discount_id();
			try {
				conn=DBUtil.getConnection();
		
		  String sql="delete from discount_commodity where list_commodity_id = ? and list_discount_id=?";
		  java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityId);
			 pst.setInt(2, discountId);
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
	public void addDiscountCommodity(int commodityId,DiscountInformation discountInformation)throws BaseException{
		
		if(commodityId<=0 ){
			throw new BusinessException("推荐商品名不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from commodity_information where commodity_id =? " ;
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityId);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
					rs.close();
					pst.close();
					throw new BusinessException("该商品不存在");
				}
			rs.close();
			pst.close();
			
			sql="select count(*) from discount_commodity where list_commodity_id =? " ;
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityId);
			 rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("该商品已存在满折活动");
				}
			rs.close();
			pst.close();
			
			sql="select count(*) from discount_commodity where list_discount_id =? " ;
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1,discountInformation.getDiscount_id());
			 rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("该满折活动已存在商品");
				}
			rs.close();
			pst.close();
			
			sql="select count(*) from promotion where promotion_commodity_id =? " ;
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityId);
			 rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("该商品已存在限时促销活动");
				}
			rs.close();
			pst.close();
		   sql="insert into discount_commodity(list_discount_id,list_commodity_id,list_discount_start_time,list_discount_end_time) values(?,?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setInt(1, discountInformation.getDiscount_id());
			pst.setInt(2, commodityId);
			pst.setTimestamp(3, discountInformation.getDiscount_start_time());
			pst.setTimestamp(4, discountInformation.getDiscount_end_time());
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
		
		
	}
	
	@Override
	public List<DiscountCommodity> loadDiscountCommodity(DiscountInformation discountInformation)throws BaseException{
		List<DiscountCommodity> result=new ArrayList<DiscountCommodity>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select list_discount_id,list_commodity_id,list_discount_start_time,list_discount_end_time from discount_commodity where list_discount_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,discountInformation.getDiscount_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 DiscountCommodity p =new DiscountCommodity();
			 p.setList_discount_id(rs.getInt(1));
			 p.setList_commodity_id(rs.getInt(2));
			 p.setList_discount_start_time(rs.getTimestamp(3));
			 p.setList_discount_end_time(rs.getTimestamp(4));
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
	public DiscountInformation creatDiscountInformation(String discount_content,int discount_number,double discount_sum, String discount_start_time,String discount_end_time) throws BaseException {
		
		if(discount_content==null || "".equals(discount_content) ){
			throw new BusinessException("满折内容不能为空");
		}
		if(discount_number<=0  ){
				throw new BusinessException("适用商品数应大于0");
			}
		if(discount_sum<=0 ||discount_sum>=10 ){
			throw new BusinessException("折扣应大于0且小于10");
		}
		if(discount_start_time==null || "".equals(discount_start_time)){
			throw new BusinessException("开始时间不能为空");
		}
		if(discount_end_time==null || "".equals(discount_end_time) ){
			throw new BusinessException("结束时间不能为空");
		}
		Connection conn=null;
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			conn=DBUtil.getConnection();
			Date timeone=time.parse(discount_start_time);
		    Date timetwo=time.parse(discount_end_time);
		    if (new java.sql.Timestamp(timetwo.getTime()).before(new java.sql.Timestamp(timeone.getTime()))) {
		    	  throw new BaseException("结束时间应晚于开始时间不能为空");
		      }
		    
		    String sql="insert into discount_information(discount_content,discount_number,discount_sum,discount_start_time,discount_end_time)values(?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, discount_content);
			pst.setInt(2, discount_number);
			pst.setDouble(3, discount_sum);
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
					
					e.printStackTrace();
				}	
	}
		return null;
	}
	@Override
	public List<DiscountInformation> loadDiscountInformation()throws BaseException{	
		List<DiscountInformation> result=new ArrayList<DiscountInformation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select discount_id,discount_content,discount_number,discount_sum,discount_start_time,discount_end_time "
					+ "from discount_information order by discount_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 DiscountInformation p =new DiscountInformation();
			 p.setDiscount_id(rs.getInt(1));
			 p.setDiscount_content(rs.getString(2));
			 p.setDiscount_number(rs.getInt(3));
			 p.setDiscount_sum(rs.getDouble(4));
			 p.setDiscount_start_time(rs.getTimestamp(5));
			 p.setDiscount_end_time(rs.getTimestamp(6));
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
	public void deleteRecommendedCommodity(Recommended recommended)throws BaseException{
			Connection conn=null;
			String name=recommended.getrec_commodity_name();
				try {
					conn=DBUtil.getConnection();
			
			  String sql="delete from recommended where rec_commodity_name = ?";
			  java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				 pst.setString(1,name);
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
	public CommodityInformation addRecommendedCommodity(String commodityName,String describe,Menu curMenu)throws BaseException{
		if(commodityName==null || "".equals(commodityName) ){
			throw new BusinessException("推荐商品名不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from commodity_information where commodity_name =? " ;
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setString(1,commodityName);
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
					rs.close();
					pst.close();
					throw new BusinessException("该商品不存在");
				}
			rs.close();
			pst.close();
			sql="select count(*) from recommended where rec_commodity_name =? and rec_menu_id =?" ;
			 pst=conn.prepareStatement(sql);
			 pst.setString(1,commodityName);
			 pst.setInt(2, curMenu.getMenu_id());
			 rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("该菜谱中该商品已存在");
				}
			rs.close();
			pst.close();
		   sql="insert into recommended(rec_commodity_name,rec_menu_id,rec_describe) values(?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setString(1, commodityName);
			pst.setInt(2, curMenu.getMenu_id());
			pst.setString(3, describe);
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
	public void deleteMenu(Menu curMenu) throws BaseException{
		Connection conn=null;
		   int MenuId =curMenu.getMenu_id();
			try {
				conn=DBUtil.getConnection();
				String sql="select count(*) from recommended where rec_menu_id = "+MenuId;
				java.sql.Statement st=conn.createStatement();
				java.sql.ResultSet rs=st.executeQuery(sql);
				if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					st.close();
					throw new BusinessException("存在推荐商品，不能删除");
				}
		     rs.close();
		     sql="delete from menu where menu_id = "+MenuId;
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
	public List<Recommended> loadRecommendedCommodity(Menu curMenu)throws BaseException{
		List<Recommended> result=new ArrayList<Recommended>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select rec_commodity_name,rec_describe,rec_menu_id from recommended where rec_menu_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,curMenu.getMenu_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 Recommended p =new Recommended();
			 p.setrec_commodity_name(rs.getString(1));
			 p.setRec_describe(rs.getString(2));
			 p.setRec_menu_id(rs.getInt(3));
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
	public List<Menu> loadMenu()throws BaseException{
		List<Menu> result=new ArrayList<Menu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select menu_id,menu_name,menu_materials,menu_step,menu_picture from menu  order by menu_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 Menu p =new Menu();
			 p.setMenu_id(rs.getInt(1));
			 p.setMenu_name(rs.getString(2));
			 p.setMenu_materials(rs.getString(3));
			 p.setMenu_step(rs.getString(4));
			 p.setMenu_picture(rs.getString(5));
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
	public Menu createMenu(String menu_name,String menu_materials,String menu_step, String menu_picture) throws BaseException {
		if(menu_name==null || "".equals(menu_name) ){
				throw new BusinessException("菜谱名不能为空");
			}
		if(menu_materials==null || "".equals(menu_materials)){
			throw new BusinessException("菜谱材料不能为空");
		}
		if(menu_step==null || "".equals(menu_step) ){
			throw new BusinessException("菜谱步骤不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from menu where menu_name =?" ;
			 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setString(1,menu_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("菜谱已存在");
				}
			rs.close();
			pst.close();
		   sql="insert into menu(menu_name,menu_materials,menu_step,menu_picture) "
					+ "values(?,?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setString(1, menu_name);
			pst.setString(2, menu_materials);
			pst.setString(3, menu_step);
		    pst.setString(4,menu_picture);
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
	public List<CommodityInformation> loadCommodity(FreshCategory curPlan)throws BaseException{
		List<CommodityInformation> result=new ArrayList<CommodityInformation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select commodity_id,commodity_name,commodity_price,commodity_vip_price,commodity_number,commodity_specifications,commodity_describe,commodity_category_id ,commodity_salecount from commodity_information "
					+ "where commodity_category_id = ? order by commodity_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,curPlan.getCategory_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 CommodityInformation p =new CommodityInformation();
			 p.setCommodity_id(rs.getInt(1));
			 p.setCommodity_name(rs.getString(2));
			 p.setCommodity_price(rs.getDouble(3));
			 p.setCommodity_vip_price(rs.getDouble(4));
			 p.setCommodity_number(rs.getInt(5));
			 p.setCommodity_specifications(rs.getString(6));
			 p.setCommodity_describe(rs.getString(7));
			 p.setCommodity_category_id(rs.getInt(8)); 
			 p.setCommodity_salecount(rs.getInt(9));
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
			String sql="select promotion_id,promotion_commodity_id,promotion_commodity_name,promotion_price,promotion_sum,promotion_start_time ,promotion_end_time from promotion order by promotion_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 Promotion p =new Promotion();
			 p.setPromotion_id(rs.getInt(1));
			 p.setPromotion_commodity_id(rs.getInt(2));
			 p.setPromotion_commodity_name(rs.getString(3));
			 p.setPromotion_price(rs.getDouble(4));
			 p.setPromotion_sum(rs.getInt(5));
			 p.setPromotion_start_time(rs.getTimestamp(6));
			 p.setPromotion_end_time(rs.getTimestamp(7));
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
	public List<CommodityPurchase> loadPurchase()throws BaseException{
		List<CommodityPurchase> result=new ArrayList<CommodityPurchase>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select purchase_id,purchase_commodity_id,purchase_admin_id,purchase_number,purchase_state from commodity_purchase order by purchase_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 CommodityPurchase p =new CommodityPurchase();
			 p.setPurchase_id(rs.getInt(1));
			 p.setPurchase_commodity_id(rs.getInt(2));
			 p.setPurchase_admin_id(rs.getString(3));
			 p.setPurchase_number(rs.getInt(4));
			 p.setPurchase_state(rs.getString(5));
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
	public void changeCommodity(String commodityName,double commodityPrice,double vipPrice,String commodityspec,String commoditydesc,CommodityInformation commodity)throws BaseException{
		if(commodityName==null || "".equals(commodityName) ){
			throw new BusinessException("商品名不能为空");
		}
		if(commodityPrice<=0 ){
			throw new BusinessException("价格应大于0");
		}
		if(vipPrice<=0){
			throw new BusinessException("vip价格应大于0");
		}
		if(commodityPrice<=vipPrice){
			throw new BusinessException("vip价格应小于原价格");
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
				
				String	sql="select count(*) from recommended where rec_commodity_name =?";
				java.sql.PreparedStatement  pst=conn.prepareStatement(sql);
				pst.setString(1,commodityName);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
						rs.close();
						pst.close();
						throw new BusinessException("商品存在推荐菜谱中，请先删除推荐商品");
					}
				rs.close();
				pst.close();
			   sql="select count(*) from promotion where promotion_commodity_name =?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,commodityName);
				 rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
						rs.close();
						pst.close();
						throw new BusinessException("商品正在促销，无法修改");
					}
				rs.close();
				pst.close();
			   sql="select count(*) from user_shopcar where shopcar_commodity_name =?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,commodityName);
			   rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
						rs.close();
						pst.close();
						throw new BusinessException("商品存在用户购物车中，无法修改");
					}
				rs.close();
				pst.close();
				sql="select count(*) from commodity_information where commodity_name =?" ;
				 pst=conn.prepareStatement(sql);
				 pst.setString(1,commodityName);
				 rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
						rs.close();
						pst.close();
						throw new BusinessException("商品已存在");
					}
				rs.close();
				pst.close();
				
				 
				
			   sql="update commodity_information set commodity_name= ?,commodity_price= ?,commodity_vip_price= ?,commodity_specifications= ?,commodity_describe= ? where commodity_id= ?";
				 pst=conn.prepareStatement(sql);
				pst.setString(1, commodityName);
				pst.setDouble(2, commodityPrice);
				pst.setDouble(3, vipPrice);
				pst.setString(4, commodityspec);
				pst.setString(5, commoditydesc);
				pst.setInt(6, commodity.getCommodity_id());
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
		}
	
	
	@Override
	
	public CommodityInformation addCommodity(String commodityName,double commodityPrice,double vipPrice,String commodityspec,String commoditydesc,FreshCategory category)throws BaseException{
		if(commodityName==null || "".equals(commodityName) ){
			throw new BusinessException("商品名不能为空");
		}
		if(commodityPrice<=0 ){
			throw new BusinessException("价格应大于0");
		}
		if(vipPrice<=0){
			throw new BusinessException("vip价格应大于0");
		}
		if(commodityPrice<=vipPrice){
			throw new BusinessException("vip价格应大于原价格");
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

		   sql="insert into commodity_information(commodity_name,commodity_price,commodity_vip_price,commodity_number,commodity_specifications,commodity_describe,commodity_category_id,commodity_salecount) "
					+ "values(?,?,?,?,?,?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setString(1, commodityName);
			pst.setDouble(2, commodityPrice);
			pst.setDouble(3, vipPrice);
			pst.setInt(4, 0);
			pst.setString(5, commodityspec);
			pst.setString(6, commoditydesc);
			pst.setInt(7, category.getCategory_id());
			pst.setInt(8, 0);
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
				String sql="select count(*) from recommended where rec_commodity_name=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,commodity.getCommodity_name());
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("商品存在菜谱推荐商品中无法删除，请先删除推荐商品");
				}
				rs.close();
				pst.close();
				
				sql="select count(*) from discount_commodity where list_commodity_id = ?";
			    pst=conn.prepareStatement(sql);
				pst.setInt(1,commodity.getCommodity_id());
				pst.execute();
				rs=pst.executeQuery();
				rs.next();
				if(rs.getInt(1)>0) {
					rs.close();
					 pst.close();
					throw new BusinessException("该商品存在满折活动,无法删除");
				}
				rs.close();
				 pst.close();	 
			 
				 sql="select count(*) from promotion where promotion_commodity_name = ?";
				    pst=conn.prepareStatement(sql);
					pst.setString(1,commodity.getCommodity_name());
					pst.execute();
					rs=pst.executeQuery();
					rs.next();
					if(rs.getInt(1)>0) {
						rs.close();
						 pst.close();
						throw new BusinessException("该商品存在限时促销活动,无法删除");
					}
					rs.close();
					 pst.close();
				
				sql="select count(*) from user_shopcar where shopcar_commodity_name =?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,commodity.getCommodity_name());
			   rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
						rs.close();
						pst.close();
						throw new BusinessException("商品存在用户购物车中，无法删除");
					}
		     java.sql.Statement st=conn.createStatement();
		   sql="delete from commodity_information where commodity_id = "+commodityId;
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
	public void addPromotion(int commodityid, double price , int sum, String starttime, String endtime) throws BaseException{
		if(price<=0) {
			throw new BusinessException("价格应大于0");
		}
		if(sum<=0) {
			throw new BusinessException("数量应大于0");
		}
		if("".equals(starttime) ||starttime==null) {
			throw new BaseException("开始时间不能为空");
		}
		if("".equals(endtime) ||endtime==null) {
			throw new BaseException("结束时间不能为空");
		}
		Connection conn=null;
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int number=0;
		double iprice=0;
		double vipprice=0;
		String name =null;
		try {
			conn=DBUtil.getConnection();
			Date timeone=time.parse(starttime);
		    Date timetwo=time.parse(endtime);
		    if (new java.sql.Timestamp(timetwo.getTime()).before(new java.sql.Timestamp(timeone.getTime()))) {
		    	  throw new BaseException("结束时间应晚于开始时间不能为空");
		      }
		    String sql="select count(*) from promotion where promotion_commodity_id = ?";
		    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,commodityid);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)>0) {
				rs.close();
				 pst.close();
				throw new BusinessException("该商品已在促销");
			}
			rs.close();
			 pst.close();
			 
			 sql="select count(*) from discount_commodity where list_commodity_id = ?";
			    pst=conn.prepareStatement(sql);
				pst.setInt(1,commodityid);
				pst.execute();
				rs=pst.executeQuery();
				rs.next();
				if(rs.getInt(1)>0) {
					rs.close();
					 pst.close();
					throw new BusinessException("该商品存在满折活动,不能添加促销活动");
				}
				rs.close();
				 pst.close();
	 
		sql="select commodity_name,commodity_number,commodity_price,commodity_vip_price from commodity_information where commodity_id = ?";
		 pst=conn.prepareStatement(sql);
		pst.setInt(1,commodityid);
		pst.execute();
		rs=pst.executeQuery();
		if(rs.next()) {
			name=rs.getString(1);
			number=rs.getInt(2);
			iprice=rs.getDouble(3);
			vipprice=rs.getDouble(4);
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
	if(price>=vipprice) {
		throw new BusinessException("促销价格应小于会员价");
	}   
		 sql="insert into promotion(promotion_commodity_id,promotion_price,promotion_sum,promotion_start_time,promotion_end_time,promotion_commodity_name)values(?,?,?,?,?,?)";
		     pst=conn.prepareStatement(sql);
			 pst.setInt(1,commodityid);
			 pst.setDouble(2,price);
			 pst.setInt(3,sum);
			 pst.setTimestamp(4,new java.sql.Timestamp(timeone.getTime()));
			 pst.setTimestamp(5,new java.sql.Timestamp(timetwo.getTime()));
			 pst.setString(6, name);
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
