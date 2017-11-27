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
import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.poCustom.FRolefuncCustom;
import cn.items.mssm.service.FroleService;

@Controller
@RequestMapping("/frole")
public class FroleController {

	/** 
	 * @ClassName: FroleController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月22日 下午7:39:05 
	 */
	
	@Resource
	private FroleService froleService;
	
	//查找所有的角色信息
	@ResponseBody
	@RequestMapping("/findRoleInfo")
	public Map<String, Object> findRoleInfo(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FRoleCustom> list=froleService.findRoleInfo();
		for(int i=0;i<list.size();i++){
			list.get(i).setId(list.get(i).getRoleid());
		}
		PageInfo<FRoleCustom> pageInfo=new PageInfo<FRoleCustom>(list);
		long total=pageInfo.getTotal();
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;		
	}
	
	//增加角色信息
	@ResponseBody
	@RequestMapping("/addRoleInfo")
	public int addRoleInfo(@RequestBody Map<String,String> map,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FRoleCustom fRoleCustom=new FRoleCustom();
		fRoleCustom.setTitle(map.get("title"));
		fRoleCustom.setMemo(map.get("memo"));
		int flag=froleService.addRoleInfo(fRoleCustom);
		return 1;		
	}
	
	//根据Id获取角色信息
	@ResponseBody
	@RequestMapping("/findRInfoById")
	public FRoleCustom findRInfoById(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FRoleCustom fRoleCustom=froleService.findRInfoById(id);
		return fRoleCustom;		
	}
	
	//更新角色信息
	@ResponseBody
	@RequestMapping("/updateRoleInfo")
	public int updateRoleInfo(@RequestBody Map<String,String> map,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FRoleCustom fRoleCustom=new FRoleCustom();
		fRoleCustom.setRoleid(Integer.parseInt(map.get("roleid")));
		fRoleCustom.setTitle(map.get("title"));
		fRoleCustom.setMemo(map.get("memo"));
		int flag=froleService.updateRoleInfo(fRoleCustom);
		return flag;		
	}
	
	//删除角色信息
	@ResponseBody
	@RequestMapping("/deleteRoleInfo")
	public int deleteRoleInfo(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		
		DBContextHolder.setDBType("0");
		int flag=0;
		for(int i=0;i<a.size();i++){
			flag=froleService.deleteRoleInfo(Integer.parseInt(a.get(i)));
		}
		return flag;		
	}
	
	//获取到对应角色的功能模块
	@ResponseBody
	@RequestMapping("/findRFunc")
	public List<FRolefuncCustom> findRFunc(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<FRolefuncCustom> list=froleService.findRFunc(id);
		return list;		
	}
	
	//修改对应角色Id的角色功能
	@ResponseBody
	@RequestMapping("/updateRF")
	public int updateRF(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		int rid=Integer.parseInt(m.get("rid").toString());
		froleService.delRFunc(rid);
		
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		int flag=0;
		if(a.size()!=0){
			for(int i=0;i<a.size();i++){
				FRolefuncCustom fRolefuncCustom=new FRolefuncCustom();
				fRolefuncCustom.setRoleid(Integer.valueOf(rid));
				fRolefuncCustom.setFuncid(Integer.parseInt(a.get(i)));
				flag=froleService.addRFunc(fRolefuncCustom);
			}
		}
		
		return flag;		
	}
}
