/**
 * 
 */
package cn.items.mssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.CreateDataBase;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.service.FdbService;

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
	 * @date: 2017年10月12日 上午9:33:38 
	 */
	@Resource
	private FdbService fdbService;
	
	public static String serverId;
	
	@ResponseBody
	@RequestMapping("/addDB")
	public int addDB(@RequestBody Map<String, String> map ,HttpServletResponse res) throws Exception{
		String FamTitle=map.get(" ");
		String FamLocal=map.get(" ");
		int count=fdbService.findDBCount(FamTitle);  //查看数据库标识是否重复
		String UserName=map.get(" ");
		String PassWord=map.get(" ");
		String Identity=map.get(" ");
		String Phone=map.get(" ");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		FUserCustom fUserCustom=new FUserCustom();
		fUserinfoCustom.setUsername(UserName);
		fUserinfoCustom.setPassword(PassWord);
		fUserinfoCustom.setPhone(Phone);
		fUserinfoCustom.setIdentity(Identity);
		fUserCustom.setUsername(UserName);
		fUserCustom.setPassword(PassWord);
		
		CreateDataBase createDataBase=new CreateDataBase();
		FDatabaseCustom fDatabaseCustom=createDataBase.CreateDB(count, FamTitle, FamLocal); //增加数据库
		
		int flag=fdbService.addDBInfo(fDatabaseCustom);
		int Id=fdbService.findLatestId();
		fUserCustom.setDbid(Id);
	    serverId=Id+"";
	    
	    DBContextHolder.setDBType(serverId);//在从库中增加用户信息
		fdbService.addManaFirst(fUserinfoCustom);  
		
		int uinfo=fdbService.findLatestUser();
		fUserCustom.setUserinfoid(uinfo);
		
		DBContextHolder.setDBType("0");
		fdbService.addUserInfo(fUserCustom);   //在主库中增加用户信息
	
		return flag;		
	}
	
	
}
