package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import itl.IUserManager;
import model.CommodityEvaluation;
import model.CommodityInformation;
import model.CommodityOrder;
import model.DeliveryAddressList;
import model.FreshCategory;
import model.Menu;
import model.OrderContent;
import model.Promotion;
import model.Recommended;
import model.UserInf;
import model.UserShopcar;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class UserManager implements IUserManager{
	
	public List<CommodityEvaluation> loadCommodityEvaluation(CommodityInformation commodityInformation)throws BaseException{
		List<CommodityEvaluation> result=new ArrayList<CommodityEvaluation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select eval_content,eval_date,eval__star,eval_picture"
					+ " from commodity_evaluation where eval_commodity_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,commodityInformation.getCommodity_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 CommodityEvaluation p =new CommodityEvaluation();
			 p.setEval_content(rs.getString(1));
			 p.setEval_date(rs.getTimestamp(2));
			 p.setEval__star(rs.getDouble(3));
			 p.setEval_picture(rs.getString(4));
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
	public void  jiesuan(List<UserShopcar> shopcar,String arrivetime,DeliveryAddressList address)throws BaseException{
		if(arrivetime.equals(null)||arrivetime==null) {
			throw new BusinessException("时间不能为空");
		}
		double sumPrice=0;
		double youhiPrice=0;
		int flag3=0;
		int coupon_id=0;
		double coupondel=0;
		Connection conn=null;
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			
				conn=DBUtil.getConnection();
				Date gettime=time.parse(arrivetime);
				int order=0;
				String sql="select max(order_id) from commodity_order ";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			    java.sql.ResultSet rs=pst.executeQuery();
				
			 if(!rs.next()) {
				 order=1;
			 } 
			 else {
				 order =rs.getInt(1)+1;
			 }
				 sql="insert into commodity_order(order_id,order_user_id,order_begin_price,order_end_price,order_gettime,order_delivery_id,order_state) "
						+ "values(?,?,0,0,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, order);
                pst.setString(2, UserInf.currentLoginUser.getUser_id());
                pst.setTimestamp(3,new java.sql.Timestamp(gettime.getTime()));
				pst.setInt(4, address.getDelivery_id());
				pst.setString(5,"已下单");
				pst.execute();
				pst.close();	 
				
		for(
			int i=0;i<shopcar.size();i++) {
			int discountId=0;
			int dicountNumber=0;
			double discountSum=0;
			double promotionPrice=0;
			int promotionSum=0;
			int flag1=0;
			int flag2=0;
			
			UserShopcar s=(UserShopcar)shopcar.get(i);
			//计算初始价格
			sumPrice=sumPrice+s.getShopcar_commodity_price()*s.getShopcar_commodity_sum();
			//判断满折条件，时间
			 sql="select list_discount_id,list_discount_start_time,list_discount_end_time from discount_commodity where list_commodity_id= ? ";
			 pst=conn.prepareStatement(sql);
			pst.setInt(1,s.getShopcar_commodity_id());
			 rs=pst.executeQuery();
			if(rs.next()) {
			if( rs.getInt(1)>0&&rs.getTimestamp(2).before(new java.sql.Timestamp(System.currentTimeMillis()))&&new java.sql.Timestamp(System.currentTimeMillis()).before(rs.getTimestamp(3)))
			 {  
				flag1=1;
				discountId=rs.getInt(1);
				rs.close();
				pst.close();
				//从满折信息表中读出数量，折扣
				sql="select discount_number, discount_sum from discount_information where discount_id= ? ";
			    pst=conn.prepareStatement(sql);
				pst.setInt(1,discountId);
				rs=pst.executeQuery();
				rs.next();
				dicountNumber=rs.getInt(1);
				discountSum=rs.getDouble(2);
				rs.close();
				pst.close();
				
			}
			}
			rs.close();
			pst.close();
			//判断限时促销条件，时间
			sql="select promotion_price, promotion_sum, promotion_start_time, promotion_end_time from promotion where promotion_commodity_id= ? ";
		    pst=conn.prepareStatement(sql);
		    pst.setInt(1,s.getShopcar_commodity_id());
			rs=pst.executeQuery();
			if(rs.next()) {
				if(rs.getTimestamp(3).before(new java.sql.Timestamp(System.currentTimeMillis()))&&new java.sql.Timestamp(System.currentTimeMillis()).before(rs.getTimestamp(4))) {
				flag2=1;
				promotionPrice=rs.getDouble(1);
				promotionSum=rs.getInt(2);
				rs.close();
				pst.close();
				
			}
			}
			rs.close();
			pst.close();
			if(flag1==1&&flag2==0) {
				if(s.getShopcar_commodity_sum()>=dicountNumber) {
					sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price,content_discount_sum,content_discount_id)values(?,?,?,?,?,?)";
				    pst=conn.prepareStatement(sql);
				    pst.setInt(1,order);
				    pst.setInt(2, s.getShopcar_commodity_id());
				    pst.setInt(3, s.getShopcar_commodity_sum());
				    pst.setDouble(4, s.getShopcar_commodity_price());
				    pst.setDouble(5,discountSum);
				    pst.setInt(6, discountId);
				    pst.execute();
				    pst.close();
		
				    youhiPrice=youhiPrice+0.1*s.getShopcar_commodity_sum()*s.getShopcar_commodity_price()*discountSum;
				    
				    }
				else {
					sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price)values(?,?,?,?)";
				    pst=conn.prepareStatement(sql);
				    pst.setInt(1,order);
				    pst.setInt(2, s.getShopcar_commodity_id());
				    pst.setInt(3, s.getShopcar_commodity_sum());
				    pst.setDouble(4, s.getShopcar_commodity_price());
				    pst.execute();
				    pst.close();
				    youhiPrice=youhiPrice+s.getShopcar_commodity_sum()*s.getShopcar_commodity_price();
				}
				
			}
			 if(flag1==0&&flag2==1){
				if(s.getShopcar_commodity_sum()<=promotionSum) {
				sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price)values(?,?,?,?)";
			    pst=conn.prepareStatement(sql);
			    pst.setInt(1,order);
			    pst.setInt(2, s.getShopcar_commodity_id());
			    pst.setInt(3, s.getShopcar_commodity_sum());
			    pst.setDouble(4, promotionPrice);
			    pst.execute();
			    pst.close();	
			    
			    //修改限时促销表
			    sql="update promotion set promotion_sum = promotion_sum -? where promotion_commodity_id =?";
			    pst=conn.prepareStatement(sql);
			    pst.setInt(1,s.getShopcar_commodity_sum());
			    pst.setInt(2,s.getShopcar_commodity_id());
			    pst.execute();
			    pst.close();
			    
			    youhiPrice=youhiPrice+s.getShopcar_commodity_sum()*promotionPrice;
				}
				
			    else {
			    	sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price)values(?,?,?,?)";
				    pst=conn.prepareStatement(sql);
				    pst.setInt(1,order);
				    pst.setInt(2, s.getShopcar_commodity_id());
				    pst.setInt(3,promotionSum);
				    pst.setDouble(4, promotionPrice);
				    pst.execute();
				    pst.close();
				    sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price)values(?,?,?,?)";
				    pst=conn.prepareStatement(sql);
				    pst.setInt(1,order);
				    pst.setInt(2, s.getShopcar_commodity_id());
				    pst.setInt(3,s.getShopcar_commodity_sum()-promotionSum);
				    pst.setDouble(4, s.getShopcar_commodity_price());
				    pst.execute();
				    pst.close();
				    
			         //修改限时促销表 
				    sql="delete from promotion where promotion_commodity_id =?";
				    pst=conn.prepareStatement(sql);
				    pst.setInt(1,s.getShopcar_commodity_id());
				    pst.execute();
				    pst.close();
				    youhiPrice=youhiPrice+(s.getShopcar_commodity_sum()-promotionSum)*s.getShopcar_commodity_price()+promotionSum*promotionPrice;
				    
			    }
				
				
			}
			if(flag1==0&&flag2==0) {
				sql="insert into order_content(content_order_id,content_commodity_id,order_number,order_price)values(?,?,?,?)";
			    pst=conn.prepareStatement(sql);
			    pst.setInt(1,order);
			    pst.setInt(2, s.getShopcar_commodity_id());
			    pst.setInt(3, s.getShopcar_commodity_sum());
			    pst.setDouble(4, s.getShopcar_commodity_price());
			    pst.execute();
			    pst.close();
			    
			    youhiPrice=youhiPrice+s.getShopcar_commodity_price()* s.getShopcar_commodity_sum();
			}
			//更新商品数量
			sql="update commodity_information set commodity_number=commodity_number - ?,commodity_salecount= commodity_salecount + ? where commodity_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, s.getShopcar_commodity_sum());
			pst.setInt(2, s.getShopcar_commodity_sum());
			pst.setInt(3, s.getShopcar_commodity_id());
			pst.execute();
			pst.close();	
					
		}
		
		//选择最优优惠券
		sql="select coupon_id,coupon_pricedel,coupon_start_time,coupon_end_time from coupon where coupon_price<= ? order by coupon_pricedel desc";
		pst=conn.prepareStatement(sql);
		pst.setDouble(1, youhiPrice);
		rs=pst.executeQuery();
		while(rs.next()&&flag3==0) {
			if(rs.getTimestamp(3).before(new java.sql.Timestamp(System.currentTimeMillis()))&&new java.sql.Timestamp(System.currentTimeMillis()).before(rs.getTimestamp(4))) {
				flag3=1;
				coupon_id=rs.getInt(1);
				 coupondel=rs.getDouble(2);
				youhiPrice=youhiPrice-rs.getDouble(2);
				
			}
				
			}
		rs.close();
		pst.close();
		
		//更新商品订单
		if(flag3==1) {
			sql="update commodity_order set order_begin_price= ?,order_end_price= ? ,order_coupon_id= ? where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setDouble(1, sumPrice);
			pst.setDouble(2, youhiPrice);
			pst.setInt(3, coupon_id);
			pst.setInt(4, order);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "购买成功，已自动为您选择最优优惠券,优惠券减免 ："+coupondel+" 原价格："+sumPrice+" 优惠后价格："+youhiPrice);
		}
		
		
		
		else {
		sql="update commodity_order set order_begin_price= ?,order_end_price= ? where order_id=?";
	    pst=conn.prepareStatement(sql);
		pst.setDouble(1, sumPrice);
		pst.setDouble(2, youhiPrice);
		pst.setInt(3, order);
		pst.execute();
		pst.close();	
		JOptionPane.showMessageDialog(null, "购买成功，已自动为您选择最优选择,无满足优惠券，原价格："+sumPrice+" 优惠后价格："+youhiPrice);
		}
		
		//更新
		sql="delete from user_shopcar where shopcar_user_id=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, UserInf.currentLoginUser.getUser_id());
		pst.execute();
		pst.close();
		
			

		
		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<CommodityOrder> loadCommodityOrderUser()throws BaseException{
		List<CommodityOrder> result=new ArrayList<CommodityOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select order_id,order_begin_price,order_end_price,order_gettime,order_delivery_id, order_state,order_coupon_id "
					+ "from commodity_order where order_user_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserInf.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 CommodityOrder p =new CommodityOrder();
			 p.setOrder_id(rs.getInt(1));
			 p.setOrder_begin_price(rs.getDouble(2));
			 p.setOrder_end_price(rs.getDouble(3));
			 p.setOrder_gettime(rs.getTimestamp(4));
			 p.setOrder_delivery_id(rs.getInt(5));
			 p.setOrder_state(rs.getString(6));
			 p.setOrder_coupon_id(rs.getInt(7));	
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
	public List<OrderContent> loadCommodityOrderContent(CommodityOrder commodityOrder)throws BaseException{
		List<OrderContent> result=new ArrayList<OrderContent>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select content_commodity_id,order_number,order_price,content_discount_sum,content_discount_id "
					+ "from order_content where content_order_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,commodityOrder.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 OrderContent p =new OrderContent();
			 
			 p.setContent_commodity_id(rs.getInt(1));
			 p.setOrder_number(rs.getInt(2));
			 p.setOrder_price(rs.getDouble(3));
			 p.setContent_discount_sum(rs.getDouble(4));
			 p.setContent_discount_id(rs.getInt(5));
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
	public UserShopcar addUserShopcartwo(int sum,Recommended recommended)throws BaseException{
		if(sum<0 ){
			throw new BusinessException("数量应大于0");
		}
		
		int allsum=sum;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select commodity_id,commodity_name,commodity_price,commodity_vip_price,commodity_number,commodity_specifications,commodity_describe,commodity_category_id "
					+ "from commodity_information where commodity_name = ? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,recommended.getrec_commodity_name());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			 CommodityInformation commodity1 =new CommodityInformation();
			 commodity1.setCommodity_id(rs.getInt(1));
			 commodity1.setCommodity_name(rs.getString(2));
			 commodity1.setCommodity_price(rs.getDouble(3));
			 commodity1.setCommodity_vip_price(rs.getDouble(4));
			 commodity1.setCommodity_number(rs.getInt(5));
			 commodity1.setCommodity_specifications(rs.getString(6));
			 commodity1.setCommodity_describe(rs.getString(7));
			 commodity1.setCommodity_category_id(rs.getInt(8)); 
			 rs.close();
			pst.close();
			
			if(commodity1.getCommodity_number()<sum) {
				throw new BusinessException("选购数量大于库存");
			}
			
			
		 sql="select sum(shopcar_commodity_sum) from user_shopcar where shopcar_commodity_id = ? and shopcar_user_id=?";
		 pst=conn.prepareStatement(sql);
		 pst.setInt(1,commodity1.getCommodity_id());
		 pst.setString(2,UserInf.currentLoginUser.getUser_id());
		 rs=pst.executeQuery();
		 rs.next();
		 if(rs.getInt(1)>0) {
			allsum=rs.getInt(1)+sum;
			rs.close();
			pst.close();
			if(commodity1.getCommodity_number()<allsum) {
				throw new BusinessException("选购数量大于库存");
			}
			sql="update user_shopcar set shopcar_commodity_sum = ? where shopcar_commodity_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,allsum);
			pst.setInt(2,commodity1.getCommodity_id());
			pst.execute();
			pst.close();
			
		}
		else {
			double price=0;
			if(UserInf.currentLoginUser.getUser_vip()==0) {
				price=commodity1.getCommodity_price();
			}
			else {
				price=commodity1.getCommodity_vip_price();
			}
			rs.close();
			pst.close();
			sql="insert into user_shopcar(shopcar_user_id,shopcar_commodity_id,shopcar_commodity_name,shopcar_commodity_sum,shopcar_commodity_price) "
					+ "values(?,?,?,?,?)";
		    pst=conn.prepareStatement(sql);
			pst.setString(1, UserInf.currentLoginUser.getUser_id());
			pst.setInt(2, commodity1.getCommodity_id());
			pst.setString(3, commodity1.getCommodity_name());
			pst.setInt(4, allsum);
			pst.setDouble(5,price);
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
	
	public void  addCommodityEvaluation(OrderContent OrderContent,String content,double eviltion)throws BaseException{
		if(content==null||content.equals(null) ){
			throw new BusinessException("评价内容不能为空");
		}
		if(eviltion<=0||eviltion>5) {
				throw new BusinessException("评价应大于0小于等于5");
			}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity_evaluation where eval_commodity_id=? and eval_user_id=?";
			java.sql.PreparedStatement  pst=conn.prepareStatement(sql);
			pst.setInt(1, OrderContent.getContent_commodity_id());
			pst.setString(2, UserInf.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("您已对改商品进行过评价");
			}
			rs.close();
			pst.close();
			
			
			sql="insert into commodity_evaluation(eval_commodity_id,eval_user_id,eval_content,eval_date,eval__star) "
					+ "values(?,?,?,?,?)";	
			  pst=conn.prepareStatement(sql);
			pst.setInt(1, OrderContent.getContent_commodity_id());
			pst.setString(2, UserInf.currentLoginUser.getUser_id());
			pst.setString(3, content);
			pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setDouble(5, eviltion);
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
	public void changeShopcar(UserShopcar shopcar,int sum)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
		String sql="select commodity_number from commodity_information where commodity_id = ?";	
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,shopcar.getShopcar_commodity_id());
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
		if(rs.getInt(1)<sum) {
			rs.close();
			pst.close();
			throw new BusinessException("选购数量大于库存");
		}
		}
		rs.close();
		pst.close();
	   sql="update user_shopcar set shopcar_commodity_sum = ? where shopcar_commodity_id = ?";
	   pst=conn.prepareStatement(sql);
		pst.setInt(1,sum);
		pst.setInt(2,shopcar.getShopcar_commodity_id());
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
	public void deleteShopcar(UserShopcar shopcar) throws BaseException {
		Connection conn=null;
		int commodityId =shopcar.getShopcar_commodity_id();
			try {
				conn=DBUtil.getConnection();
		java.sql.Statement st=conn.createStatement();
		  String sql="delete from user_shopcar where shopcar_commodity_id = "+commodityId;
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
		rs.next();
		if(rs.getInt(1)>0) {
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
			sql="insert into user_shopcar(shopcar_user_id,shopcar_commodity_id,shopcar_commodity_name,shopcar_commodity_sum,shopcar_commodity_price) "
					+ "values(?,?,?,?,?)";
		    pst=conn.prepareStatement(sql);
			pst.setString(1, UserInf.currentLoginUser.getUser_id());
			pst.setInt(2, commodity.getCommodity_id());
			pst.setString(3, commodity.getCommodity_name());
			pst.setInt(4, allsum);
			if(UserInf.currentLoginUser.getUser_vip()==1) {
			pst.setDouble(5, commodity.getCommodity_vip_price());	
			}
			else {
			pst.setDouble(5, commodity.getCommodity_price());
			}
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
			String sql="select shopcar_commodity_id,shopcar_commodity_name,shopcar_commodity_sum,shopcar_commodity_price"
					+ " from user_shopcar where shopcar_user_id = ? order by shopcar_commodity_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserInf.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
		 while(rs.next()) {
			 UserShopcar p =new UserShopcar();
			 p.setShopcar_commodity_id(rs.getInt(1));
			 p.setShopcar_commodity_name(rs.getString(2));
			 p.setShopcar_commodity_sum(rs.getInt(3));
			 p.setShopcar_commodity_price(rs.getDouble(4));
	
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
			String sql="select user_id,user_pwd,user_vip,user_vip_end_time from user_inf where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException("账号不存在");
			UserInf u =new UserInf();
			u.setUser_id(rs.getString(1));
			u.setUser_pwd(rs.getString(2));
			u.setUser_vip(rs.getInt(3));
			u.setUser_vip_end_time(rs.getTimestamp(4));
			/*UserInf.currentLoginUser.setUser_id(rs.getString(1));
			UserInf.currentLoginUser.setUser_pwd(rs.getString(2));
			UserInf.currentLoginUser.setUser_vip(rs.getInt(3));
			UserInf.currentLoginUser.setUser_vip_end_time(rs.getTimestamp(4));*/
			
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
			u.setUser_registration_time(rs.getTimestamp(8));
			u.setUser_vip(rs.getInt(9));
			u.setUser_vip_end_time(rs.getTimestamp(10));
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
	public void changeUserPwd(String userid,String oldPwd,String newPwd1,String newPwd2)throws BaseException{
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
			pst.setInt(9, 0);
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
				String sql="select count(*) from commodity_order where order_delivery_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,address.getDelivery_id());
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) 
					if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("地址存在订单表中无法删除");
				}
				rs.close();
				pst.close();
				
				
		java.sql.Statement st=conn.createStatement();
		   sql="delete from delivery_address_list where delivery_id = "+addressId;
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
			String sql="select count(*) from commodity_order where order_delivery_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,address.getDelivery_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1)>0) {
				rs.close();
				pst.close();
				throw new BusinessException("地址存在订单表中无法修改");
			}
			rs.close();
			pst.close();
			
			  sql="update delivery_address_list set  delivery_province = ?,delivery_city = ?,delivery_area = ?,delivery_address = ?,delivery_name = ?,delivery_phone = ? where delivery_id=?";
			  pst=conn.prepareStatement(sql);
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
