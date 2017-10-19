package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FPicturesCustom;

public interface FhomePageService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午6:59:58 
	 */
	
	public List<String> findFirstPics() throws Exception;
	
	public String findSecondPic(String memo) throws Exception;
	
	public List<FFamnewsCustom> findHPNews() throws Exception;
}
