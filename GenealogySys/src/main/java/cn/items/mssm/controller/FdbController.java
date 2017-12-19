/**
 * 
 */
package cn.items.mssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.CreateDataBase;
import cn.items.mssm.entity.DBoperation;
import cn.items.mssm.entity.PropertiesUtil;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;
import cn.items.mssm.service.FdbService;
import cn.items.mssm.service.FproService;
import cn.items.mssm.service.FroleService;
import cn.items.mssm.service.FuserService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/fdb")
public class FdbController {

	/** 
	 * @ClassName: FdbController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��12�� ����9:33:38 
	 */
	@Resource
	private FdbService fdbService;
	
	@Resource
	private FproService fproService;
	
	@Resource
	private FuserService fuserService;
	
	@Resource
	private FroleService froleService;
	
	public static String serverId;
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/
	
	@ResponseBody
	@RequestMapping("/addDB")
	public int addDB(@RequestBody Map<String, String> map,HttpServletResponse res) throws Exception{
		String FamTitle=map.get("surname");
		String FamLocal=map.get("address");
		int count=fdbService.findDBCount(FamTitle);  //�鿴���ݿ��ʶ�Ƿ��ظ�
		String UserName=map.get("username");
		String PassWord=map.get("pwd");
		String Identity=map.get("sfz");
		String Phone=map.get("phone");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		FUserCustom fUserCustom=new FUserCustom();
		fUserinfoCustom.setUsername(UserName);
		fUserinfoCustom.setPassword(PassWord);
		fUserinfoCustom.setPhone(Phone);
		fUserinfoCustom.setIdentity(Identity);
		fUserCustom.setUsername(UserName);
		fUserCustom.setPassword(PassWord);
		
		CreateDataBase createDataBase=new CreateDataBase();
		FDatabaseCustom fDatabaseCustom=createDataBase.CreateDB(count, FamTitle, FamLocal); //�������ݿ�
		
		int flag=fdbService.addDBInfo(fDatabaseCustom);
		int Id=fdbService.findLatestId();
		fproService.addFamPro(Id,FamLocal);
		fUserCustom.setDbid(Id);
	    serverId=Id+"";
	    
	    DBContextHolder.setDBType(serverId);//�ڴӿ��������û���Ϣ
		fdbService.addManaFirst(fUserinfoCustom);  
		
		int uinfo=fdbService.findLatestUser();
		fUserCustom.setUserinfoid(uinfo);
		
		DBContextHolder.setDBType("0");
		fdbService.addUserInfo(fUserCustom);   //�������������û���Ϣ
		
		int uid=fuserService.findLatestUser();
		int rid=froleService.findRIdByName("�����Ա");
		FUserroleCustom fUserroleCustom=new FUserroleCustom();
		fUserroleCustom.setUserid(uid);
		fUserroleCustom.setRoleid(rid);
		fuserService.addURole(fUserroleCustom);
	
		return flag;		
	}
	
	//��ȡ��ӦId�����ݿ���Ϣ�����б���
	@ResponseBody
	@RequestMapping("/backDB")
	public boolean backDB(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FDatabaseCustom fDatabaseCustom=fdbService.findDbById(Integer.parseInt("1"));
		boolean flag=DBoperation.DBbackup("localhost", fDatabaseCustom.getUsername(), fDatabaseCustom.getPassword(),PropertiesUtil.getProperties("/db.properties", "savePath"), fDatabaseCustom.getDatabasekey()+".sql",fDatabaseCustom.getDatabasekey());
		return flag;		
	}
	
	//��ȡ��ӦId�����ݿ���Ϣ�����е���
	@ResponseBody
	@RequestMapping("/importDB")
	public boolean importDB(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FDatabaseCustom fDatabaseCustom=fdbService.findDbById(Integer.parseInt("1"));
		boolean flag=DBoperation.DBimport("localhost", fDatabaseCustom.getUsername(), fDatabaseCustom.getPassword(),PropertiesUtil.getProperties("/db.properties", "savePath"), fDatabaseCustom.getDatabasekey()+".sql",fDatabaseCustom.getDatabasekey());
		return flag;		
	}
}
