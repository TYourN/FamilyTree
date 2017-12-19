/**
 * 
 */
package cn.items.mssm.mapper;

import java.util.List;
import java.util.Map;

import cn.items.mssm.po.FAdmin;
import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;

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
	
	public int deleteUser(int userinfoid)throws Exception;

	public int deleteUserInfo(int userinfoid)throws Exception;
	
	public int findUserExit(FUserinfoCustom fUserinfoCustom)throws Exception;
	
	public int findAllCount(Map<String, Object> map)throws Exception;
	
	public int findFamCount()throws Exception;
	
	public int findExCount(Map<String, String> map)throws Exception;
	
	public int findFamSex(Map<String, String> map)throws Exception;
	
	public int findFamAge(Map<String, Integer> map)throws Exception;
	
	public List<FUserinfoCustom> findAllperson()throws Exception;
	
	public FUserinfoCustom findpersonById(int userinfoid)throws Exception;
	
	public List<FUserroleCustom> findURole(int userid)throws Exception;
	
	public int delURole(int userid)throws Exception; 
	
	public int addURole(FUserroleCustom fUserroleCustom)throws Exception;
	
	public FUserCustom findUserE(FUserCustom fUserCustom)throws Exception;
	
	public FUserCustom findUserById(int userid)throws Exception;
	
	public FAdminCustom findAdminE(FAdminCustom fAdminCustom)throws Exception;
	
	public int findLatestUser()throws Exception;
	
	public int findUserIdByAu(Map<String,Integer> map)throws Exception;
}
