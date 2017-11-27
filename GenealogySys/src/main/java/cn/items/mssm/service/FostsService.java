package cn.items.mssm.service;

import java.util.Date;
import java.util.List;

import cn.items.mssm.poCustom.FPostsCustom;

public interface FostsService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年11月15日 下午1:48:38 
	 */
	
	//查找出最新更新的几条家族公告信息
	public List<FPostsCustom> findReportsTop()throws Exception;
	
	//查询出今天的所有帖子数目
	public int findTotie()throws Exception;
	
	//查询出昨天的所有帖子数目
	public int findYestie()throws Exception;
	
	//查询出所有的水贴数目
	public int findAlltie()throws Exception;
	
	//查询出所有的公告数目 
	public int findAllreports()throws Exception;
	
	//查找出水贴中最晚发帖的时间
	public Date findLatesttie()throws Exception;
	
	//查找出公告中最晚发帖的时间
	public Date findLatestReport()throws Exception;
	
	//查找出所有的水贴相关信息 
	public List<FPostsCustom> findAlltieInfo() throws Exception;
	
	//查找出所有的水贴相关信息 
	public List<FPostsCustom> findAllReportsInfo()throws Exception;
	
	//查找出对应id的公告或水贴的信息
	public FPostsCustom findDetialById(int postid)throws Exception;
	
	//删除一条对应的水贴或者公告
	public int deleteDetialById(int postid)throws Exception;
	
	//更新对应id的公告内容
	public int updateRepById(FPostsCustom fPostsCustom)throws Exception;
	
	//发布公告
	public int addPosts(FPostsCustom fPostsCustom)throws Exception;
}
