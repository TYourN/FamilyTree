package cn.items.mssm.service;

import cn.items.mssm.poCustom.FProfileCustom;

public interface FproService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年11月13日 下午12:49:26 
	 */
	
	//注册家族时插入一条对应的家族数据
	public int addFamPro(int dbid,String famlocal)throws Exception;
	
	//对家族简介信息进行修改
	public int updateFamPro(FProfileCustom fProfileCustom)throws Exception;
	
	//查找对应家族的家族简介信息
	public FProfileCustom findFamPro(int userid)throws Exception;
}
