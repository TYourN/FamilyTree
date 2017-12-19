package cn.items.mssm.controller;

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
import cn.items.mssm.poCustom.FHonCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FhonService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fhon")
public class FhonController {

	/** 
	 * @ClassName: FhonController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月25日 下午2:39:44 
	 */
	
	@Resource
	private FhonService fhonService;
	
	@Resource
	private FuserService fuserService;
	
	private static PicsSave pics=new PicsSave();
	
	/*public static Map<String , Object> usermap=new HashMap<>();*/
	
	
	//手机端有条件地读取对应家族的荣誉
	@ResponseBody
	@RequestMapping("/findAnFamHon")
	public List<Map<String,Object>> findAnFamHon(@RequestBody Map<String,String> map,HttpSession session,HttpServletResponse res)throws Exception{
		List<Map<String,Object>> honlist=new ArrayList<>();
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		Map<String,String> m=new HashMap<>();
		m.put("content",map.get("mess"));
		if(map.get("byear")==null||map.get("byear")==""){
			m.put("starttime","");
		}
		if(map.get("lyear")==null||map.get("lyear")==""){
			m.put("endtime","");
		}
		if(map.get("byear")!="" && map.get("bmonth")==""){
			m.put("starttime",map.get("byear"));
		}
		if(map.get("lyear")!="" && map.get("lmonth")==""){
			m.put("endtime",map.get("lyear"));
		}
		if(map.get("byear")!="" && map.get("bmonth")!=""){
			if(Integer.parseInt(map.get("bmonth"))<10){
				m.put("starttime",map.get("byear")+"-"+"0"+map.get("bmonth"));
			}else{
				m.put("starttime",map.get("byear")+"-"+map.get("bmonth"));
			}			
		}
		if(map.get("lyear")!="" && map.get("lmonth")!=""){
			if(Integer.parseInt(map.get("lmonth"))<10){
				m.put("endtime",map.get("lyear")+"-"+"0"+map.get("lmonth"));
			}else{
				m.put("endtime",map.get("lyear")+"-"+map.get("lmonth"));
			}			
		}
		
		PageHelper.startPage(Integer.parseInt(map.get("page")),10);
		List<FHonCustom> list=fhonService.findSomeHon(m);
		PageInfo<FHonCustom> pageInfo=new PageInfo<FHonCustom>(list);
		for(int i=0;i<list.size();i++){
			Map<String,Object> p=new HashMap<>();
			p.put("id",list.get(i).getHonid());
			p.put("img",list.get(i).getUrl());
			p.put("title",list.get(i).getTitle());
			p.put("time", StringUtil.changeTime(list.get(i).getOpertime()));
			p.put("content",list.get(i).getContent());
			honlist.add(p);
		}
	    
		return honlist;	
	}
	
	//展示端有条件地读取对应家族的荣誉
	@ResponseBody
	@RequestMapping("/findShFamHon")
	public List<Map<String,Object>> findShFamHon(@RequestBody Map<String,String> map,HttpSession session,HttpServletResponse res)throws Exception{
		List<Map<String,Object>> honlist=new ArrayList<>();
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		Map<String,String> m=new HashMap<>();
		m.put("content",map.get("mess"));
		if(map.get("byear")==null||map.get("byear")==""){
			m.put("starttime","");
		}
		if(map.get("lyear")==null||map.get("lyear")==""){
			m.put("endtime","");
		}
		if(map.get("byear")!="" && map.get("bmonth")==""){
			m.put("starttime",map.get("byear"));
		}
		if(map.get("lyear")!="" && map.get("lmonth")==""){
			m.put("endtime",map.get("lyear"));
		}
		if(map.get("byear")!="" && map.get("bmonth")!=""){
			if(Integer.parseInt(map.get("bmonth"))<10){
				m.put("starttime",map.get("byear")+"-"+"0"+map.get("bmonth"));
			}else{
				m.put("starttime",map.get("byear")+"-"+map.get("bmonth"));
			}			
		}
		if(map.get("lyear")!="" && map.get("lmonth")!=""){
			if(Integer.parseInt(map.get("lmonth"))<10){
				m.put("endtime",map.get("lyear")+"-"+"0"+map.get("lmonth"));
			}else{
				m.put("endtime",map.get("lyear")+"-"+map.get("lmonth"));
			}			
		}
		
		PageHelper.startPage(Integer.parseInt(map.get("page")),10);
		List<FHonCustom> list=fhonService.findSomeHon(m);
		PageInfo<FHonCustom> pageInfo=new PageInfo<FHonCustom>(list);
		for(int i=0;i<list.size();i++){
			Map<String,Object> p=new HashMap<>();
			p.put("id",list.get(i).getHonid());
			p.put("url",list.get(i).getUrl());
			honlist.add(p);
		}
	    
		return honlist;		
	}
	
