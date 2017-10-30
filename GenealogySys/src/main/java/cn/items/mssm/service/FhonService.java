package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FHonCustom;

public interface FhonService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月25日 下午2:32:04 
	 */
	
	//读取对应家族所有的荣誉图片和Id
	public List<FHonCustom> findFamHon()throws Exception;
	
	//根据Id读取对应家族的荣誉文字信息
	public List<FHonCustom> findHonDety(int honid)throws Exception;
	
	//增加对应家族荣誉 
	public int addFamHon(FHonCustom fHonCustom)throws Exception;
	
	//删除对应Id的对应家族的荣誉
	public int deleteFamHon(int honid)throws Exception;
}
