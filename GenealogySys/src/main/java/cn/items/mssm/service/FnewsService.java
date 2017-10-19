package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;

public interface FnewsService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月17日 下午2:06:16 
	 */
	
	public List<FFamnewsCustom> findAllNews() throws Exception;
	
	public FFamnewsCustom findNewDety(int newsid)throws Exception;
}
