package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FHonCustom;

public interface FhonMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月25日 下午2:19:08 
	 */
	
	public List<FHonCustom> findFamHon()throws Exception;
	
	public List<FHonCustom> findHonDety(int honid)throws Exception;
	
	public int addFamHon(FHonCustom fHonCustom)throws Exception;
	
	public int deleteFamHon(int honid)throws Exception;
}
