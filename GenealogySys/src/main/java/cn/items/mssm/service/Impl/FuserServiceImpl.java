package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FuserMapper;
import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;
import cn.items.mssm.service.FuserService;

@Service("FuserService")
public class FuserServiceImpl implements FuserService{


	/** 
	 * @ClassName: FuserServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月13日 下午2:56:55 
	 */
	
	@Autowired
	private FuserMapper FUserMapper;
	
	@Override
	public int addUserInfo(FUserinfoCustom fUserinfoCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.addUserInfo(fUserinfoCustom);
	}

	@Override
	public int findLatestUserInfo() throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findLatestUserInfo();
	}

	@Override
	public int addUser(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.addUser(fUserCustom); //必须要切回从库
	}

	@Override
	public int updateUserInfo(FUserinfoCustom fUserinfoCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.updateUserInfo(fUserinfoCustom);
	}

	@Override
	public int updateUser(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.updateUser(fUserCustom);
	}

	@Override
	public int deleteUser(int userinfoid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.deleteUser(userinfoid);
	}

	@Override
	public int deleteUserInfo(int userinfoid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.deleteUserInfo(userinfoid);
	}

	@Override
	public int findUserExit(FUserinfoCustom fUserinfoCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findUserExit(fUserinfoCustom);
	}

	@Override
	public int findAllCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findAllCount(map);
	}

	@Override
	public int findFamCount() throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findFamCount();
	}

	@Override
	public int findExCount(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findExCount(map);
	}

	@Override
	public int findFamSex(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findFamSex(map);
	}

	@Override
	public int findFamAge(Map<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findFamAge(map);
	}

	@Override
	public List<FUserinfoCustom> findAllperson() throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findAllperson();
	}

	@Override
	public FUserinfoCustom findpersonById(int userinfoid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findpersonById(userinfoid);
	}

	@Override
	public List<FUserroleCustom> findURole(int userid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findURole(userid);
	}

	@Override
	public int delURole(int userid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.delURole(userid);
	}

	@Override
	public int addURole(FUserroleCustom fUserroleCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.addURole(fUserroleCustom);
	}

	@Override
	public FUserCustom findUserE(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findUserE(fUserCustom);
	}

	@Override
	public FUserCustom findUserById(int userid) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findUserById(userid);
	}

	@Override
	public FAdminCustom findAdminE(FAdminCustom fAdminCustom) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findAdminE(fAdminCustom);
	}

	@Override
	public int findLatestUser() throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findLatestUser();
	}

	@Override
	public int findUserIdByAu(Map<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.findUserIdByAu(map);
	}
}
