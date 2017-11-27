package cn.items.mssm.mapper;

import cn.items.mssm.poCustom.FProfileCustom;

public interface FproMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年11月13日 下午12:39:40 
	 */
	
	public int addFamPro(FProfileCustom fProfileCustom)throws Exception;
	
	public int updateFamPro(FProfileCustom fProfileCustom)throws Exception;
	
	public FProfileCustom findFamPro(int userid)throws Exception;
}
