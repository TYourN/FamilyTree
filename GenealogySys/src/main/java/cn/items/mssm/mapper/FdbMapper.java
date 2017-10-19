/**
 * 
 */
package cn.items.mssm.mapper;

import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;

/**
 * @author Administrator
 *
 */
public interface FdbMapper {

	/** 
	 * @ClassName: FdbMapper 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月10日 下午4:21:26 
	 */
	
	public int findDBCount(String famtitle) throws Exception;
	
	public int addDBInfo(FDatabaseCustom fDatabaseCustom)throws Exception;
	
	public int findLatestId()throws Exception;
	
	public int addManaFirst(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	public int findLatestUser()throws Exception;
	
	public int addUserInfo(FUserCustom fUserCustom)throws Exception;
}
