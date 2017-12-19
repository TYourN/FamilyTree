package cn.items.mssm.service;

import java.util.List;
import java.util.Map;

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
	public List<FCulCustom> findFamCul()throws Exception;
	
	//上传家族文化 
	public int addFamCul(String title,String content,String type)throws Exception;
	
	//更新家族文化
	public int updateFamCul(int id,String title,String content,String type)throws Exception;
	
	//根据Id查找对应的家族文化
	public FCulCustom findCulById(int culid)throws Exception;
	
	//删除一条家族文化
	public int deleteFamCul(int culid)throws Exception;
	
	//查找出对应条件的文化 
	public FCulCustom findAnCulByType(Map<String,String> map)throws Exception;
}
