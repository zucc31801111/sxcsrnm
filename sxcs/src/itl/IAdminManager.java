package itl;

import java.util.List;

import model.AdminInformation;
import util.BaseException;

public interface IAdminManager { 
	public AdminInformation loadAdmin(String adminid)throws BaseException;
	
	public AdminInformation login(String userid, String pwd) throws BaseException;
	
	public AdminInformation changeAdminPwd(String adminid,String oldPwd,String newPwd1,String newPwd2) throws BaseException;

}