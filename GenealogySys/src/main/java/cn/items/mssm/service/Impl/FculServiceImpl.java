package cn.items.mssm.service.Impl;

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
	public FCulCustom findFamCul() throws Exception {
		// TODO Auto-generated method stub
		return fculMapper.findFamCul();
	}

	@Override
	public int addFamCul(String title,String content) throws Exception {
		// TODO Auto-generated method stub
		FCulCustom fCulCustom=new FCulCustom();
		fCulCustom.setTitle(title);
		fCulCustom.setContent(content);
		fCulCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		return fculMapper.addFamCul(fCulCustom);
	}

	@Override
	public int updateFamCul(String title,String content) throws Exception {
		// TODO Auto-generated method stub
		FCulCustom fCulCustom=new FCulCustom();
		fCulCustom.setTitle(title);
		fCulCustom.setContent(content);
		return fculMapper.updateFamCul(fCulCustom);
	}	
}
