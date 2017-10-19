package cn.items.mssm.service.Impl;

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
}
