package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FFamnamesCustom;

public interface FnamesService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月18日 上午10:58:31 
	 */
	
	//获取所有的姓氏文化
	public List<FFamnamesCustom> findAllName()throws Exception;
	
	//查找出对应Id的姓氏文化
	public FFamnamesCustom findNameById(int fnameid)throws Exception;
	
	public FFamnamesCustom findNameDety(String title)throws Exception;
	
	//主页插入一条姓氏文化的内容 
	public int addMainNames(FFamnamesCustom famnamesCustom)throws Exception;
	
	//主页删除一条姓氏文化的内容
	public int deleteMainNames(int fnameid)throws Exception;
	
	//修改一条姓氏文化
	public int updateMainNames(FFamnamesCustom famnamesCustom)throws Exception;
}
