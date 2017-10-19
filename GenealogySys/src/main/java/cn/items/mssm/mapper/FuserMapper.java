/**
 * 
 */
package cn.items.mssm.mapper;

import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;

/**
 * @author Administrator
 *
 */
public interface FuserMapper {

	/** 
	 * @ClassName: FuserMapper 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��13�� ����2:30:35 
	 */
	
	public int addUserInfo(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	public int findLatestUserInfo()throws Exception;
	
	public int addUser(FUserCustom fUserCustom)throws Exception;
	
	public int updateUserInfo(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	public int updateUser(FUserCustom fUserCustom)throws Exception;
	
	public int deleteUser(int userid)throws Exception;

	public int deleteUserInfo(int userinfoid)throws Exception;
	
	public int findUserExit(FUserinfoCustom fUserinfoCustom)throws Exception;
}
