package cn.items.mssm.service;

import java.util.List;
import java.util.Map;

import cn.items.mssm.poCustom.FRepliesCustom;

public interface FrepliesService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年11月15日 下午4:10:39 
	 */
	//查找出所有的回复数目 
	public int findAllreplies()throws Exception;
	
	//查询出对应帖子的回复数目
	public int findReNumById(int postid)throws Exception;
	
	//查询出对应帖子的主回复（回复的ParentId为0）
	public List<FRepliesCustom> findPRepliesById(int postid)throws Exception;
	
	//查询出对应回复的子回复 
	public List<FRepliesCustom> findCRepliesById(Map<String,Object> map)throws Exception;
	
	//添加一条回复
	public int addReplies(FRepliesCustom fRepliesCustom)throws Exception;
}
