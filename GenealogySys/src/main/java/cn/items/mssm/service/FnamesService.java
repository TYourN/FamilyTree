package cn.items.mssm.service;

import cn.items.mssm.poCustom.FFamnamesCustom;

public interface FnamesService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月18日 上午10:58:31 
	 */
	public FFamnamesCustom findNameDety(String title)throws Exception;
	
	//主页插入一条姓氏文化的内容 
	public int addMainNames(FFamnamesCustom famnamesCustom)throws Exception;
	
	//主页删除一条姓氏文化的内容
	public int deleteMainNames(int fnameid)throws Exception;
}
