package cn.items.mssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FproMapper;
import cn.items.mssm.poCustom.FProfileCustom;
import cn.items.mssm.service.FproService;

@Service("FproService")
public class FproServiceImpl implements FproService{

	/** 
	 * @ClassName: FproServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月13日 下午12:50:02 
	 */
	@Autowired
	private FproMapper fproMapper;

	@Override
	public int addFamPro(int dbid,String famlocal) throws Exception {
		// TODO Auto-generated method stub
		FProfileCustom fProfileCustom=new FProfileCustom();
		fProfileCustom.setDbid(dbid);
		fProfileCustom.setFamlocal(famlocal);
		return fproMapper.addFamPro(fProfileCustom);
	}

	@Override
	public int updateFamPro(FProfileCustom fProfileCustom) throws Exception {
		// TODO Auto-generated method stub
		return fproMapper.updateFamPro(fProfileCustom);
	}

	@Override
	public FProfileCustom findFamPro(int userid) throws Exception {
		// TODO Auto-generated method stub
		return fproMapper.findFamPro(userid);
	}
		
}
