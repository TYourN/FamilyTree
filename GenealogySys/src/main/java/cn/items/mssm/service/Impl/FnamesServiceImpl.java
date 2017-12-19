package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FnamesMapper;
import cn.items.mssm.poCustom.FFamnamesCustom;
import cn.items.mssm.service.FnamesService;

@Service("FnamesService")
public class FnamesServiceImpl implements FnamesService{

	/** 
	 * @ClassName: FnamesServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月18日 上午10:59:01 
	 */
	
	@Autowired
	private FnamesMapper fnamesMapper;

	@Override
	public FFamnamesCustom findNameDety(String title) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fnamesMapper.findNameDety(title);
	}

	@Override
	public int addMainNames(FFamnamesCustom famnamesCustom) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fnamesMapper.addMainNames(famnamesCustom);
	}

	@Override
	public int deleteMainNames(int fnameid) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fnamesMapper.deleteMainNames(fnameid);
	}

	@Override
	public List<FFamnamesCustom> findAllName() throws Exception {
		// TODO Auto-generated method stub
		return fnamesMapper.findAllName();
	}

	@Override
	public FFamnamesCustom findNameById(int fnameid) throws Exception {
		// TODO Auto-generated method stub
		return fnamesMapper.findNameById(fnameid);
	}

	@Override
	public int updateMainNames(FFamnamesCustom famnamesCustom) throws Exception {
		// TODO Auto-generated method stub
		return fnamesMapper.updateMainNames(famnamesCustom);
	}		
}
