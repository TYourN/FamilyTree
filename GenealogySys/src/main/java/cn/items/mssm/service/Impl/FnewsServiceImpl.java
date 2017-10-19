package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FnewsMapper;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.service.FnewsService;

@Service("FnewsService")
public class FnewsServiceImpl implements FnewsService{

	/** 
	 * @ClassName: FnewsServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月17日 下午2:09:29 
	 */
	@Autowired
	private FnewsMapper fnewsMapper;

	@Override
	public List<FFamnewsCustom> findAllNews() throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fnewsMapper.findAllNews();
	}

	@Override
	public FFamnewsCustom findNewDety(int newsid) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fnewsMapper.findNewDety(newsid);
	}	
}
