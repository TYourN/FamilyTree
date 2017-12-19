package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FrepliesMapper;
import cn.items.mssm.poCustom.FRepliesCustom;
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

	@Override
	public int findReNumById(int postid) throws Exception {
		// TODO Auto-generated method stub
		return frepliesMapper.findReNumById(postid);
	}

	@Override
	public List<FRepliesCustom> findPRepliesById(int postid) throws Exception {
		// TODO Auto-generated method stub
		return frepliesMapper.findPRepliesById(postid);
	}

	@Override
	public List<FRepliesCustom> findCRepliesById(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return frepliesMapper.findCRepliesById(map);
	}

	@Override
	public int addReplies(FRepliesCustom fRepliesCustom) throws Exception {
		// TODO Auto-generated method stub
		return frepliesMapper.addReplies(fRepliesCustom);
	}
}
