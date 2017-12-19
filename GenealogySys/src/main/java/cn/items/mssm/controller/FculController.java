package cn.items.mssm.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FCulCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FculService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fcul")
public class FculController {

	/** 
	 * @ClassName: FculController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月26日 下午2:37:05 
	 */
	@Resource
	private FculService fculService;
	
	@Resource
	private FuserService fuserService;
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/
	
	//查找出所有的文化
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamCul")
	public Map<String, Object> findFamCul(@RequestParam int pageNumber,@RequestParam int pageSize,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FCulCustom> list=fculService.findFamCul();
		PageInfo<FCulCustom> pageInfo=new PageInfo<FCulCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setId(list.get(i).getCulid());
			list.get(i).setOtime(StringUtil.changeTime(list.get(i).getOpertime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;		
	}
	
	//获取对应类型的家族文化:手机端
	@ResponseBody
	@RequestMapping("/findAnCul")
	public Map<String,Object> findAnCul(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FCulCustom fCulCustom=fculService.findAnCulByType(map);
		Map<String,Object> m=new HashMap<>();
		if(fCulCustom==null){
			m.put("title","");
			m.put("date","");
			m.put("content","");
		}else{
			m.put("title",fCulCustom.getTitle());
			m.put("date",StringUtil.changeTime(fCulCustom.getOpertime()));
			m.put("content",fCulCustom.getContent());
		}		
		return m;		
	}
	
	//获取对应类型的家族文化:展示端
	@ResponseBody
	@RequestMapping("/findShCul")
	public Map<String,Object> findShCul(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		Map<String, String> p=new HashMap<>();
		p.put("type",map.get("culture"));
		FCulCustom fCulCustom=fculService.findAnCulByType(p);
		Map<String,Object> m=new HashMap<>();
		if(fCulCustom==null){
			m.put("title","");
			m.put("date","");
			m.put("content","");
		}else{
			m.put("title",fCulCustom.getTitle());
			m.put("date",StringUtil.changeTime(fCulCustom.getOpertime()));
			m.put("content",fCulCustom.getContent());
		}		
		return m;	
	}
	
	//上传家族文化 
	@ResponseBody
	@RequestMapping("/addFamCul")
	public int addFamCul(@RequestParam String title,@RequestParam String content,@RequestParam String type,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		String Content=PicsSave.uploadContent(content);
		int flag=fculService.addFamCul(title,Content,type);
		return flag;		
	}
	
	//根据Id查找对应的家族文化
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findCulById")
	public FCulCustom findCulById(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		FCulCustom fCulCustom=fculService.findCulById(id);
		fCulCustom.setOtime(StringUtil.changeTime(fCulCustom.getOpertime()));
		return fCulCustom;		
	}
	
	//更新家族文化
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateFamCul")
	public int updateFamCul(@RequestParam int id,@RequestParam String title,@RequestParam String content,@RequestParam String type,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		String Content=PicsSave.uploadContent(content);
		int flag=fculService.updateFamCul(id,title,Content,type);
		return flag;		
	}
	
	//删除一条家族文化
	@ResponseBody
	@RequestMapping("/deleteFamCul")
	public int deleteFamCul(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{		
		@SuppressWarnings("unchecked")
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		int flag = 0;
		for(int i=0;i<a.size();i++){
			flag=fculService.deleteFamCul(Integer.parseInt(a.get(i)));
		}
		return flag; 	
	}
}
