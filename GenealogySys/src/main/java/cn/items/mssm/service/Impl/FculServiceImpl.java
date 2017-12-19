package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.mapper.FculMapper;
import cn.items.mssm.poCustom.FCulCustom;
import cn.items.mssm.service.FculService;

@Service("FculService")
public class FculServiceImpl implements FculService{

	/** 
	 * @ClassName: FculServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月26日 下午2:35:14 
	 */
	
	@Autowired
	private FculMapper fculMapper;

	@Override
	public List<FCulCustom> findFamCul() throws Exception {
		// TODO Auto-generated method stub
		return fculMapper.findFamCul();
	}

	@Override
	public int addFamCul(String title,String content,String type) throws Exception {
		// TODO Auto-generated method stub
		FCulCustom fCulCustom=new FCulCustom();
		fCulCustom.setTitle(title);
		fCulCustom.setContent(content);
		fCulCustom.setType(type);
		fCulCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		return fculMapper.addFamCul(fCulCustom);
	}

	@Override
	public int updateFamCul(int id,String title,String content,String type) throws Exception {
		// TODO Auto-generated method stub
		FCulCustom fCulCustom=new FCulCustom();
		fCulCustom.setCulid(Integer.valueOf(id));
		fCulCustom.setTitle(title);
		fCulCustom.setContent(content);
		fCulCustom.setType(type);
		return fculMapper.updateFamCul(fCulCustom);
	}

	@Override
	public FCulCustom findCulById(int culid) throws Exception {
		// TODO Auto-generated method stub
		return fculMapper.findCulById(culid);
	}

	@Override
	public int deleteFamCul(int culid) throws Exception {
		// TODO Auto-generated method stub
		return fculMapper.deleteFamCul(culid);
	}

	@Override
	public FCulCustom findAnCulByType(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return fculMapper.findAnCulByType(map);
	}	
}
