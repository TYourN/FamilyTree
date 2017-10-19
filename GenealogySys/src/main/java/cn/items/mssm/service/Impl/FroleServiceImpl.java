package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FroleMapper;
import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.service.FroleService;

@Service("FroleService")
public class FroleServiceImpl implements FroleService{

	/** 
	 * @ClassName: FroleServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月16日 下午2:46:59 
	 */
	
	@Autowired
	private FroleMapper froleMapper;

	@Override
	public int addRoleInfo(FRoleCustom fRoleCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return froleMapper.addRoleInfo(fRoleCustom);
	}

	@Override
	public int updateRoleInfo(FRoleCustom fRoleCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return froleMapper.updateRoleInfo(fRoleCustom);
	}

	@Override
	public int deleteRoleInfo(int roleid) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return froleMapper.deleteRoleInfo(roleid);
	}

	@Override
	public List<FRoleCustom> findRoleInfo() throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return froleMapper.findRoleInfo();
	}
	
	
}
