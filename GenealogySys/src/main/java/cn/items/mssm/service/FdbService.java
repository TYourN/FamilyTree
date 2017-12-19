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
	
	//查找符合条件的数据库信息 :主
    public int findDBCount(String famtitle) throws Exception;
	
    //插入家族的数据库信息 ：主
	public int addDBInfo(FDatabaseCustom fDatabaseCustom)throws Exception;
	
	//查找出最新添加的一条数据库信息的Id ：主
	public int findLatestId()throws Exception;
	
	//第一次注册数据库时增加管理员 ：从
	public int addManaFirst(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	//查找出最新添加的一条管理员信息的Id：从
	public int findLatestUser()throws Exception;
	
	//增加人员的登录信息：主
	public int addUserInfo(FUserCustom fUserCustom)throws Exception;
	
	//获取对应Id的数据库信息
	public FDatabaseCustom findDbById(int databaseid)throws Exception;
}
