/**
 * 
 */
package cn.items.mssm.service;

import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;

/**
 * @author Administrator
 *
 */
public interface FdbService {

	/** 
	 * @ClassName: FdbService 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��12�� ����9:21:16 
	 */
	
    public int findDBCount(String famtitle) throws Exception;
	
	public int addDBInfo(FDatabaseCustom fDatabaseCustom)throws Exception;
	
	public int findLatestId()throws Exception;
	
	public int addManaFirst(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	public int findLatestUser()throws Exception;
	
	public int addUserInfo(FUserCustom fUserCustom)throws Exception;
}
