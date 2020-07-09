package itl;

import java.util.List;

import model.CommodityInformation;
import model.FreshCategory;
import model.Promotion;
import util.BaseException;

public interface ICommodityManager {
	public List<CommodityInformation> loadCommodity(FreshCategory curPlan)throws BaseException;
	
	public List<FreshCategory> loadFreshCategory()throws BaseException;
	
	public List<Promotion> loadPromotion()throws BaseException;
	
	public CommodityInformation addCommodity(String commodityName,float commodityPrice,float vipPrice,String commodityspec,String commoditydesc,FreshCategory category)throws BaseException;
   
	public FreshCategory addCategory(String categoryName,String categoryDescribe) throws BaseException;
	
	public void purchaseCommodity(CommodityInformation commodity, int purchase,String purchaseState) throws BaseException;
	
	public void deleteCommodity(CommodityInformation commodity) throws BaseException;
	
	public void deleteCategory(FreshCategory category) throws BaseException;

	public void deletePromotion(Promotion promotion) throws BaseException;
	
	public void addPromotion(int commodityid, float price , int sum, String starttime, String endtime) throws BaseException;
	
	
}
