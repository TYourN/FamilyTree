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
import cn.items.mssm.poCustom.FFunctionCustom;
import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.service.FfuncService;

@Controller
@RequestMapping("/ffunc")
public class FfuncController {

	/** 
	 * @ClassName: FfuncController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月22日 下午7:39:16 
	 */
	
	@Resource
	private FfuncService ffuncService;
	
	//查找出所有的功能
	@ResponseBody
	@RequestMapping("/findAllfunc")
	public Map<String, Object> findAllfunc(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FFunctionCustom> list=ffuncService.findAllfunc();
		for(int i=0;i<list.size();i++){
			list.get(i).setId(list.get(i).getFuncid());
		}
		PageInfo<FFunctionCustom> pageInfo=new PageInfo<FFunctionCustom>(list);
		long total=pageInfo.getTotal();
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;	
	}
	
	//获取所有的 父功能模块
	@ResponseBody
	@RequestMapping("/findParents")
	public List<FFunctionCustom> findParents(HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");		
		return ffuncService.findParents();		
	}
	
	//增加一个功能模块
	@ResponseBody
	@RequestMapping("/addFuncInfo")
	public int addFuncInfo(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFunctionCustom fFunctionCustom=new FFunctionCustom();
		fFunctionCustom.setTitle(map.get("title"));
		fFunctionCustom.setParentid(Integer.parseInt(map.get("parent")));
		fFunctionCustom.setUrl(map.get("url"));
		fFunctionCustom.setMemo(map.get("memo"));
		fFunctionCustom.setFlag(map.get("flag"));
		int flag=ffuncService.addFuncInfo(fFunctionCustom);
		return flag;		
	}
	
	//根据Id获取功能信息
	@ResponseBody
	@RequestMapping("/findFuncById")
	public FFunctionCustom findFuncById(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		return ffuncService.findFuncById(id);		
	}
	
	//更新功能信息
	@ResponseBody
	@RequestMapping("/updateFunc")
	public int updateFunc(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFunctionCustom fFunctionCustom=new FFunctionCustom();
		fFunctionCustom.setFuncid(Integer.parseInt(map.get("funcid")));
		fFunctionCustom.setTitle(map.get("title"));
		fFunctionCustom.setParentid(Integer.parseInt(map.get("parent")));
		fFunctionCustom.setUrl(map.get("url"));
		fFunctionCustom.setMemo(map.get("memo"));
		fFunctionCustom.setFlag(map.get("flag"));	
		int flag=ffuncService.updateFunc(fFunctionCustom);
		return flag;	
	}
	
	//删除功能信息
	@ResponseBody
	@RequestMapping("/delFunc")
	public int delFunc(@RequestBody Map<String,Object> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) map.get("idlist");
		
		int flag=0;
		for(int i=0;i<a.size();i++){
			int funcid=Integer.parseInt(a.get(i));
			if(funcid==0){
				ffuncService.delFuncParent(funcid);				
			}
			flag=ffuncService.delFunc(funcid);
		}
		return flag;	
	}
}
