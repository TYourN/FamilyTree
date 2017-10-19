package cn.items.mssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * @date: 2017年10月18日 上午11:01:26 
	 */
	
	@Resource
	private FnamesService fnamesService;
	
	@RequestMapping("/findNameDety")
	public String findNameDety(@RequestParam String title,HttpServletResponse res)throws Exception{
		FFamnamesCustom famnamesCustom=fnamesService.findNameDety(title);
		JSONObject json=JSONObject.fromObject(famnamesCustom);
		ResponseUtil.write(res, json);
		return null;		
	}
}
