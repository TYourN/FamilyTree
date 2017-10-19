package cn.items.mssm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.items.mssm.entity.ResponseUtil;
import cn.items.mssm.entity.TotalPrompt;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.service.FnewsService;
import net.sf.json.JSONObject;

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
	
	public static TotalPrompt totalPrompt=new TotalPrompt();
	
	@RequestMapping("/findNewsAll")
	public String findNewsAll(@RequestParam int pages,HttpServletResponse res)throws Exception{
		PageHelper.startPage(pages, 10);
		JSONObject jContent=new JSONObject();
		List<FFamnewsCustom> list=fnewsService.findAllNews();
		PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
		long total=pageInfo.getTotal();
		jContent.put("pages", total);
		jContent.put("locate",totalPrompt.newsObject());
		jContent.put("Content",list);
		ResponseUtil.write(res,jContent);	
		return null;		
	}
	
	@RequestMapping("/findNewDety")
	public String findNewDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		FFamnewsCustom famnewsCustom=fnewsService.findNewDety(newsid);
		famnewsCustom.setLocate(totalPrompt.newsObject());
		JSONObject json=JSONObject.fromObject(famnewsCustom);
		ResponseUtil.write(res, json);
		return null;		
	}
}
