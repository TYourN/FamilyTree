package cn.items.mssm.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.mapper.FhomePageMapper;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FPicturesCustom;
import cn.items.mssm.service.FhomePageService;

@Service("FhomePageService")
public class FHPServiceImpl implements FhomePageService{

	/** 
	 * @ClassName: FHPServiceImpl 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月16日 下午7:00:57 
	 */
	
	@Autowired
	private FhomePageMapper fHomePageMapper;

	@Override
	public List<String> findFirstPics() throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fHomePageMapper.findFirstPics();
	}

	@Override
	public String findSecondPic(String memo) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fHomePageMapper.findSecondPic(memo);
	}

	@Override
	public List<FFamnewsCustom> findHPNews() throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fHomePageMapper.findHPNews();
	}

	@Override
	public int addFSPics(String title, String url, String memo, String flag) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		FPicturesCustom fPicturesCustom=new FPicturesCustom();
		fPicturesCustom.setTitle(title);
		fPicturesCustom.setUrl(url);
		fPicturesCustom.setMemo(memo);
		fPicturesCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		fPicturesCustom.setFlag(flag);
		return fHomePageMapper.addFSPics(fPicturesCustom);
	}

	@Override
	public int deleteMianPics(int picid) throws Exception {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("0");
		return fHomePageMapper.deleteMianPics(picid);
	}

	@Override
	public List<String> findPicsByAu(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return fHomePageMapper.findPicsByAu(map);
	}

	@Override
	public int deletePicsByFLag(String flag) throws Exception {
		// TODO Auto-generated method stub
		return fHomePageMapper.deletePicsByFLag(flag);
	}
}
