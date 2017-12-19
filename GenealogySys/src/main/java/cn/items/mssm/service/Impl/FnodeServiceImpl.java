package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.mapper.FnodeMapper;
import cn.items.mssm.poCustom.FNodeCustom;
import cn.items.mssm.service.FnodeService;

@Service("FnodeService")
public class FnodeServiceImpl implements FnodeService{

	/** 
	 * @ClassName: FnodeServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月30日 上午9:54:16 
	 */
	
	@Autowired
	private FnodeMapper fnodeMapper;

	@Override
	public List<FNodeCustom> findNodeByCount(int count) throws Exception {
		// TODO Auto-generated method stub
		return fnodeMapper.findNodeByCount(count);
	}

	@Override
	public Object findNodeCount() throws Exception {
		// TODO Auto-generated method stub
		return fnodeMapper.findNodeCount();
	}

	@Override
	public int addNodeFrag(FNodeCustom fNodeCustom) throws Exception {
		// TODO Auto-generated method stub
		return fnodeMapper.addNodeFrag(fNodeCustom);
	}

	@Override
	public Object findMaxCount() throws Exception {
		// TODO Auto-generated method stub
		return fnodeMapper.findMaxCount();
	}

	@Override
	public int deleteNode(int count) throws Exception {
		// TODO Auto-generated method stub
		return fnodeMapper.deleteNode(count);
	}	
}
