package cn.items.mssm.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FostsMapper;
import cn.items.mssm.poCustom.FPostsCustom;
import cn.items.mssm.service.FostsService;

@Service("FostsService")
public class FostsServiceImpl implements FostsService{

	/** 
	 * @ClassName: FostsServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月15日 下午1:48:50 
	 */
	
	@Autowired
	private FostsMapper fostsMapper;

	@Override
	public List<FPostsCustom> findReportsTop() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findReportsTop();
	}

	@Override
	public int findTotie() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findTotie();
	}

	@Override
	public int findYestie() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findYestie();
	}

	@Override
	public int findAlltie() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findAlltie();
	}

	@Override
	public int findAllreports() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findAllreports();
	}

	@Override
	public Date findLatesttie() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findLatesttie();
	}

	@Override
	public Date findLatestReport() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findLatestReport();
	}

	@Override
	public List<FPostsCustom> findAlltieInfo() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findAlltieInfo();
	}

	@Override
	public List<FPostsCustom> findAllReportsInfo() throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findAllReportsInfo();
	}

	@Override
	public FPostsCustom findDetialById(int postid) throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findDetialById(postid);
	}

	@Override
	public int deleteDetialById(int postid) throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.deleteDetialById(postid);
	}

	@Override
	public int updateRepById(FPostsCustom fPostsCustom) throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.updateRepById(fPostsCustom);
	}

	@Override
	public int addPosts(FPostsCustom fPostsCustom) throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.addPosts(fPostsCustom);
	}

	@Override
	public List<FPostsCustom> findInfoBy(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return fostsMapper.findInfoBy(map);
	}
	
	
}
