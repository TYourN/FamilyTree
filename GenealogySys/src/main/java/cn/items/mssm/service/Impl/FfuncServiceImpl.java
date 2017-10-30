package cn.items.mssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FfuncMapper;
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
	
	
}
