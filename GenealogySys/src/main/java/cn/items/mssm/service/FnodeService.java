package cn.items.mssm.service;

import java.util.List;

import cn.items.mssm.poCustom.FNodeCustom;

public interface FnodeService {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.service 
	 * @author: Administrator   
	 * @date: 2017年11月30日 上午9:54:02 
	 */
	
	//按对应条件获取到家谱信息 
	public List<FNodeCustom> findNodeByCount(int count)throws Exception;
	
	//获取到最大的count
	public Object findNodeCount()throws Exception;
	
	//增加一段家谱树
	public int addNodeFrag(FNodeCustom fNodeCustom)throws Exception;
	
	//查找家谱中最大的Count
	public Object findMaxCount()throws Exception;
	
	//删除对应的族谱节点
	public int deleteNode(int count)throws Exception;
}
