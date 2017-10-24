package cn.items.mssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.items.mssm.entity.ResponseUtil;
import cn.items.mssm.poCustom.FFamnamesCustom;
import cn.items.mssm.service.FnamesService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/fnames")
public class FnamesController {

	/** 
	 * @ClassName: FnamesController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��18�� ����11:01:26 
	 */
	
	@Resource
	private FnamesService fnamesService;
	
	@ResponseBody
	@RequestMapping("/findNameDety")
	public FFamnamesCustom findNameDety(@RequestParam String title,HttpServletResponse res)throws Exception{
		FFamnamesCustom famnamesCustom=fnamesService.findNameDety(title);
		return famnamesCustom;		
	}
}
