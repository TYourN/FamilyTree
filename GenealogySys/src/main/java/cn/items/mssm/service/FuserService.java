package cn.items.mssm.service;

import java.util.List;
import java.util.Map;

import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;

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
	public int deleteUser(int userinfoid)throws Exception;

	//管理员删除族人：从 
	public int deleteUserInfo(int userinfoid)throws Exception;
	
	//查找出对应家族的所有对应身份的人的人数：主
	public int findAllCount(Map<String, Object> map)throws Exception;
	
	//查找出所有的族人人数
	public int findFamCount()throws Exception;
	
	//查找出对应年代对应迁离情况的 族人人数
	public int findExCount(Map<String, String> map)throws Exception;
	
	//获取家族人员中男女的数量
	public int findFamSex(Map<String, String> map)throws Exception;
	
	//查找家族中不同年龄段的人的数量
	public int findFamAge(Map<String, Integer> map)throws Exception;
	
	//获取族人的基本信息
	public List<FUserinfoCustom> findAllperson()throws Exception;
	
	//查询对应ID的族人的信息
	public FUserinfoCustom findpersonById(int userinfoid)throws Exception;
	
	//获取到对应用户的所有角色
	public List<FUserroleCustom> findURole(int userid)throws Exception;
	
	//删除对应用户Id的用户角色
	public int delURole(int userid)throws Exception;
	
	//添加对应用户Id的用户角色 
	public int addURole(FUserroleCustom fUserroleCustom)throws Exception;
	
	//判断主库中族人信息是否存在 
	public FUserCustom findUserE(FUserCustom fUserCustom)throws Exception;
	
	//获取对应Id的用户基本信息
	public FUserCustom findUserById(int userid)throws Exception;
	
	//判断是否是管理员登录
	public FAdminCustom findAdminE(FAdminCustom fAdminCustom)throws Exception;
	
	//增加族人信息之后找到增加信息的Id：主 
	public int findLatestUser()throws Exception;
	
	//在主库中获取到对应条件的用户Id
	public int findUserIdByAu(Map<String,Integer> map)throws Exception;
}
