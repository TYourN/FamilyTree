package cn.items.mssm.service;

import java.util.List;
import java.util.Map;

import cn.items.mssm.poCustom.FFunctionCustom;

public interface FfuncService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午3:29:20 
	 */
	
	//查找出所有的功能
	public List<FFunctionCustom> findAllfunc()throws Exception;
	
	//获取所有的 父功能模块
	public List<FFunctionCustom> findParents()throws Exception;
	
	//增加一个功能（模块）
	public int addFuncInfo(FFunctionCustom fFunctionCustom)throws Exception;
	
	//获取对应Id的功能信息
	public FFunctionCustom findFuncById(int funcid)throws Exception;
	
	//更新功能数据
	public int updateFunc(FFunctionCustom fFunctionCustom)throws Exception;
	
	//删除一条功能模块
	public int delFunc(int funcid)throws Exception;
	
	//删除对应父节点下所有的子节点
	public int delFuncParent(int parentid)throws Exception;
	
	//获取对应用户所对应的功能
	public List<FFunctionCustom> findUserFunc(FFunctionCustom fFunctionCustom)throws Exception;
}
