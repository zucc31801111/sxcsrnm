package starter;

import control.AdminManager;
import control.CommodityManager;
import control.UserManager;
import itl.IAdminManager;
import itl.ICommodityManager;
import itl.IUserManager;


public class SXCSUtil {
	public static IAdminManager adminManager=new AdminManager();
	public static IUserManager userManager=new UserManager();
	public static ICommodityManager commodityManager =new CommodityManager();
}
