package cn.items.mssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.mapper.FnewsMapper;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FNewsCustom;
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
		return fnewsMapper.findAllNews();
	}

	@Override
	public FFamnewsCustom findNewDety(int newsid) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.findNewDety(newsid);
	}

	@Override
	public int addMainNews(FFamnewsCustom famnewsCustom) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.addMainNews(famnewsCustom);
	}

	@Override
	public int deleteMainNews(int newsid) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.deleteMainNews(newsid);
	}

	@Override
	public int addFamNews(String title,String Content,int operid,String url,String memo) throws Exception {
		// TODO Auto-generated method stub
		FNewsCustom fNewsCustom=new FNewsCustom();
		fNewsCustom.setTitle(title);
		fNewsCustom.setContent(Content);
		fNewsCustom.setOperid(operid);
		fNewsCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		fNewsCustom.setUrl(url);
		fNewsCustom.setMemo(memo);
		return fnewsMapper.addFamNews(fNewsCustom);
	}

	@Override
	public int deleteFamNews(int newsid) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.deleteFamNews(newsid);
	}

	@Override
	public List<FNewsCustom> findFamNews() throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.findFamNews();
	}

	@Override
	public FNewsCustom findFamNewsDety(int newsid) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.findFamNewsDety(newsid);
	}

	@Override
	public int updateFamNews(FNewsCustom fNewsCustom) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.updateFamNews(fNewsCustom);
	}

	@Override
	public List<FFamnewsCustom> findNewsTop() throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.findNewsTop();
	}

	@Override
	public int updateMainNews(FFamnewsCustom famnewsCustom) throws Exception {
		// TODO Auto-generated method stub
		return fnewsMapper.updateMainNews(famnewsCustom);
	}	
}
