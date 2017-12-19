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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.poCustom.FFamnamesCustom;
import cn.items.mssm.poCustom.FFamnewsCustom;
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
	
	//获取所有的姓氏文化信息:管理端
	@ResponseBody
	@RequestMapping("/findAllName")
	public Map<String,Object> findAllName(@RequestParam int pageSize,@RequestParam int pageNumber,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FFamnamesCustom> list=fnamesService.findAllName();
		PageInfo<FFamnamesCustom> pageInfo=new PageInfo<FFamnamesCustom>(list);
		long total=pageInfo.getTotal();
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total",total);
		return map;  		
	}
	
	@ResponseBody
	@RequestMapping("/findNameDety")
	public FFamnamesCustom findNameDety(@RequestParam String title,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnamesCustom famnamesCustom=fnamesService.findNameDety(title);
		return famnamesCustom;		
	}
	
	//查找出对应Id的姓氏文化
	@ResponseBody
	@RequestMapping("/findNameById")
	public FFamnamesCustom findNameById(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnamesCustom famnamesCustom=fnamesService.findNameById(id);
		return famnamesCustom;		
	}
	
	//主页插入一条姓氏文化的内容
	@ResponseBody
	@RequestMapping("/addMainNames")
	public int addMainNames(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnamesCustom famnamesCustom=new FFamnamesCustom();
		famnamesCustom.setTitle(map.get("title"));
		famnamesCustom.setContent(PicsSave.uploadContent(map.get("content")));
		famnamesCustom.setMemo(map.get("memo"));
		int flag=fnamesService.addMainNames(famnamesCustom);
		return flag;	
	}
	
	//主页删除一条姓氏文化的内容
	@ResponseBody
	@RequestMapping("/deleteMainNames")
	public int deleteMainNames(@RequestParam int nameid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		int flag=fnamesService.deleteMainNames(nameid);
		return flag;		
	}
	
	//主页修改一条姓氏文化的内容
	@ResponseBody
	@RequestMapping("/updateMainNames")
	public int updateMainNames(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnamesCustom famnamesCustom=new FFamnamesCustom();
		famnamesCustom.setTitle(map.get("title"));
		famnamesCustom.setContent(PicsSave.uploadContent(map.get("content")));
		famnamesCustom.setMemo(map.get("memo"));
		famnamesCustom.setFnameid(Integer.parseInt(map.get("nameid")));
		int flag=fnamesService.updateMainNames(famnamesCustom);
		return flag;		
	}
}
