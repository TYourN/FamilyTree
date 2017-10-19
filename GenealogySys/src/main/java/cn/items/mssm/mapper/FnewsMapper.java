package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;

public interface FnewsMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月17日 下午1:53:06 
	 */
	
	public List<FFamnewsCustom> findAllNews() throws Exception;
	
	public FFamnewsCustom findNewDety(int newsid)throws Exception;
}
