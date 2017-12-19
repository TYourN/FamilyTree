package cn.items.mssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FPostsCustom;
import cn.items.mssm.poCustom.FRepliesCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FostsService;
import cn.items.mssm.service.FrepliesService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fposts")
public class FpostsController{

	/** 
	 * @ClassName: FpostsController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月15日 下午1:51:55 
	 */
	
	@Resource
	private FostsService fostsService;
	
	@Resource
	private FrepliesService frepliesService;
	
	@Resource
	private FuserService fuserService;
	
	public static PicsSave pics=new PicsSave();
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/
	
	//查找出最新更新的几条家族公告信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findReportsTop")
	public List<FPostsCustom> findReportsTop(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		List<FPostsCustom> list=fostsService.findReportsTop();
		return list;		
	}
	
	//查找出BBS页所需的统计数据
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findAllCount")
	public Map<String, Object> findAllCount(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		Map<String, Object> map=new HashMap<>();
		map.put("Totie",fostsService.findTotie());
		map.put("Yestie",fostsService.findYestie());
		map.put("Alltie", fostsService.findAlltie());
		map.put("Allreports", fostsService.findAllreports());
		map.put("Allreplies", frepliesService.findAllreplies());
		map.put("Latesttie", fostsService.findLatesttie());
		map.put("LatestReport", fostsService.findLatestReport());
		return map;	
	}
	
