package cn.items.mssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.ImgUtil;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.PropertiesUtil;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.entity.TotalPrompt;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FNewsCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FnewsService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fnews")
public class FnewsController {

	/** 
	 * @ClassName: FnewsController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月17日 下午2:11:49 
	 */
	@Resource
	private FnewsService fnewsService;
	
	@Resource
	private FuserService fuserService;
	
	public static TotalPrompt totalPrompt=new TotalPrompt();
	
	public static PicsSave pics=new PicsSave();
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/
	
	//获取主页的前几条信息
	@ResponseBody
	@RequestMapping("/findNewsTop")
	public List<Map<String,Object>> findNewsTop(HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<Map<String,Object>> newslist=new ArrayList<>();
		List<FFamnewsCustom> list=fnewsService.findNewsTop();
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=new HashMap<>();
			map.put("id",list.get(i).getNewsid());
			map.put("title",list.get(i).getTitle());
			map.put("img",list.get(i).getUrl());
			map.put("date",StringUtil.changeTime(list.get(i).getOpertime()));
			newslist.add(map);
		}
		return newslist;		
	}
	
	//找出所有的主页的新闻
	@ResponseBody
	@RequestMapping("/findNewsAll")
	public Map<String, Object> findNewsAll(@RequestParam int pages,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		PageHelper.startPage(pages, 10);
		Map<String, Object> map=new HashMap<>();
		List<FFamnewsCustom> list=fnewsService.findAllNews();
		PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
		List<Map<String,Object>> newslist=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=new HashMap<>();
			m.put("newid",list.get(i).getNewsid());
			m.put("content",list.get(i).getContent());
			m.put("title",list.get(i).getTitle());
			m.put("memo",list.get(i).getMemo());
			m.put("opertime",StringUtil.changeTime(list.get(i).getOpertime()));
			newslist.add(m);
		}
		map.put("pages",pageInfo.getPages());
		map.put("locate",totalPrompt.newsObject());
		map.put("Content",newslist);
		return map;		
	}
	
	//根据Id查找新闻的具体内容
	@ResponseBody
	@RequestMapping("/findNewDety")
	public Map<String, Object> findNewDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnewsCustom famnewsCustom=fnewsService.findNewDety(newsid);
		Map<String,Object> map=new HashMap<>();
		map.put("title",famnewsCustom.getTitle());
		map.put("time", StringUtil.changeTime(famnewsCustom.getOpertime()));
		map.put("locate",totalPrompt.newsObject());
		map.put("content",famnewsCustom.getContent());
		return map;		
	}
	
	//上传展示主页的新闻
	@ResponseBody
	@RequestMapping("/addMainNews")
	public int addMainNews(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		//新图片名=获取时间戳+图片扩展名
		List<String> list=ImgUtil.getImgName(map.get("content"));
    	String url=null;
    	if(list.size()!=0){
    		String name=StringUtil.Cut(list.get(0)); 
    		url=pics.saveFileString(name);
    	}else{
    		url="http://oyepy0lel.bkt.clouddn.com/newLog.jpg";
    	}
    	
		FFamnewsCustom famnewsCustom=new FFamnewsCustom();
		famnewsCustom.setTitle(map.get("title"));
		famnewsCustom.setContent(PicsSave.uploadContent(map.get("content")));
		famnewsCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		famnewsCustom.setMemo(map.get("memo"));
		famnewsCustom.setUrl(url);
		
		int flag=fnewsService.addMainNews(famnewsCustom);
		
		return flag;		
	}
	
	//删除主页主页的一条新闻
    @ResponseBody
    @RequestMapping("/deleteMainNews")
    public int deleteMainNews(@RequestParam int newsid,HttpServletResponse res)throws Exception{
    	DBContextHolder.setDBType("0");
    	int flag=fnewsService.deleteMainNews(newsid);
		return flag;   	
    }
    
    //更新主页的一条新闻
    @ResponseBody
    @RequestMapping("/updateMainNews")
    public int updateMainNews(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
    	DBContextHolder.setDBType("0");
    	List<String> list=ImgUtil.getImgName(map.get("content"));
    	String url=null;
    	if(list.size()!=0){
    		String name=StringUtil.Cut(list.get(0)); 
    		url=pics.saveFileString(name);
    	}else{
    		url="http://oyepy0lel.bkt.clouddn.com/newLog.jpg";
    	}
    	
    	FFamnewsCustom famnewsCustom=new FFamnewsCustom();
		famnewsCustom.setTitle(map.get("title"));
		famnewsCustom.setContent(PicsSave.uploadContent(map.get("content")));
		famnewsCustom.setMemo(map.get("memo"));
		famnewsCustom.setUrl(url);
		famnewsCustom.setNewsid(Integer.parseInt(map.get("newsid")));
		
		int flag=fnewsService.updateMainNews(famnewsCustom);
		return flag;   	
    }
    
    //查询出所有的主页新闻：管理端
    @ResponseBody
	@RequestMapping("/findMaNews")
    public Map<String,Object> findMaNews(@RequestParam int pageSize,@RequestParam int pageNumber,HttpSession session,HttpServletResponse res)throws Exception{
    	DBContextHolder.setDBType("0");
    	PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FFamnewsCustom> list=fnewsService.findAllNews();
		PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setCreatetime(StringUtil.changeTime(list.get(i).getOpertime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;  	
    }
    
    //根据Id查找公共新闻的具体内容:管理端
    @ResponseBody
	@RequestMapping("/findMaNewDety")
    public FFamnewsCustom findMaNewDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnewsCustom famnewsCustom=fnewsService.findNewDety(newsid);
		return famnewsCustom;		
	}
    
    
	//查询出所有的家族新闻
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamNews")
	public Map<String, Object> findFamNews(@RequestParam int pageSize,@RequestParam int pageNumber,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FNewsCustom> list=fnewsService.findFamNews();
		PageInfo<FNewsCustom> pageInfo=new PageInfo<FNewsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setId(list.get(i).getNewsid());
			list.get(i).setOtime(StringUtil.changeTime(list.get(i).getOpertime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;				
	}
	
	//查询出所有的家族新闻：展示端
	@ResponseBody
	@RequestMapping("/findShFamNews")
	public List<Map<String, Object>> findShFamNews(@RequestParam int id,HttpServletResponse res)throws Exception{
		List<Map<String, Object>> newslist=new ArrayList<>();
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		List<FNewsCustom> list=fnewsService.findFamNews();
		for(int i=0;i<list.size();i++){
			Map<String, Object> m=new HashMap<>();
			m.put("newsid",list.get(i).getNewsid());
			m.put("newstitle",list.get(i).getTitle());
			m.put("newsimg", list.get(i).getUrl());
			m.put("newstime",StringUtil.changeTime(list.get(i).getOpertime()));
			newslist.add(m);
		}
		return newslist;		
	}
	
	//查询出所有的家族新闻:手机端
	@ResponseBody
	@RequestMapping("/findAnFamNews")
	public List<Map<String, Object>> findAnFamNews(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		List<Map<String, Object>> newslist=new ArrayList<>();
		if(map.get("id").equals("0")){
			DBContextHolder.setDBType("0");
			PageHelper.startPage(Integer.parseInt(map.get("page")), 10);	
			List<FFamnewsCustom> list=fnewsService.findAllNews();
			PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
			for(int i=0;i<list.size();i++){
				Map<String, Object> m=new HashMap<>();
				m.put("id",list.get(i).getNewsid());
				m.put("title",list.get(i).getTitle());
				m.put("img", list.get(i).getUrl());
				m.put("time",StringUtil.changeTime(list.get(i).getOpertime()));
				newslist.add(m);
			}
		}else{
			DBContextHolder.setDBType("0");
			FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
			DBContextHolder.setDBType(fUserCustom.getDbid()+"");
			PageHelper.startPage(Integer.parseInt(map.get("page")), 10);		
			List<FNewsCustom> list=fnewsService.findFamNews();
			PageInfo<FNewsCustom> pageInfo=new PageInfo<FNewsCustom>(list);
			for(int i=0;i<list.size();i++){
				Map<String, Object> m=new HashMap<>();
				m.put("id",list.get(i).getNewsid());
				m.put("title",list.get(i).getTitle());
				m.put("img", list.get(i).getUrl());
				m.put("time",StringUtil.changeTime(list.get(i).getOpertime()));
				newslist.add(m);
			}
		}	
		return newslist;		
	} 
	
	//根据Id查找出家族新闻的具体信息:手机端
	@ResponseBody
	@RequestMapping("/findAnNewsDety")
	public Map<String, Object> findAnNewsDety(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String,Object> m=new HashMap<>();
		if(map.get("id")==null||map.get("id").equals("")){
			FFamnewsCustom famnewsCustom=fnewsService.findNewDety(Integer.parseInt(map.get("newsid")));
			m.put("title",famnewsCustom.getTitle());
			m.put("date",StringUtil.changeTime(famnewsCustom.getOpertime()));
			m.put("content",famnewsCustom.getContent());
		}else{
			FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
			DBContextHolder.setDBType(fUserCustom.getDbid()+"");
			FNewsCustom fNewsCustom=fnewsService.findFamNewsDety(Integer.parseInt(map.get("newsid")));			
			m.put("title",fNewsCustom.getTitle());
			m.put("date",StringUtil.changeTime(fNewsCustom.getOpertime()));
			m.put("content",fNewsCustom.getContent());
		}
		
		return m;	
	}
	
	//根据Id查找出家族新闻的具体信息:展示端
	@ResponseBody
	@RequestMapping("/findShNewsDety")
	public Map<String,Object> findShNewsDety(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String,Object> m=new HashMap<>();
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FNewsCustom fNewsCustom=fnewsService.findFamNewsDety(Integer.parseInt(map.get("itemid")));			
		m.put("title",fNewsCustom.getTitle());
		m.put("time",StringUtil.changeTime(fNewsCustom.getOpertime()));
		m.put("content",fNewsCustom.getContent());
		return m;		
	}
	
	//根据Id查找出家族新闻的具体信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamNewsDety")
	public FNewsCustom findFamNewsDety(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		FNewsCustom fNewsCustom=fnewsService.findFamNewsDety(id);
		fNewsCustom.setOtime(StringUtil.changeTime(fNewsCustom.getOpertime()));
		return fNewsCustom;		
	}
    
    //上传对应家族的一条新闻
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/addFamNews")
    public int addFamNews(@RequestParam MultipartFile[] file,@RequestParam String title,@RequestParam String memo,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{
    	
    	Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
    	int operid=Integer.parseInt(usermap.get("userinfoid").toString());
		
		String url=null;
		
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		
		String Content=PicsSave.uploadContent(content);
		
		int flag=fnewsService.addFamNews(title, Content, operid, url, memo);
		return flag;   	
    }
    
    //删除一条对应家族的新闻信息
    @ResponseBody
    @RequestMapping("/deleteFamNews")
    public int deleteFamNews(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
    	@SuppressWarnings("unchecked")
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
    	@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		int flag = 0;
		for(int i=0;i<a.size();i++){			
			flag=fnewsService.deleteFamNews(Integer.parseInt(a.get(i)));
		}
		return flag;  	
    }
    
    //修改一条对应家族的新闻信息
    @ResponseBody
    @RequestMapping("/updateFamNews")
    public int updateFamNews(@RequestParam MultipartFile[] file,@RequestParam String id,@RequestParam String title,@RequestParam String memo,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{
    	@SuppressWarnings("unchecked")
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
        String url=null;		
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		
		String Content=PicsSave.uploadContent(content);
		FNewsCustom fNewsCustom=new FNewsCustom();
		fNewsCustom.setNewsid(Integer.valueOf(id));
		fNewsCustom.setTitle(title);
		fNewsCustom.setContent(Content);
		fNewsCustom.setMemo(memo);
		fNewsCustom.setUrl(url);
		
		int flag=fnewsService.updateFamNews(fNewsCustom);
		return flag;    	
    }
}
