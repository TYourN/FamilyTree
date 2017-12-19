package cn.items.mssm.mapper;

import java.util.List;
import java.util.Map;

import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FPicturesCustom;

public interface FhomePageMapper {

	/**   
	 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
	 * 
	 * @Package: cn.items.mssm.mapper 
	 * @author: Administrator   
	 * @date: 2017年10月16日 下午4:18:50 
	 */
	
	public List<String> findFirstPics() throws Exception;
	
	public String findSecondPic(String memo) throws Exception;
	
	public List<FFamnewsCustom> findHPNews() throws Exception;
	
	public int addFSPics(FPicturesCustom fPicturesCustom) throws Exception;	
	
	public int deleteMianPics(int picid)throws Exception;
	
	public List<String> findPicsByAu(Map<String,String> map)throws Exception;
	
	public int deletePicsByFLag(String flag)throws Exception;
}