	//展示端根据Id读取对应家族的荣誉信息
	@ResponseBody
	@RequestMapping("/findShHonDety")
	public Map<String,Object> findShHonDety(@RequestBody Map<String,Object> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id").toString()));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		Map<String,Object> m=new HashMap<>();
		@SuppressWarnings("unchecked")
		List<String> list=(List<String>) map.get("honid");
		int honid=Integer.parseInt(list.get(0));
		FHonCustom fHonCustom=fhonService.findHonDety(honid);
		m.put("title",fHonCustom.getTitle());
		m.put("content",fHonCustom.getContent());
		return m;		
	}
	
	//读取对应家族的荣誉:管理端
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findAllHon")
	public Map<String, Object> findAllHon(@RequestParam int pageNumber,@RequestParam int pageSize,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String , Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FHonCustom> list=fhonService.findAllHon();
		PageInfo<FHonCustom> pageInfo=new PageInfo<FHonCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setId(list.get(i).getHonid());
			list.get(i).setOtime(StringUtil.changeTime(list.get(i).getOpertime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;		
	}
	
	//根据Id读取对应家族的荣誉文字信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findHonDety")
	public FHonCustom findHonDety(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String , Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		int honid=id;
		FHonCustom fHonCustom=fhonService.findHonDety(honid);
		fHonCustom.setOtime(StringUtil.changeTime(fHonCustom.getOpertime()));
		return fHonCustom;		
	}
	
	//增加对应家族荣誉 
	@ResponseBody
	@RequestMapping("/addFamHon")
	public int addFamHon(@RequestParam MultipartFile[] file,@RequestParam String title,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{	
		@SuppressWarnings("unchecked")
		Map<String , Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		int operid=Integer.parseInt(usermap.get("userinfoid").toString());
		String url=null;
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		FHonCustom fHonCustom=new FHonCustom();
		fHonCustom.setTitle(title);
		fHonCustom.setContent(content);
		fHonCustom.setOperid(operid);
		fHonCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		fHonCustom.setUrl(url);
		int flag=fhonService.addFamHon(fHonCustom);
		return flag;		
	}
	
	//删除对应Id的对应家族的荣誉
	@ResponseBody
	@RequestMapping("/deleteFamHon")
	public int deleteFamHon(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String , Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		int flag = 0;
		for(int i=0;i<a.size();i++){
			int honid=Integer.parseInt(a.get(i));
			flag=fhonService.deleteFamHon(honid);
		}		
		return flag;		
	}
	
	//更新对应Id的对应家族的荣誉
	@ResponseBody
	@RequestMapping("/updateFamHon")
	public int updateFamHon(@RequestParam MultipartFile[] file,@RequestParam String id,@RequestParam String title,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String , Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		String url=null;
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		FHonCustom fHonCustom=new FHonCustom();
		fHonCustom.setTitle(title);
		fHonCustom.setContent(content);
		fHonCustom.setUrl(url);
		int flag=fhonService.updateFamHon(fHonCustom);
		return flag;		
	}
}
