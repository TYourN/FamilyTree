package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;

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
	
	public int addFSPics(String title,String url,String memo,String flag) throws Exception;	

	public int deleteMianPics(int picid)throws Exception;
}
