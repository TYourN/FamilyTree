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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.poCustom.FRolefuncCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;
import cn.items.mssm.service.FroleService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fuser")
public class FuserController {

	/** 
	 * @ClassName: FuserController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��11��22�� ����7:38:55 
	 */
	
	@Resource
	private FuserService fuserService;
	
	@Resource
	private FroleService froleService;
	
	public static PicsSave pics=new PicsSave();
	
	//��ȡ���˵Ļ�����Ϣ
	@ResponseBody
	@RequestMapping("/findAllperson")
	public Map<String, Object> findAllperson(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FUserinfoCustom> list=fuserService.findAllperson();
		PageInfo<FUserinfoCustom> pageInfo=new PageInfo<FUserinfoCustom>(list);
		long total=pageInfo.getTotal();
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;		
	}
	
	//��ѯ��ӦID�����˵���Ϣ
	@ResponseBody
	@RequestMapping("/findpersonById")
	public FUserinfoCustom findpersonById(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(id);
		return fUserinfoCustom;		
	}
	
	//�����������ݿ������������Ϣ
	@ResponseBody
	@RequestMapping("/addPersonInfo")
	public int addPersonInfo(@RequestParam MultipartFile[] file,FUserinfoCustom fUserinfoCustom,HttpSession session,HttpServletResponse res)throws Exception{
		//�ڴӿ�������������Ϣ
		DBContextHolder.setDBType("1");		
		String url=null;
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		fUserinfoCustom.setPic(url);
		fuserService.addUserInfo(fUserinfoCustom);
		Integer LatestId=fuserService.findLatestUserInfo();
		
		//���������������˵ĵ�¼��Ϣ
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(fUserinfoCustom.getUsername());
		fUserCustom.setPassword(fUserinfoCustom.getPassword());
		fUserCustom.setUserinfoid(LatestId);
		fUserCustom.setDbid(Integer.parseInt("1"));
		int flag=fuserService.addUser(fUserCustom);
				
		return flag;		
	}
	
	//�����������ݿ����޸�������Ϣ
	@ResponseBody
	@RequestMapping("/updatePersonInfo")
	public int updatePersonInfo(@RequestParam int id,@RequestParam MultipartFile[] file,FUserinfoCustom fUserinfoCustom,HttpSession session,HttpServletResponse res)throws Exception{
		//�ڴӿ����޸�������Ϣ
		DBContextHolder.setDBType("1");
		String url=null;
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		fUserinfoCustom.setPic(url);
		fUserinfoCustom.setUserinfoid(Integer.valueOf(id));
		fuserService.updateUserInfo(fUserinfoCustom);
		
		//���������޸�������Ϣ
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(fUserinfoCustom.getUsername());
		fUserCustom.setPassword(fUserinfoCustom.getPassword());
		fUserCustom.setUserinfoid(Integer.valueOf(id));
		int flag=fuserService.updateUser(fUserCustom);
		
		return flag;		
	}
	
	//�����������ݿ����޸�������Ϣ
	@ResponseBody
	@RequestMapping("/delPersonInfo")
	public int delPersonInfo(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		//�ڴӿ���ɾ������
		DBContextHolder.setDBType("1");
		for(int i=0;i<a.size();i++){
			fuserService.deleteUserInfo(Integer.parseInt(a.get(i)));
		}
		
		//��������ɾ������
		DBContextHolder.setDBType("0");
		int flag=0;
		for(int i=0;i<a.size();i++){
			flag=fuserService.deleteUser(Integer.parseInt(a.get(i)));
		}
		
		return flag;		
	}
	
	//��ȡ��Ӧ�û��Ľ�ɫ�͹���
	@ResponseBody
	@RequestMapping("/findURF")
	public Map<String, Object> findURF(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<FUserroleCustom> rlist=fuserService.findURole(id);
		List<FRolefuncCustom> flist=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		
		if(rlist.size()==0){			
		}else{
			for(int i=0;i<rlist.size();i++){
				List<FRolefuncCustom> list=froleService.findRFunc(rlist.get(i).getRoleid());
				if(list.size()!=0){
					for(int j=0;j<list.size();j++){
						flist.add(list.get(j));
					}
				}
			}
		}
		
		map.put("role", rlist);
		map.put("func", flist);
		
		return map;		
	}
	
	//�޸Ķ�Ӧ�û�Id���û���ɫ
	@ResponseBody
	@RequestMapping("/updateUR")
	public int updateUR(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		int uid=Integer.parseInt(m.get("uid").toString());		
		int flag=fuserService.delURole(uid);
				
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		if(a.size()!=0){
			for(int i=0;i<a.size();i++){
				FUserroleCustom fUserroleCustom=new FUserroleCustom();
				fUserroleCustom.setUserid(Integer.valueOf(uid));
				fUserroleCustom.setRoleid(Integer.parseInt(a.get(i)));
				fuserService.addURole(fUserroleCustom);
			}
		}
				
		return flag;		
	}
}
