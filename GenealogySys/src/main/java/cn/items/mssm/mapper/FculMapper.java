package cn.items.mssm.mapper;

import cn.items.mssm.poCustom.FCulCustom;

public interface FculMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月26日 下午2:28:46 
	 */
	
	public FCulCustom findFamCul()throws Exception;
	
	public int addFamCul(FCulCustom fCulCustom)throws Exception;
	
	public int updateFamCul(FCulCustom fCulCustom)throws Exception;
}
