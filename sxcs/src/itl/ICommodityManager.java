package itl;

import java.util.List;

import model.CommodityInformation;
import model.CommodityPurchase;
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
	
	public CommodityInformation addCommodity(String commodityName,float commodityPrice,float vipPrice,String commodityspec,String commoditydesc,FreshCategory category)throws BaseException;
   
	public FreshCategory addCategory(String categoryName,String categoryDescribe) throws BaseException;
	
	public void purchaseCommodity(CommodityInformation commodity, int purchase,String purchaseState) throws BaseException;
	
	public void deleteCommodity(CommodityInformation commodity) throws BaseException;
	
	public void changeCommodity(String commodityName,float commodityPrice,float vipPrice,String commodityspec,String commoditydesc,CommodityInformation commodity)throws BaseException;
	
	public void deleteCategory(FreshCategory category) throws BaseException;

	public void deletePromotion(Promotion promotion) throws BaseException;
	
	public void addPromotion(int commodityid, float price , int sum, String starttime, String endtime) throws BaseException;
	
	public List<CommodityPurchase> loadPurchase()throws BaseException;
	
	public Menu createMenu(String menu_name,String menu_materials,String menu_step, String menu_picture)throws BaseException;
	
	public List<Menu> loadMenu()throws BaseException;
	
	public List<Recommended> loadRecommendedCommodity(Menu curMenu)throws BaseException;
	
	public void deleteMenu(Menu curMenu) throws BaseException;
	
	public CommodityInformation addRecommendedCommodity(String commodityName,String describe,Menu curMenu)throws BaseException;

	public void deleteRecommendedCommodity(Recommended recommended)throws BaseException;

}
