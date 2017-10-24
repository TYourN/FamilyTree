package cn.items.mssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@RequestMapping("/findNewsAll")
	public Map<String, Object> findNewsAll(@RequestParam int pages,HttpServletResponse res)throws Exception{
		PageHelper.startPage(pages, 10);
		Map<String, Object> map=new HashMap<>();
		List<FFamnewsCustom> list=fnewsService.findAllNews();
		PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
		long total=pageInfo.getTotal();
		map.put("pages", total);
		map.put("locate",totalPrompt.newsObject());
		map.put("Content",list);
		return map;		
	}
	
	@ResponseBody
	@RequestMapping("/findNewDety")
	public FFamnewsCustom findNewDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		FFamnewsCustom famnewsCustom=fnewsService.findNewDety(newsid);
		famnewsCustom.setLocate(totalPrompt.newsObject());
		return famnewsCustom;		
	}
}