	//查找出所有的水贴相关信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findAllInfo")
	public Map<String, Object> findAlltieInfo(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam int flag,HttpServletResponse res)throws Exception{		
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FPostsCustom> list=fostsService.findAlltieInfo();
		PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setCtimeString(StringUtil.changeTime(list.get(i).getCreatetime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	//手机端获取到所有的帖子信息
	@ResponseBody
	@RequestMapping("/findTieInfo")
	public List<Map<String,Object>> findTieInfo(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		List<Map<String,Object>> tielist=new ArrayList<>();
		Map<String,String> m=new HashMap<>();
		if(map.get("type").equals("all")){
			PageHelper.startPage(Integer.parseInt(map.get("page")),10);
			m.put("type","");
			List<FPostsCustom> list=fostsService.findInfoBy(m);
			PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
			for(int i=0;i<list.size();i++){
				Map<String, Object> p=new HashMap<>();
				p.put("id",list.get(i).getPostid());
				p.put("title",list.get(i).getTitle());
				p.put("firstname",list.get(i).getName());
				p.put("number",frepliesService.findReNumById(list.get(i).getPostid()));
				p.put("lasttime",StringUtil.changeTime(list.get(i).getCreatetime()));
				p.put("type",list.get(i).getType());
				tielist.add(p);
			}
		}else if(map.get("type").equals("forum_notice")){
			PageHelper.startPage(Integer.parseInt(map.get("page")),10);
			m.put("type","公告");
			List<FPostsCustom> list=fostsService.findInfoBy(m);
			PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
			for(int i=0;i<list.size();i++){
				Map<String, Object> p=new HashMap<>();
				p.put("id",list.get(i).getPostid());
				p.put("title",list.get(i).getTitle());
				p.put("firstname",list.get(i).getName());
				p.put("number",frepliesService.findReNumById(list.get(i).getPostid()));
				p.put("lasttime",StringUtil.changeTime(list.get(i).getCreatetime()));
				p.put("type",list.get(i).getType());
				tielist.add(p);
			}
		}
		return tielist;		
	}
	
	//展示端获取所有的帖子信息
	@ResponseBody
	@RequestMapping("/findShTieInfo")
	public Map<String,Object> findShTieInfo(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		Map<String,Object> info=new HashMap<>();
		List<Map<String,Object>> tielist=new ArrayList<>();
		Map<String,String> m=new HashMap<>();
		PageHelper.startPage(Integer.parseInt(map.get("page")),10);
		m.put("type","");
		List<FPostsCustom> list=fostsService.findInfoBy(m);
		PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
		for(int i=0;i<list.size();i++){
			Map<String, Object> p=new HashMap<>();
			p.put("id",list.get(i).getPostid());
			p.put("title",list.get(i).getTitle());
			p.put("firstname",list.get(i).getName());
			p.put("lastname",list.get(i).getName());
			p.put("number",frepliesService.findReNumById(list.get(i).getPostid()));
			p.put("firsttime",StringUtil.changeTime(list.get(i).getCreatetime()));
			p.put("lasttime",StringUtil.changeTime(list.get(i).getCreatetime()));
			tielist.add(p);
		}
		info.put("page",pageInfo.getPages());
		info.put("content",tielist);
		return info;		
	}
	
	//手机端读取对应Id的帖子信息
	@ResponseBody
	@RequestMapping("/findTieInfoById")
	public Map<String, Object> findTieInfoById(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		Map<String,Object> tieMap=new HashMap<>();
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FPostsCustom fPostsCustom=fostsService.findDetialById(Integer.parseInt(map.get("pid")));
		tieMap.put("title",fPostsCustom.getTitle());
		List<Map<String,Object>> contentlist=new ArrayList<>();
		List<FRepliesCustom> plist=new ArrayList<>();
		if(Integer.parseInt(map.get("page"))==1){
			PageHelper.startPage(Integer.parseInt(map.get("page")),9);
			plist=frepliesService.findPRepliesById(Integer.parseInt(map.get("pid")));
			PageInfo<FRepliesCustom> pageInfo=new PageInfo<FRepliesCustom>(plist);
			tieMap.put("page",pageInfo.getPages());
			Map<String,Object> ma=new HashMap<>();
			ma.put("id",fPostsCustom.getPostid());
			ma.put("img",fPostsCustom.getPic());
			ma.put("user",fPostsCustom.getName());
			ma.put("time",StringUtil.changeTime(fPostsCustom.getCreatetime()));
			ma.put("content",fPostsCustom.getContent());
			contentlist.add(ma);
		}else{
			PageHelper.startPage(Integer.parseInt(map.get("page")),9);
			plist=frepliesService.findPRepliesById(Integer.parseInt(map.get("pid")));
			PageInfo<FRepliesCustom> pageInfo=new PageInfo<FRepliesCustom>(plist);
			tieMap.put("page",pageInfo.getPages());
		}	
		if(plist.size()!=0){
			for(int i=0;i<plist.size();i++){
				Map<String,Object> cmap=new HashMap<>();
				Map<String,Object> m=new HashMap<>();
				m.put("postid",Integer.parseInt(map.get("pid")));
				m.put("parentid",plist.get(i).getReplyid());
				cmap.put("id",plist.get(i).getReplyid());
				cmap.put("img",plist.get(i).getPic());
				cmap.put("user",plist.get(i).getName());
				cmap.put("time",StringUtil.changeTime(plist.get(i).getCreatetime()));
				cmap.put("content",plist.get(i).getContent());
				List<Map<String,Object>> childlist=new ArrayList<>();
				List<FRepliesCustom> clist=frepliesService.findCRepliesById(m);
				if(clist.size()!=0){					
					for(int j=0;j<clist.size();j++){
						Map<String,Object> ccmap=new HashMap<>();
						ccmap.put("id", clist.get(j).getReplyid());
						ccmap.put("user",clist.get(j).getName());
						ccmap.put("content",clist.get(j).getContent());
						ccmap.put("time",StringUtil.changeTime(clist.get(j).getCreatetime()));
						childlist.add(ccmap);
					}
				}
				cmap.put("children",childlist);
				contentlist.add(cmap);
			}
		}
		tieMap.put("content",contentlist);
		return tieMap;		
	}
	
	//展示端读取对应Id的帖子信息
	@ResponseBody
	@RequestMapping("/findShTieInfoById")
	public Map<String,Object> findShTieInfoById(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		Map<String,Object> tieMap=new HashMap<>();
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FPostsCustom fPostsCustom=fostsService.findDetialById(Integer.parseInt(map.get("pid")));
		tieMap.put("title",fPostsCustom.getTitle());
		List<Map<String,Object>> contentlist=new ArrayList<>();
		List<FRepliesCustom> plist=new ArrayList<>();
		if(Integer.parseInt(map.get("page"))==1){
			PageHelper.startPage(Integer.parseInt(map.get("page")),9);
			plist=frepliesService.findPRepliesById(Integer.parseInt(map.get("pid")));
			PageInfo<FRepliesCustom> pageInfo=new PageInfo<FRepliesCustom>(plist);
			tieMap.put("page",pageInfo.getPages());
			Map<String,Object> ma=new HashMap<>();
			ma.put("id",fPostsCustom.getPostid());
			ma.put("img",fPostsCustom.getPic());
			ma.put("user",fPostsCustom.getName());
			ma.put("time",StringUtil.changeTime(fPostsCustom.getCreatetime()));
			ma.put("content",fPostsCustom.getContent());
			contentlist.add(ma);
		}else{
			PageHelper.startPage(Integer.parseInt(map.get("page")),9);
			plist=frepliesService.findPRepliesById(Integer.parseInt(map.get("pid")));
			PageInfo<FRepliesCustom> pageInfo=new PageInfo<FRepliesCustom>(plist);
			tieMap.put("page",pageInfo.getPages());
		}	
		if(plist.size()!=0){
			for(int i=0;i<plist.size();i++){
				Map<String,Object> cmap=new HashMap<>();
				Map<String,Object> m=new HashMap<>();
				m.put("postid",Integer.parseInt(map.get("pid")));
				m.put("parentid",plist.get(i).getReplyid());
				cmap.put("id",plist.get(i).getReplyid());
				cmap.put("img",plist.get(i).getPic());
				cmap.put("user",plist.get(i).getName());
				cmap.put("time",StringUtil.changeTime(plist.get(i).getCreatetime()));
				cmap.put("content",plist.get(i).getContent());
				List<Map<String,Object>> childlist=new ArrayList<>();
				List<FRepliesCustom> clist=frepliesService.findCRepliesById(m);
				if(clist.size()!=0){					
					for(int j=0;j<clist.size();j++){
						Map<String,Object> ccmap=new HashMap<>();
						ccmap.put("id", clist.get(j).getReplyid());
						ccmap.put("user",clist.get(j).getName());
						ccmap.put("content",clist.get(j).getContent());
						ccmap.put("time",StringUtil.changeTime(clist.get(j).getCreatetime()));
						childlist.add(ccmap);
					}
				}
				cmap.put("children",childlist);
				contentlist.add(cmap);
			}
		}
		tieMap.put("content",contentlist);
		return tieMap;		
	}
	
	//查找出所有的公告相关信息
	@ResponseBody
	@RequestMapping("/findAllReportsInfo")
	public Map<String, Object> findAllReportsInfo(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FPostsCustom> list=fostsService.findAllReportsInfo();
		PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setCtimeString(StringUtil.changeTime(list.get(i).getCreatetime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;	
	}
	
	//查找出对应id的公告或水贴的信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findDetialById")
	public FPostsCustom findDetialById(HttpSession session,@RequestParam int postid,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		FPostsCustom fPostsCustom=fostsService.findDetialById(postid);
		return fPostsCustom;		
	}
	
	//删除一条对应的水贴或者公告
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteDetialById")
	public int deleteDetialById(@RequestParam int postid,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		int flag=fostsService.deleteDetialById(postid);
		return flag;		
	}
	
	//更新对应id的公告内容
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateRepById")
	public int updateRepById(@RequestBody Map<String, String> map,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		FPostsCustom fPostsCustom=new FPostsCustom();
		String Content=PicsSave.uploadContent(map.get("content"));
		fPostsCustom.setTitle(map.get("title"));
		fPostsCustom.setContent(Content);
		fPostsCustom.setPostid(Integer.parseInt(map.get("postid")));
		int flag=fostsService.updateRepById(fPostsCustom);
		return flag;		
	}
	
	//发布公告
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/addPosts")
	public int addPosts(@RequestBody Map<String, String> map,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		String Content=PicsSave.uploadContent(map.get("content"));
		FPostsCustom fPostsCustom=new FPostsCustom();
		fPostsCustom.setTitle(map.get("title"));
		fPostsCustom.setContent(Content);
		fPostsCustom.setPosterid(1);
		fPostsCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		fPostsCustom.setType("公告");
		int flag=fostsService.addPosts(fPostsCustom);
		return flag;		
	}
	
	//手机端发布水贴
	@ResponseBody
	@RequestMapping("/addShuiTie")
	public int addShuiTie(@RequestParam MultipartFile[] img,int id, String title, String content,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");		
		String urlContent="";
		if(img.length!=0){			
			for(int i=0;i<img.length;i++){
				String newFileName=pics.picsProcess(img[i]); 	

				String fileUrl=pics.saveFile(newFileName, img[i]);
				PicsSave.uploadFile(fileUrl, newFileName);
				
				String url=pics.saveFileString(newFileName);
				urlContent=StringUtil.SplicePRUrl(urlContent, url);
			}
		}
		content=content+urlContent;
		
		FPostsCustom fPostsCustom=new FPostsCustom();
		fPostsCustom.setTitle(title);
		fPostsCustom.setContent(content);
		fPostsCustom.setPosterid(fUserCustom.getUserinfoid());
		fPostsCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		fPostsCustom.setType("水贴");
		int flag=fostsService.addPosts(fPostsCustom);
		return flag;		
	}
	
	//展示端发布水贴
	@ResponseBody
	@RequestMapping("/addShShuiTie")
	public int addShShuiTie(@RequestParam MultipartFile[] img,int id, String title, String content,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");		
		String urlContent="";
		if(img.length!=0){			
			for(int i=0;i<img.length;i++){
				String newFileName=pics.picsProcess(img[i]); 	

				String fileUrl=pics.saveFile(newFileName, img[i]);
				PicsSave.uploadFile(fileUrl, newFileName);
				
				String url=pics.saveFileString(newFileName);
				urlContent=StringUtil.SplicePRUrl(urlContent, url);
			}
		}
		content=content+urlContent;
		
		FPostsCustom fPostsCustom=new FPostsCustom();
		fPostsCustom.setTitle(title);
		fPostsCustom.setContent(content);
		fPostsCustom.setPosterid(fUserCustom.getUserinfoid());
		fPostsCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		fPostsCustom.setType("水贴");
		int flag=fostsService.addPosts(fPostsCustom);
		return flag;				
	}
}
