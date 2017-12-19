package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.mapper.FhonMapper;
import cn.items.mssm.poCustom.FHonCustom;
import cn.items.mssm.service.FhonService;

@Service("FhonService")
public class FhonServiceImpl implements FhonService{

	/** 
	 * @ClassName: FhonServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月25日 下午2:34:18 
	 */
	@Autowired
	private FhonMapper fhonMapper;

	@Override
	public List<FHonCustom> findFamHon() throws Exception {
		// TODO Auto-generated method stub			
		return fhonMapper.findFamHon();
	}

	@Override
	public FHonCustom findHonDety(int honid) throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.findHonDety(honid);
	}

	@Override
	public int addFamHon(FHonCustom fHonCustom) throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.addFamHon(fHonCustom);
	}

	@Override
	public int deleteFamHon(int honid) throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.deleteFamHon(honid);
	}

	@Override
	public List<FHonCustom> findAllHon() throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.findAllHon();
	}

	@Override
	public int updateFamHon(FHonCustom fHonCustom) throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.updateFamHon(fHonCustom);
	}

	@Override
	public List<FHonCustom> findSomeHon(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return fhonMapper.findSomeHon(map);
	}
		
}
