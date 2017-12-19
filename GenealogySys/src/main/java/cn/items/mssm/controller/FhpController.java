package cn.items.mssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.service.FhomePageService;

@Controller
@RequestMapping("/fhp")
public class FhpController {

	/** 
	 * @ClassName: FhpController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月16日 下午7:34:22 
	 */
	@Resource
	private FhomePageService fhomePageService;
	
	public static PicsSave pics=new PicsSave();
	
	//读取首页第一页的图片
	@ResponseBody
	@RequestMapping("/findFirstPics")
	public List<String> findFirstPics(HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<String> list=fhomePageService.findFirstPics();
		return list;		
	}
	
	//读取首页第二页的图片
	/*@ResponseBody
	@RequestMapping("/findSecondPics")
	public List<Map<String,Object>> findSecondPics(HttpServletResponse res)throws Exception{
		String PicOne=fhomePageService.findSecondPic("在线编修");
		String PicTwo=fhomePageService.findSecondPic("随时查看");
		String PicThree=fhomePageService.findSecondPic("家族圈");
		String PicOneChild=fhomePageService.findSecondPic("在线编修子");
		String PicTwoChild=fhomePageService.findSecondPic("随时查看子");
		String PicThreeChild=fhomePageService.findSecondPic("家族圈子");
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> m=new HashMap<>();
		m.put("p","在线编修");
		m.put("img",PicOne);
		map.put("title",m);
		m.put("p","简单操作，在线编修。");
		m.put("img",PicOneChild);
		map.put("content",);
		
		return list;		
	}*/
	
	//获取首页三页的数据
	@ResponseBody
	@RequestMapping("/findForthPics")
	public List<FFamnewsCustom> findForthPics(HttpServletResponse res)throws Exception{
		List<FFamnewsCustom> list=fhomePageService.findHPNews();
		return list;		
	}
	
	//首页第一页图片的上传
	@ResponseBody
	@RequestMapping("/addFirstPics")
	public Map<String,String> addFirstPics(@RequestParam String flag,@RequestParam MultipartFile img,HttpServletResponse res)throws Exception{
		//新图片名=获取时间戳+图片扩展名
		DBContextHolder.setDBType("0");
		
		if(flag.equals("展示")){
			fhomePageService.deletePicsByFLag(flag);
		}
		
		String newFileName=pics.picsProcess(img); 	

		String fileUrl=pics.saveFile(newFileName,img);
		PicsSave.uploadFile(fileUrl, newFileName);
		
		String url=pics.saveFileString(newFileName);
		
		Map<String,String> map=new HashMap<>();
		fhomePageService.addFSPics("",url,"",flag);
		
		map.put("img",url);
		return map;		
	}
	
	@ResponseBody
	@RequestMapping("/deleteMianPics")
	public int deleteMianPics(@RequestParam int picid,HttpServletResponse res)throws Exception{
		int flag=fhomePageService.deleteMianPics(picid);
		return flag;		
	}
	
	//读取公共图片资源：管理端
	@ResponseBody
	@RequestMapping("/findPicsByAu")
	public List<Map<String,Object>> findPicsByAu(HttpServletResponse res) throws Exception{
		DBContextHolder.setDBType("0");
		List<Map<String,Object>> picslist=new ArrayList<>();
		
		Map<String,Object> map1=new HashMap<>();
		Map<String,String> m1=new HashMap<>();
		m1.put("flag","第一页");
		List<String> list1=fhomePageService.findPicsByAu(m1);
		map1.put("id","homebg");
		map1.put("item",list1);
		picslist.add(map1);
		
		Map<String,Object> map2=new HashMap<>();
		Map<String,String> m2=new HashMap<>();
		m2.put("flag","展示");
		List<String> list2=fhomePageService.findPicsByAu(m2);
		map2.put("id","personbg");
		map2.put("item",list2);
		picslist.add(map2);
		
		Map<String,Object> map3=new HashMap<>();
		Map<String,String> m3=new HashMap<>();
		m3.put("flag","手机");
		List<String> list3=fhomePageService.findPicsByAu(m3);
		map3.put("id","phonebg");
		map3.put("item",list3);
		picslist.add(map3);
		
		return picslist;		
	}
}
