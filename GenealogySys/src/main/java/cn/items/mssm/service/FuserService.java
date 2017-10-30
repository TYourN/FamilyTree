package cn.items.mssm.service;

import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;

public interface FuserService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月13日 下午2:56:16 
	 */
	//管理员增加族人信息之前先判断是否存在这个族人:从
	public int findUserExit(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	//管理员增加族人的信息：从 
	public int addUserInfo(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	//增加族人信息之后找到增加信息的Id：从
    public int findLatestUserInfo()throws Exception;
	
    //管理员增加族人的登录信息：主
	public int addUser(FUserCustom fUserCustom)throws Exception;
	
	//修改族人的 所有个人信息:从
	public int updateUserInfo(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	//修改族人的 所有个人信息:主
	public int updateUser(FUserCustom fUserCustom)throws Exception;
	
	//管理员删除族人:主
	public int deleteUser(int userid)throws Exception;

	//管理员删除族人：从 
	public int deleteUserInfo(int userinfoid)throws Exception;
}
