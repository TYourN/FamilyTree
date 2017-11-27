package cn.items.mssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FrepliesMapper;
import cn.items.mssm.service.FrepliesService;

@Service("FrepliesService")
public class FrepliesServiceImpl implements FrepliesService{

	/** 
	 * @ClassName: FrepliesServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月15日 下午4:11:26 
	 */
	@Autowired
	private FrepliesMapper frepliesMapper;

	@Override
	public int findAllreplies() throws Exception {
		// TODO Auto-generated method stub
		return frepliesMapper.findAllreplies();
	}
}
