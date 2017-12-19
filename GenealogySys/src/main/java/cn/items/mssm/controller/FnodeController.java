package cn.items.mssm.controller;

import java.util.ArrayList;
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

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.po.FNode;
import cn.items.mssm.poCustom.FNodeCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FnodeService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("fnode")
public class FnodeController {

	/** 
	 * @ClassName: FnodeController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月30日 上午10:04:47 
	 */
	
	@Resource
	private FnodeService fnodeService;
	
	@Resource
	private FuserService fuserService;
	
	//按对应条件获取到家谱信息 
	@ResponseBody
	@RequestMapping("/findNodeByCount")
	public List<Map<String,Object>> findNodeByCount(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<Map<String,Object>> nodelist=new ArrayList<>();
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		List<FNodeCustom> list=fnodeService.findNodeByCount(Integer.parseInt(map.get("count")));
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=new HashMap<>();
			m.put("id",list.get(i).getId());
			m.put("pId",list.get(i).getParentid());
			m.put("name",list.get(i).getName());
			m.put("sex",list.get(i).getSex());
			m.put("spouse",list.get(i).getConsort());
			nodelist.add(m);
		}
		return nodelist;		
	}
	
	//展示端按对应条件获取到家谱信息 
	@ResponseBody
	@RequestMapping("/findshNodeByCount")
	public List<Map<String,Object>> findshNodeByCount(@RequestBody Map<String,String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");  
		List<Map<String,Object>> nodelist=new ArrayList<>();
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		List<FNodeCustom> list=fnodeService.findNodeByCount(Integer.parseInt(map.get("count")));
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=new HashMap<>();
			m.put("id",list.get(i).getId());
			m.put("pId",list.get(i).getParentid());
			m.put("name",list.get(i).getName());
			m.put("sex",list.get(i).getSex());
			m.put("spouse",list.get(i).getConsort());
			nodelist.add(m);
		}
		return nodelist;	
	}
	
	//管理端按对应条件获取到家谱信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findMaNodeByCount")
	public List<Map<String,Object>> findMaNodeByCount(@RequestParam int count,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		List<Map<String,Object>> nodelist=new ArrayList<>();
		List<FNodeCustom> list=fnodeService.findNodeByCount(count);
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=new HashMap<>();
			m.put("id",list.get(i).getId());
			m.put("pId",list.get(i).getParentid());
			m.put("name",list.get(i).getName());
			m.put("sex",list.get(i).getSex());
			m.put("spouse",list.get(i).getConsort());
			nodelist.add(m);
		}
		return nodelist;		
	}
	
	//上传一段家谱信息：展示端
	@ResponseBody
	@RequestMapping("/addNodeFrag")
	public int addNodeFrag(@RequestParam int id,@RequestBody List<Map<String,Object>> list,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		int flag=0;
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		int count=0;
		if(fnodeService.findNodeCount()!=null){
			count=(Integer)fnodeService.findNodeCount();
		}
		for(int i=0;i<list.size();i++){
			FNodeCustom fNodeCustom=new FNodeCustom();
			Map<String,Object> map=list.get(i);
			fNodeCustom.setId(Integer.parseInt(map.get("id").toString()));
			fNodeCustom.setName(map.get("name").toString());
			fNodeCustom.setParentid(Integer.parseInt(map.get("pid").toString()));
			fNodeCustom.setSex(map.get("sex").toString());
			fNodeCustom.setConsort(map.get("spouse").toString());
			fNodeCustom.setCount(count+1);
			flag=fnodeService.addNodeFrag(fNodeCustom);
		}
		return flag;		
	}
	
	//管理端按对应条件获取到家谱信息 
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findAllNode")
	public List<Map<String,Object>> findAllNode(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		List<Map<String,Object>> list=new ArrayList<>();
		if(fnodeService.findMaxCount()==null){
			
		}else{
			for(int i=0;i<=(Integer)fnodeService.findMaxCount();i++){
				Map<String,Object> nmap=new HashMap<>();
				List<Map<String,Object>> nodelist=new ArrayList<>();
				List<FNodeCustom> nlist=fnodeService.findNodeByCount(i);
				if(nlist.size()!=0){
					for(int j=0;j<nlist.size();j++){
						Map<String,Object> m=new HashMap<>();
						m.put("id",nlist.get(j).getId());
						m.put("pId",nlist.get(j).getParentid());
						m.put("name",nlist.get(j).getName());
						m.put("sex",nlist.get(j).getSex());
						m.put("spouse",nlist.get(j).getConsort());
						nodelist.add(m);
					}
					nmap.put("count",i);
					nmap.put("content",nodelist);
					list.add(nmap);
				}		
			}
		}		
		return list;		
	}
	
	//删除对应的族谱节点
	@ResponseBody
	@RequestMapping("/deleteNode")
	public int deleteNode(@RequestParam int count,HttpServletResponse res)throws Exception{
		int flag=fnodeService.deleteNode(count);
		return flag;		
	}
	
	//保存一段家谱信息：管理端
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/addMaNodeFrag")
	public int addMaNodeFrag(@RequestBody List<Map<String,Object>> list,HttpSession session,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		int flag=0;
		fnodeService.deleteNode(0);
		for(int i=0;i<list.size();i++){
			FNodeCustom fNodeCustom=new FNodeCustom();
			Map<String,Object> map=list.get(i);
			fNodeCustom.setId(Integer.parseInt(map.get("id").toString()));
			fNodeCustom.setName(map.get("name").toString());
			fNodeCustom.setParentid(Integer.parseInt(map.get("pid").toString()));
			fNodeCustom.setSex(map.get("sex").toString());
			fNodeCustom.setConsort(map.get("spouse").toString());
			fNodeCustom.setCount(0);
			flag=fnodeService.addNodeFrag(fNodeCustom);
		}
		return flag;		
	}
}
