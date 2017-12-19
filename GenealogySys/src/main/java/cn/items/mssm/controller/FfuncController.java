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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.poCustom.FFunctionCustom;
import cn.items.mssm.poCustom.FRoleCustom;
import cn.items.mssm.service.FfuncService;
import cn.items.mssm.service.FroleService;

@Controller
@RequestMapping("/ffunc")
public class FfuncController {

	/** 
	 * @ClassName: FfuncController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��11��22�� ����7:39:16 
	 */
	
	@Resource
	private FfuncService ffuncService;
	
	@Resource
	private FroleService froleService;
	
	/*private static Map<String, Object> usermap=new HashMap<>();*/
	
	//��ȡ��Ӧ�û�����Ӧ�Ĺ���:�����
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findUserFunc")
	public List<Map<String, Object>> findUserFunc(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		int roleid=0;
		List<Map<String, Object>> list=new ArrayList<>();
		List<FRoleCustom> rlist=froleService.findUserRoleTitle(Integer.parseInt(usermap.get("userid").toString()));
		for(int i=0;i<rlist.size();i++){
			if((rlist.get(i).getTitle()).equals("����")){
				roleid=rlist.get(i).getRoleid();
			}else if((rlist.get(i).getTitle()).equals("�����Ա")){
				roleid=rlist.get(i).getRoleid();
				break;
			}else if((rlist.get(i).getTitle()).equals("ģ�����Ա")){
				roleid=rlist.get(i).getRoleid();
				break;
			}
		}
		FFunctionCustom fFunctionCustom=new FFunctionCustom();
		fFunctionCustom.setParentid(0);
		fFunctionCustom.setFlag("����");
		fFunctionCustom.setUserid(Integer.parseInt(usermap.get("userid").toString()));
		fFunctionCustom.setRoleid(roleid);
		List<FFunctionCustom> parentList=ffuncService.findUserFunc(fFunctionCustom);
		for(int i=0;i<parentList.size();i++){
			Map<String, Object> funcmap=new HashMap<>();
			funcmap.put("id",parentList.get(i).getFuncid());
			funcmap.put("text",parentList.get(i).getTitle());
			funcmap.put("memo",parentList.get(i).getMemo());
			if(parentList.get(i).getUrl()==null){
				funcmap.put("attr","");
			}else{
				funcmap.put("attr",parentList.get(i).getUrl());	
			}					
			
			FFunctionCustom f=new FFunctionCustom();
			f.setParentid(parentList.get(i).getFuncid());
			f.setFlag("����");
			f.setUserid(Integer.parseInt(usermap.get("userid").toString()));
			f.setRoleid(roleid);
			List<FFunctionCustom> childList=ffuncService.findUserFunc(f);
			
			funcmap.put("children",childList);
			list.add(funcmap);
		}		
		return list;		
	}
	
	//��ȡ�û��Ľ�ɫ
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findUserR")
	public Map<String,Object> findUserR(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		String role=null;
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		List<FRoleCustom> rlist=froleService.findUserRoleTitle(Integer.parseInt(usermap.get("userid").toString()));
		for(int i=0;i<rlist.size();i++){
			if((rlist.get(i).getTitle()).equals("����")){
				role=rlist.get(i).getTitle();
			}else if((rlist.get(i).getTitle()).equals("�����Ա")){
				role=rlist.get(i).getTitle();
				break;
			}else if((rlist.get(i).getTitle()).equals("ģ�����Ա")){
				role=rlist.get(i).getTitle();
				break;
			}
		}
		Map<String,Object> m=new HashMap<>();
		m.put("role",role);
		return m;		
	}
	
	//���ҳ����еĹ���
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
	
	//��ȡ���е� ������ģ��
	@ResponseBody
	@RequestMapping("/findParents")
	public List<FFunctionCustom> findParents(HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");		
		return ffuncService.findParents();		
	}
	
	//����һ������ģ��
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
	
	//����Id��ȡ������Ϣ
	@ResponseBody
	@RequestMapping("/findFuncById")
	public FFunctionCustom findFuncById(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		return ffuncService.findFuncById(id);		
	}
	
	//���¹�����Ϣ
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
	
	//ɾ��������Ϣ
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
