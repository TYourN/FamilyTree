package cn.items.mssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.poCustom.FFamnamesCustom;
import cn.items.mssm.service.FnamesService;

@Controller
@RequestMapping("/fnames")
public class FnamesController {

	/** 
	 * @ClassName: FnamesController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月18日 上午11:01:26 
	 */
	
	@Resource
	private FnamesService fnamesService;
	
	@ResponseBody
	@RequestMapping("/findNameDety")
	public FFamnamesCustom findNameDety(@RequestParam String title,HttpServletResponse res)throws Exception{
		FFamnamesCustom famnamesCustom=fnamesService.findNameDety(title);
		return famnamesCustom;		
	}
	
	//主页插入一条姓氏文化的内容
	@ResponseBody
	@RequestMapping("/addMainNames")
	public int addMainNames(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		FFamnamesCustom famnamesCustom=new FFamnamesCustom();
		famnamesCustom.setTitle(map.get(""));
		famnamesCustom.setContent(PicsSave.uploadContent(map.get("")));
		famnamesCustom.setMemo(map.get(""));
		int flag=fnamesService.addMainNames(famnamesCustom);
		return flag;	
	}
	
	//主页删除一条姓氏文化的内容
	@ResponseBody
	@RequestMapping("/deleteMainNames")
	public int deleteMainNames(@RequestParam int nameid,HttpServletResponse res)throws Exception{
		int flag=fnamesService.deleteMainNames(nameid);
		return flag;		
	}
}
