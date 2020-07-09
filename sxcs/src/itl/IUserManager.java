package itl;
import java.util.List;

import model.UserInf;
import util.BaseException;
public interface IUserManager {
	public UserInf login(String userid, String pwd) throws BaseException;
	
	public UserInf changeUserPwd(String userid,String oldPwd,String newPwd1,String newPwd2)throws BaseException;
	
	public UserInf createUser(UserInf user) throws BaseException;
	
	}