package itl;

import java.util.List;

import model.AdminInformation;
import util.BaseException;

public interface IAdminManager {
	/**
	 * 添加计划
	 * 要求新增的计划的排序号为当前用户现有最大排序号+1
	 * 注意：当前登陆用户可通过 BeanUser.currentLoginUser获取
	 * @param name  计划名称
	 * @throws BaseException
	 */
	public AdminInformation loadAdmin(String userid)throws BaseException;
	public AdminInformation login(String userid, String pwd) throws BaseException;

}