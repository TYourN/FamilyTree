package cn.items.mssm.service;

import java.util.List;
import java.util.Map;

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
	
	//读取所有对应条件的公共图片资源
	public List<String> findPicsByAu(Map<String,String> map)throws Exception;
	
	//删除对应Flag的图片
	public int deletePicsByFLag(String flag)throws Exception;
}
