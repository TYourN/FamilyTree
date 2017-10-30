package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FNewsCustom;

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
	
	public int addMainNews(FFamnewsCustom famnewsCustom)throws Exception;
	
	public int deleteMainNews(int newsid)throws Exception;
	
	public int addFamNews(FNewsCustom fNewsCustom)throws Exception;
	
	public int deleteFamNews(int newsid)throws Exception;
	
	public List<FNewsCustom> findFamNews()throws Exception;
	
	public FNewsCustom findFamNewsDety(int newsid)throws Exception;
}
