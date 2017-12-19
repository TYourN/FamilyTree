package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.poCustom.FRolefuncCustom;

public interface FroleMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午2:40:55 
	 */
	
	public int addRoleInfo(FRoleCustom fRoleCustom) throws Exception;
	
	public int updateRoleInfo(FRoleCustom fRoleCustom) throws Exception;
	
	public int deleteRoleInfo(int roleid) throws Exception;
	
	public List<FRoleCustom> findRoleInfo() throws Exception;
	
	public FRoleCustom findRInfoById(int roleid)throws Exception;
	
	public List<FRolefuncCustom> findRFunc(int roleid)throws Exception;
	
	public int delRFunc(int roleid)throws Exception;
	
	public int addRFunc(FRolefuncCustom fRolefuncCustom)throws Exception;
	
	public int findRIdByName(String title)throws Exception;
	
	public List<FRoleCustom> findUserRoleTitle(int userid)throws Exception;
}
