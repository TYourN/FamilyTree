package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.poCustom.FRolefuncCustom;

public interface FroleService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午2:46:30 
	 */
	
	//查找所有的角色信息
	public List<FRoleCustom> findRoleInfo() throws Exception;
	
	//增加角色信息 
	public int addRoleInfo(FRoleCustom fRoleCustom) throws Exception;
	
	//修改角色信息
	public int updateRoleInfo(FRoleCustom fRoleCustom) throws Exception;
	
	//删除角色信息
	public int deleteRoleInfo(int roleid) throws Exception;
	
	//根据Id查找角色信息
	public FRoleCustom findRInfoById(int roleid)throws Exception;
	
	//获取对应角色的功能 
	public List<FRolefuncCustom> findRFunc(int roleid)throws Exception;
	
	//删除对应角色Id的角色功能
	public int delRFunc(int roleid)throws Exception;
	
	//添加对应角色Id的角色功能
	public int addRFunc(FRolefuncCustom fRolefuncCustom)throws Exception;
	
	//查找对应名称的角色Id
	public int findRIdByName(String title)throws Exception;
	
	//获取用户对应的角色
	public List<FRoleCustom> findUserRoleTitle(int userid)throws Exception;
}
