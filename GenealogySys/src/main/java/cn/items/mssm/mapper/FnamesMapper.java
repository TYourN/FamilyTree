package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FFamnamesCustom;

public interface FnamesMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月18日 上午10:44:32 
	 */
	
	public List<FFamnamesCustom> findAllName()throws Exception;
	
	public FFamnamesCustom findNameById(int fnameid)throws Exception;
	
	public FFamnamesCustom findNameDety(String title)throws Exception;
	
	public int addMainNames(FFamnamesCustom famnamesCustom)throws Exception;
	
	public int deleteMainNames(int fnameid)throws Exception;
	
	public int updateMainNames(FFamnamesCustom famnamesCustom)throws Exception;
}
