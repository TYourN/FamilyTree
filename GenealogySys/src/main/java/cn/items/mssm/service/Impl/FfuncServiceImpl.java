package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FfuncMapper;
import cn.items.mssm.poCustom.FFunctionCustom;
import cn.items.mssm.service.FfuncService;

@Service("FfuncService")
public class FfuncServiceImpl implements FfuncService{

	/** 
	 * @ClassName: FfuncServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月16日 下午3:29:40 
	 */
	
	@Autowired
	private FfuncMapper ffuncMapper;

	@Override
	public List<FFunctionCustom> findAllfunc() throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.findAllfunc();
	}

	@Override
	public List<FFunctionCustom> findParents() throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.findParents();
	}

	@Override
	public int addFuncInfo(FFunctionCustom fFunctionCustom) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.addFuncInfo(fFunctionCustom);
	}

	@Override
	public FFunctionCustom findFuncById(int funcid) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.findFuncById(funcid);
	}

	@Override
	public int updateFunc(FFunctionCustom fFunctionCustom) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.updateFunc(fFunctionCustom);
	}

	@Override
	public int delFunc(int funcid) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.delFunc(funcid);
	}

	@Override
	public int delFuncParent(int parentid) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.delFuncParent(parentid);
	}

	@Override
	public List<FFunctionCustom> findUserFunc(FFunctionCustom fFunctionCustom) throws Exception {
		// TODO Auto-generated method stub
		return ffuncMapper.findUserFunc(fFunctionCustom);
	}
	
	
}
