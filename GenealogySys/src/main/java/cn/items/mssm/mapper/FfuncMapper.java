package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FFunctionCustom;

public interface FfuncMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月13日 下午4:22:40 
	 */
	
	public List<FFunctionCustom> findAllfunc()throws Exception;
	
	public List<FFunctionCustom> findParents()throws Exception;
	
	public int addFuncInfo(FFunctionCustom fFunctionCustom)throws Exception;
	
	public FFunctionCustom findFuncById(int funcid)throws Exception;
	
	public int updateFunc(FFunctionCustom fFunctionCustom)throws Exception;
	
	public int delFunc(int funcid)throws Exception;
	
	public int delFuncParent(int parentid)throws Exception;
	
	public List<FFunctionCustom> findUserFunc(FFunctionCustom fFunctionCustom)throws Exception;
}
