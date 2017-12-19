package cn.items.mssm.mapper;

import java.util.List;
import java.util.Map;

import cn.items.mssm.poCustom.FRepliesCustom;

public interface FrepliesMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年11月15日 下午4:01:17 
	 */
	
	public int findAllreplies()throws Exception;
	
	public int findReNumById(int postid)throws Exception;
	
	public List<FRepliesCustom> findPRepliesById(int postid)throws Exception;
	
	public List<FRepliesCustom> findCRepliesById(Map<String,Object> map)throws Exception;
	
	public int addReplies(FRepliesCustom fRepliesCustom)throws Exception;
}
