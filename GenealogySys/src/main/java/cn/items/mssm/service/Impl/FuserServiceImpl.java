package cn.items.mssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FsnameMapper;
import cn.items.mssm.mapper.FuserMapper;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
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
	public int addUserInfo(FUserinfoCustom fUserinfoCustom,String serverId) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType(serverId);
		return FUserMapper.addUserInfo(fUserinfoCustom);
	}

	@Override
	public int findLatestUserInfo(String serverId) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType(serverId);
		return FUserMapper.findLatestUserInfo();
	}

	@Override
	public int addUser(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return FUserMapper.addUser(fUserCustom); //必须要切回从库
	}

	@Override
	public int updateUserInfo(FUserinfoCustom fUserinfoCustom,String serverId) throws Exception {
		// TODO Auto-generated method stub
		return FUserMapper.updateUserInfo(fUserinfoCustom);
	}

	@Override
	public int updateUser(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return FUserMapper.updateUser(fUserCustom);
	}

	@Override
	public int deleteUser(int userid) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return FUserMapper.deleteUser(userid);
	}

	@Override
	public int deleteUserInfo(int userinfoid,String serverId) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType(serverId);
		return FUserMapper.deleteUserInfo(userinfoid);
	}

	@Override
	public int findUserExit(FUserinfoCustom fUserinfoCustom, String serverId) throws Exception {
		// TODO Auto-generated method stub
	    DBContextHolder.setDBType(serverId);
		return FUserMapper.findUserExit(fUserinfoCustom);
	}
}
