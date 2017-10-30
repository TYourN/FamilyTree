package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FNewsCustom;

public interface FnewsService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年10月17日 下午2:06:16 
	 */
	
	//查找出所有的新闻信息:主
	public List<FFamnewsCustom> findAllNews() throws Exception;
	
	//查询出一条新闻的具体内容:主
	public FFamnewsCustom findNewDety(int newsid)throws Exception;
	
	//主页插入一条新闻的内容：主
	public int addMainNews(FFamnewsCustom famnewsCustom)throws Exception;
	
	//删除一条主页新闻信息：主
	public int deleteMainNews(int newsid)throws Exception;
	
	//查询出所有的家族新闻
	public List<FNewsCustom> findFamNews()throws Exception;
	
	//根据Id查找出家族新闻的具体信息
	public FNewsCustom findFamNewsDety(int newsid)throws Exception;
	
	//增加一条对应家族的新闻信息
	public int addFamNews(String title,String Content,int operid,String url,String memo)throws Exception;
	
	//删除一条对应家族的新闻信息
	public int deleteFamNews(int newsid)throws Exception;
}
