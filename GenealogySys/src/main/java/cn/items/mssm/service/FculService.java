package cn.items.mssm.service;

import cn.items.mssm.poCustom.FCulCustom;

public interface FculService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月26日 下午2:34:34 
	 */
	//查找出所有的文化
	public FCulCustom findFamCul()throws Exception;
	
	//上传家族文化 
	public int addFamCul(String title,String content)throws Exception;
	
	//更新家族文化
	public int updateFamCul(String title,String content)throws Exception;
}
