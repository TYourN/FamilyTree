package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FsnameMapper;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.service.FSnameService;

@Service("FSnameService")
public class FSnameSImpl implements FSnameService{
	
	@Autowired
	private FsnameMapper fSnameMapper;

	@Override
	public List<FDatabaseCustom> findAllDataBase() throws Exception {
		// TODO Auto-generated method stub
		return fSnameMapper.findAllDataBase();
	}
	
}
