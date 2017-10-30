package cn.items.mssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.poCustom.FCulCustom;
import cn.items.mssm.service.FculService;

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
	
	private static String CulServerId;
	
	//查找出所有的文化
	@ResponseBody
	@RequestMapping("/findFamCul")
	public FCulCustom findFamCul(HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,String> map=(Map<String, String>) session.getAttribute("user");
		CulServerId=map.get("serverId");
		DBContextHolder.setDBType(CulServerId);
		FCulCustom fCulCustom=fculService.findFamCul();
		return fCulCustom;		
	}
	
	//上传家族文化 
	@ResponseBody
	@RequestMapping("/addFamCul")
	public int addFamCul(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType(CulServerId);
		int flag=fculService.addFamCul(map.get("title"), PicsSave.uploadContent(map.get("content")));
		return flag;		
	}
	
	//更新家族文化
	@ResponseBody
	@RequestMapping("/updateFamCul")
	public int updateFamCul(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType(CulServerId);
		int flag=fculService.updateFamCul(map.get("title"),PicsSave.uploadContent(map.get("content")));
		return flag;		
	}
	
	
}
