package itl;
import java.util.List;

import model.CommodityInformation;
import model.DeliveryAddressList;
import model.UserInf;
import model.UserShopcar;
import util.BaseException;
public interface IUserManager {
	public UserInf login(String userid, String pwd) throws BaseException;
	
	public UserInf changeUserPwd(String userid,String oldPwd,String newPwd1,String newPwd2)throws BaseException;
	
	public UserInf createUser(UserInf user) throws BaseException;
	
	public List<DeliveryAddressList> loadAddress()throws BaseException;
	
	public DeliveryAddressList addAddress(String province,String city,String area,String address,String name,String phone)throws BaseException;

	public void deleteAddress(DeliveryAddressList address) throws BaseException;
	
	public void changeAddress(DeliveryAddressList address) throws BaseException;
	
	public List<UserShopcar> loadShopcar()throws BaseException;
	
	public UserShopcar addUserShopcar(int sum,CommodityInformation commodity)throws BaseException;
}