/**
 * 
 */
package cn.items.mssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FdbMapper;
import cn.items.mssm.mapper.FsnameMapper;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.service.FdbService;

/**
 * @author Administrator
 *
 */
@Service("FdbService")
public class FdbServiceImpl implements FdbService{

	/** 
	 * @ClassName: FdbServcieImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��12�� ����9:21:42 
	 */
	
	@Autowired
	private FdbMapper fdbMapper;

	@Override
	public int findDBCount(String famtitle) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fdbMapper.findDBCount(famtitle);
	}

	@Override
	public int addDBInfo(FDatabaseCustom fDatabaseCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fdbMapper.addDBInfo(fDatabaseCustom);
	}

	@Override
	public int findLatestId() throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fdbMapper.findLatestId();
	}

	@Override
	public int findLatestUser(String serverId) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType(serverId);
		return fdbMapper.findLatestUser();
	}

	@Override
	public int addUserInfo(FUserCustom fUserCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fdbMapper.addUserInfo(fUserCustom);
	}

	@Override
	public int addManaFirst(FUserinfoCustom fUserinfoCustom,String serverId) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType(serverId);
		return fdbMapper.addManaFirst(fUserinfoCustom);
	}
	
	
	
}
