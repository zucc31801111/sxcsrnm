package itl;

import java.util.List;

import model.CommodityInformation;
import model.CommodityPurchase;
import model.DiscountCommodity;
import model.DiscountInformation;
import model.FreshCategory;
import model.Menu;
import model.Promotion;
import model.Recommended;
import model.UserShopcar;
import util.BaseException;

public interface ICommodityManager {
	public List<CommodityInformation> loadCommodity(FreshCategory curPlan)throws BaseException;
	
	public List<FreshCategory> loadFreshCategory()throws BaseException;
	
	public List<Promotion> loadPromotion()throws BaseException;
	
	public CommodityInformation addCommodity(String commodityName,double commodityPrice,double vipPrice,String commodityspec,String commoditydesc,FreshCategory category)throws BaseException;
   
	public FreshCategory addCategory(String categoryName,String categoryDescribe) throws BaseException;
	
	public void purchaseCommodity(CommodityInformation commodity, int purchase,String purchaseState) throws BaseException;
	
	public void deleteCommodity(CommodityInformation commodity) throws BaseException;
	
	public void changeCommodity(String commodityName,double commodityPrice,double vipPrice,String commodityspec,String commoditydesc,CommodityInformation commodity)throws BaseException;
	
	public void deleteCategory(FreshCategory category) throws BaseException;

	public void deletePromotion(Promotion promotion) throws BaseException;
	
	public void addPromotion(int commodityid, double price , int sum, String starttime, String endtime) throws BaseException;
	
	public List<CommodityPurchase> loadPurchase()throws BaseException;
	
	public Menu createMenu(String menu_name,String menu_materials,String menu_step, String menu_picture)throws BaseException;
	
	public List<Menu> loadMenu()throws BaseException;
	
	public List<Recommended> loadRecommendedCommodity(Menu curMenu)throws BaseException;
	
	public void deleteMenu(Menu curMenu) throws BaseException;
	
	public CommodityInformation addRecommendedCommodity(String commodityName,String describe,Menu curMenu)throws BaseException;

	public void deleteRecommendedCommodity(Recommended recommended)throws BaseException;

	
	public List<DiscountCommodity> loadDiscountCommodity(DiscountInformation discountInformation)throws BaseException;
	
	public void addDiscountCommodity(int commodityId,DiscountInformation discountInformation)throws BaseException;
	
	public void deleteDiscountCommodity(DiscountCommodity discountCommodity) throws BaseException;
	
	public DiscountInformation creatDiscountInformation(String discount_content,int discount_number,double discount_sum, String discount_start_time,String discount_end_time) throws BaseException;
	
	public List<DiscountInformation> loadDiscountInformation()throws BaseException;
	
	public void deleteDiscountInformation(DiscountInformation curdiscountInformation) throws BaseException;
}
