package cn.items.mssm.mapper;

import java.util.Date;
import java.util.List;

import cn.items.mssm.poCustom.FPostsCustom;

public interface FostsMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年11月15日 下午1:47:31 
	 */
	
	public List<FPostsCustom> findReportsTop()throws Exception;
	
	public int findTotie()throws Exception;
	
	public int findYestie()throws Exception;
	
	public int findAlltie()throws Exception;
	
	public int findAllreports()throws Exception;
	
	public Date findLatesttie()throws Exception;
	
	public Date findLatestReport()throws Exception;
	
	public List<FPostsCustom> findAlltieInfo() throws Exception;
	
	public List<FPostsCustom> findAllReportsInfo()throws Exception;
	
	public FPostsCustom findDetialById(int postid)throws Exception;
	
	public int deleteDetialById(int postid)throws Exception;
	
	public int updateRepById(FPostsCustom fPostsCustom)throws Exception;
	
	public int addPosts(FPostsCustom fPostsCustom)throws Exception;
}
