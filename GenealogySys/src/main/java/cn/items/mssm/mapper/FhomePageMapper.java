package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;

public interface FhomePageMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午4:18:50 
	 */
	
	public List<String> findFirstPics() throws Exception;
	
	public String findSecondPic(String memo) throws Exception;
	
	public List<FFamnewsCustom> findHPNews() throws Exception;
}
