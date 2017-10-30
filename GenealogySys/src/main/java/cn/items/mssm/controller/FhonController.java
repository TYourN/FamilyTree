package cn.items.mssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FHonCustom;
import cn.items.mssm.service.FhonService;

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
	
	private static String serverId;
	
	private static PicsSave pics=new PicsSave();
	
	//读取对应家族的荣誉
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamHon")
	public List<FHonCustom> findFamHon(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String,String> map=(Map<String, String>) session.getAttribute("user");
		serverId=map.get("serverId");
		DBContextHolder.setDBType(serverId);
	    List<FHonCustom> list=fhonService.findFamHon();
		return list;	
	}
	
	//根据Id读取对应家族的荣誉文字信息
	@ResponseBody
	@RequestMapping("/findHonDety")
	public List<FHonCustom> findHonDety(@RequestParam int honid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType(serverId);
		List<FHonCustom> list=fhonService.findHonDety(honid);
		return list;		
	}
	
	//增加对应家族荣誉 
	@ResponseBody
	@RequestMapping("/addFamHon")
	public int addFamHon(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType(serverId);
		@SuppressWarnings("unchecked")
		Map<String,String> map=(Map<String, String>) session.getAttribute("user");
		int operid=Integer.parseInt(map.get("userid"));
		String newFileName=pics.picsProcess(file); 	

		String fileUrl=pics.saveFile(newFileName, file);
		PicsSave.uploadFile(fileUrl, newFileName);
		
		String url=pics.saveFileString(newFileName);
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
	public int deleteFamHon(@RequestParam int honid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType(serverId);
		int flag=fhonService.deleteFamHon(honid);
		return flag;		
	}
}
