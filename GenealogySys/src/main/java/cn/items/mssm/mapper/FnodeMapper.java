package cn.items.mssm.mapper;

import java.util.List;

import cn.items.mssm.poCustom.FNodeCustom;

public interface FnodeMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年11月30日 上午9:53:07 
	 */
	
	public List<FNodeCustom> findNodeByCount(int count)throws Exception;
	
	public Object findNodeCount()throws Exception;
	
	public int addNodeFrag(FNodeCustom fNodeCustom)throws Exception;
	
	public Object findMaxCount()throws Exception;
	
	public int deleteNode(int count)throws Exception;
}
