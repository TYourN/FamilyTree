package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FRoleCustom;

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
	
}
