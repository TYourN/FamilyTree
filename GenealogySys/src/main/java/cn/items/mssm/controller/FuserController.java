package cn.items.mssm.controller;

import java.io.InputStream;
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
import cn.items.mssm.entity.SqlConnect;
import cn.items.mssm.entity.excelBot;
import cn.items.mssm.poCustom.FAdminCustom;
import cn.items.mssm.poCustom.FDatabaseCustom;
import cn.items.mssm.poCustom.FRolefuncCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.poCustom.FUserinfoCustom;
import cn.items.mssm.poCustom.FUserroleCustom;
import cn.items.mssm.service.FdbService;
import cn.items.mssm.service.FroleService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fuser")
public class FuserController {

	/** 
	 * @ClassName: FuserController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月22日 下午7:38:55 
	 */
	
	@Resource
	private FuserService fuserService;
	
	@Resource
	private FdbService fdbService;
	
	@Resource
	private FroleService froleService;
	
	public static PicsSave pics=new PicsSave();
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/   //管理端的Session	
	
	public static int userid;
	
	//用户名获取用户图片：手机端
	@ResponseBody
	@RequestMapping("/getUserImg")
	public Map<String, Object> getUserImg(@RequestParam String username,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String, Object> m=new HashMap<>();
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(username);
		FUserCustom f=fuserService.findUserE(fUserCustom);
		if(f.getNum()==0){
			m.put("img","");
		}else{
			DBContextHolder.setDBType(String.valueOf(f.getDbid()));
			FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(f.getUserinfoid());
			m.put("img",fUserinfoCustom.getPic());
		}
		return m;		
	}
	
	//获取用户基本信息
	@ResponseBody
	@RequestMapping("/getUserDety")
	public Map<String,Object> getUserDety(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");	
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(fUserCustom.getUserinfoid());
		Map<String,Object> m=new HashMap<>();
		m.put("name",fUserinfoCustom.getName());
		m.put("place",fUserinfoCustom.getAddress());
		m.put("img", fUserinfoCustom.getPic());
		return m;		
	}
	
	//用户登录验证：手机端
	@ResponseBody
	@RequestMapping("/anUserLogin")
	public Map<String, Object> anUserLogin(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String, Object> m=new HashMap<>();
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(map.get("username"));
		fUserCustom.setPassword(map.get("userpwd"));
		FUserCustom f=fuserService.findUserE(fUserCustom);
		if(f.getNum()!=0){
			m.put("id",f.getUserid());
			userid=f.getUserid();
		}
		return m;		
	}
	
	//用户登录验证：展示端
	@ResponseBody
	@RequestMapping("/shUserLogin")
	public Map<String, Object> shUserLogin(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String, Object> m=new HashMap<>();
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(map.get("name"));
		fUserCustom.setPassword(map.get("pwd"));
		FUserCustom f=fuserService.findUserE(fUserCustom);
		if(f.getNum()!=0){
			m.put("id",f.getUserid());
			userid=f.getUserid();
		}
		return m;	
	}
	
	//用户登录验证：管理端
	@ResponseBody
	@RequestMapping("/userLogin")
	public int userLogin(@RequestParam String username,@RequestParam String password,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		String state=null;
		int flag=0;
		Map<String, Object> userMap=new HashMap<>();
		FAdminCustom fAdminCustom=new FAdminCustom();
		fAdminCustom.setUsername(username);
		fAdminCustom.setPassword(password);
		FAdminCustom fa=fuserService.findAdminE(fAdminCustom);
		if(fa.getNum()!=0){
			flag=4;
			userMap.put("userid",fa.getAdminid());
			userMap.put("dbid", 0+"");
			session.setAttribute("userinfo", userMap);
		}else{			
			FUserCustom fUserCustom=new FUserCustom();
			fUserCustom.setUsername(username);
			fUserCustom.setPassword(password);
			FUserCustom f=fuserService.findUserE(fUserCustom);
			if(f.getNum()!=0){
				FDatabaseCustom fDatabaseCustom=fdbService.findDbById(f.getDbid());
				state=SqlConnect.getConnection(fDatabaseCustom.getDriverclass(),fDatabaseCustom.getUrl(),fDatabaseCustom.getUsername(),fDatabaseCustom.getPassword());
				if(state.equals("连接成功")){
					userMap.put("userid",f.getUserid());
					userMap.put("dbid", f.getDbid()+"");
					userMap.put("userinfoid", f.getUserinfoid());
					session.setAttribute("userinfo", userMap);
					flag=1;
				}else if(state.equals("加载失败")){
					flag=2;
				}else{
					flag=3;
				}
			}
		}	
		return flag;		
	}
	
	//用户信息注销
	@ResponseBody
	@RequestMapping("/Logout")
	public int Logout(HttpSession session,HttpServletResponse res)throws Exception{
		if(session.getAttribute("userinfo")!=null){
			session.invalidate();
		}
		return 1;		
	}
	
	//获取族人的基本信息
	@ResponseBody
	@RequestMapping("/findAllperson")
	public Map<String, Object> findAllperson(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(p.get("dbid").toString());
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
	
	//获取对应Id的人员的信息:手机端
	@ResponseBody
	@RequestMapping("/findAnPersonById")
	public Map<String, Object> findpersonById(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(id);
		Map<String, Object> map=new HashMap<>();
		map.put("img",fUserinfoCustom.getPic());
		map.put("username",fUserinfoCustom.getName());
		map.put("userage", fUserinfoCustom.getAge());
		map.put("usergender",fUserinfoCustom.getSex());
		map.put("useraddress",fUserinfoCustom.getAddress());
		map.put("userphone",fUserinfoCustom.getPhone());
		map.put("userms",fUserinfoCustom.getMarriage());
		return map;		
	}
	
	//获取对应Id的人员的信息:管理端
	@ResponseBody
	@RequestMapping("/findShPersonById")
	public Map<String,Object> findShPersonById(@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(id);
		Map<String, Object> map=new HashMap<>();
		map.put("userhp",fUserinfoCustom.getPic());
		map.put("username",fUserinfoCustom.getName());
		map.put("userage", fUserinfoCustom.getAge());
		map.put("usergender",fUserinfoCustom.getSex());
		map.put("useraddress",fUserinfoCustom.getAddress());
		map.put("userphone",fUserinfoCustom.getPhone());
		map.put("userms",fUserinfoCustom.getMarriage());
		return map;		
	}
	
	//查询对应ID的族人的信息
	@ResponseBody
	@RequestMapping("/findpersonById")
	public FUserinfoCustom findpersonById(@RequestParam int id,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(p.get("dbid").toString());
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(id);
		return fUserinfoCustom;		
	}
	
	//根据Session获取人员的信息
	@ResponseBody
	@RequestMapping("/findUserDetail")
	public FUserinfoCustom findUserDetail(HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(p.get("dbid").toString());
		FUserinfoCustom fUserinfoCustom=fuserService.findpersonById(Integer.parseInt(p.get("userinfoid").toString()));		
		return fUserinfoCustom;		
	}
	
	//在主、从数据库中添加族人信息
	@ResponseBody
	@RequestMapping("/addPersonInfo")
	public int addPersonInfo(@RequestParam MultipartFile[] file,FUserinfoCustom fUserinfoCustom,HttpSession session,HttpServletResponse res)throws Exception{
		//在从库中增加族人信息
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(p.get("dbid").toString());	
		String url=null;
		if(file.length!=0){
			String newFileName=pics.picsProcess(file[0]); 	

			String fileUrl=pics.saveFile(newFileName, file[0]);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			url=pics.saveFileString(newFileName);
		}
		fUserinfoCustom.setPic(url);
		if((fUserinfoCustom.getSex()).equals("")){
			fUserinfoCustom.setSex("男");
		}
		fuserService.addUserInfo(fUserinfoCustom);
		Integer LatestId=fuserService.findLatestUserInfo();				
		
		//在主库中增加族人的登录信息
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(fUserinfoCustom.getUsername());
		fUserCustom.setPassword(fUserinfoCustom.getPassword());
		fUserCustom.setUserinfoid(LatestId);
		fUserCustom.setDbid(Integer.parseInt(p.get("dbid").toString()));
		int flag=fuserService.addUser(fUserCustom);
		
		FUserroleCustom fUserroleCustom=new FUserroleCustom();
		int uid=fuserService.findLatestUser();
		int roleid=froleService.findRIdByName("族人");
		fUserroleCustom.setUserid(uid);
		fUserroleCustom.setRoleid(roleid);
		fuserService.addURole(fUserroleCustom);
						
		return flag;		
	}
	
	//在主、从数据库中修改族人信息
	@ResponseBody
	@RequestMapping("/updatePersonInfo")
	public int updatePersonInfo(@RequestParam int id,@RequestParam MultipartFile[] file,FUserinfoCustom fUserinfoCustom,HttpSession session,HttpServletResponse res)throws Exception{
		//在从库中修改族人信息
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(p.get("dbid").toString());
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
		
		//在主库中修改族人信息		
		DBContextHolder.setDBType("0");
		Map<String,Integer> a=new HashMap<>();
		a.put("dbid",Integer.parseInt(p.get("dbid").toString()));
		a.put("userinfoid",id);
		int uid=fuserService.findUserIdByAu(a);
		FUserCustom fUserCustom=new FUserCustom();
		fUserCustom.setUsername(fUserinfoCustom.getUsername());
		fUserCustom.setPassword(fUserinfoCustom.getPassword());
		fUserCustom.setUserid(Integer.valueOf(uid));
		int flag=fuserService.updateUser(fUserCustom);
		
		return flag;		
	}
	
	//从库中修改用户信息:手机端
	@ResponseBody
	@RequestMapping("/updateCongInfo")
	public int updateCongInfo(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setName(map.get("username"));
		fUserinfoCustom.setAge(Integer.parseInt(map.get("age")));
		fUserinfoCustom.setAddress(map.get("address"));
		fUserinfoCustom.setPhone(map.get("phone"));
		fUserinfoCustom.setMarriage(map.get("ms"));
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		int flag=fuserService.updateUserInfo(fUserinfoCustom);
		return flag;		
	}
	
	//从库中修改用户信息:展示端
	@ResponseBody
	@RequestMapping("/updateShCongInfo")
	public int updateShCongInfo(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setName(map.get("name"));
		fUserinfoCustom.setAge(Integer.parseInt(map.get("age")));
		fUserinfoCustom.setSex(map.get("sex"));
		fUserinfoCustom.setAddress(map.get("address"));
		fUserinfoCustom.setPhone(map.get("phone"));
		fUserinfoCustom.setMarriage(map.get("ms"));
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		int flag=fuserService.updateUserInfo(fUserinfoCustom);
		return flag;		
	}
	
	//从库中修改用户头像:手机端
	@ResponseBody
	@RequestMapping("/udatePic")
	public String updatePic(@RequestParam MultipartFile img,@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		String newFileName=pics.picsProcess(img); 	

		String fileUrl=pics.saveFile(newFileName, img);
		PicsSave.uploadFile(fileUrl, newFileName);
		
		String url=pics.saveFileString(newFileName);
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setPic(url);
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		fuserService.updateUserInfo(fUserinfoCustom);
		
		return url;		
	}
	
	//从库中修改用户头像:展示端
	@ResponseBody
	@RequestMapping("/updateShPic")
	public String updateShPic(@RequestParam MultipartFile img,@RequestParam int id,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		String newFileName=pics.picsProcess(img); 	

		String fileUrl=pics.saveFile(newFileName, img);
		PicsSave.uploadFile(fileUrl, newFileName);
		
		String url=pics.saveFileString(newFileName);
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setPic(url);
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		fuserService.updateUserInfo(fUserinfoCustom);
		
		return url;		
	}
	
	//用户修改密码:手机端
	@ResponseBody
	@RequestMapping("/updatePassword")
	public int updatePassword(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		//主库修改密码
		FUserCustom f=new FUserCustom();		
		f.setPassword(map.get("newpassword"));
		f.setUserinfoid(Integer.parseInt(map.get("id")));
		fuserService.updateUser(f);
				
		//从库修改密码
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setPassword(map.get("newpassword"));
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		int flag=fuserService.updateUserInfo(fUserinfoCustom);
		return flag;		
	}
	
	//用户修改密码:展示端
	@ResponseBody
	@RequestMapping("/updateShPassword")
	public int updateShPassword(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		//主库修改密码
		FUserCustom f=new FUserCustom();		
		f.setPassword(map.get("pwd"));
		f.setUserinfoid(Integer.parseInt(map.get("id")));
		fuserService.updateUser(f);
				
		//从库修改密码
		FUserCustom fUserCustom=fuserService.findUserById(Integer.parseInt(map.get("id")));
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");
		FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
		fUserinfoCustom.setPassword(map.get("pwd"));
		fUserinfoCustom.setUserinfoid(fUserCustom.getUserinfoid());
		int flag=fuserService.updateUserInfo(fUserinfoCustom);
		return flag;		
	}
	
	//在主、从数据库中删除族人信息
	@ResponseBody
	@RequestMapping("/delPersonInfo")
	public int delPersonInfo(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		
		List<Integer> ilist=new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<String> a=(List<String>) m.get("idlist");
		
		DBContextHolder.setDBType("0");
		for(int i=0;i<a.size();i++){
			Map<String,Integer> ma=new HashMap<>();
			ma.put("dbid",Integer.parseInt(p.get("dbid").toString()));
			ma.put("userinfoid",Integer.parseInt(a.get(i)));
			int uid=fuserService.findUserIdByAu(ma);
			ilist.add(Integer.valueOf(uid));
		}
				
		//在从库中删除族人
		
		DBContextHolder.setDBType(p.get("dbid").toString());
		for(int i=0;i<a.size();i++){
			fuserService.deleteUserInfo(Integer.parseInt(a.get(i)));
		}
		
		//在主库中删除族人
		DBContextHolder.setDBType("0");
		int flag=0;
		for(int i=0;i<ilist.size();i++){
			flag=fuserService.deleteUser(ilist.get(i));
		}
		
		return flag;		
	}
	
	//获取对应用户的角色和功能
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
	
	//修改对应用户Id的用户角色
	@ResponseBody
	@RequestMapping("/updateUR")
	public int updateUR(@RequestBody Map<String,Object> m,HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType("0");
		Map<String,Integer> ma=new HashMap<>();
		ma.put("dbid",Integer.parseInt(p.get("dbid").toString()));
		ma.put("userinfoid",Integer.parseInt(m.get("uid").toString()));
		int uid=fuserService.findUserIdByAu(ma);
			
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
	
	//管理员通过Excel导入用户的基本信息
	@ResponseBody
	@RequestMapping("/insertUserByExcel")
	public int insertUserByExcel(@RequestParam MultipartFile eFile,HttpSession session,HttpServletResponse res)throws Exception{		
		InputStream in =null;
		List<Object> list=new ArrayList<>();
		int flag=0;
		if(eFile.isEmpty()){  
            throw new Exception("文件不存在！");  
        }else{
        	in = eFile.getInputStream();  
            list = new excelBot().getBankListByExcel(in,eFile.getOriginalFilename());  
            in.close();
            if(list.size()==1){
            	flag=2;
            }else{
            	for(int i=1;i<list.size();i++){
            		@SuppressWarnings("unchecked")
            		Map<String,Object> p=(Map<String, Object>) session.getAttribute("userinfo");
            		DBContextHolder.setDBType(p.get("dbid").toString());
                    @SuppressWarnings("unchecked")
    				List<Object> l=(List<Object>) list.get(i);
                    FUserinfoCustom fUserinfoCustom=new FUserinfoCustom();
                    fUserinfoCustom.setUsername(l.get(0).toString());
                    fUserinfoCustom.setPassword(l.get(1).toString());
                    fUserinfoCustom.setName(l.get(2).toString());
                    fUserinfoCustom.setPhone(l.get(3).toString());
                    fUserinfoCustom.setIdentity(l.get(4).toString());
                    fUserinfoCustom.setSex(l.get(5).toString());
            		fuserService.addUserInfo(fUserinfoCustom);
            		Integer LatestId=fuserService.findLatestUserInfo();                              
                    
                	//在主库中插入用户信息
                	DBContextHolder.setDBType("0");
                	FUserCustom fUserCustom=new FUserCustom();
            		fUserCustom.setUsername(l.get(0).toString());
            		fUserCustom.setPassword(l.get(1).toString());
            		fUserCustom.setUserinfoid(LatestId);
            		fUserCustom.setDbid(Integer.parseInt(p.get("dbid").toString()));
            		flag=fuserService.addUser(fUserCustom);
            	}
            }
        }
          
		return flag;		
	}
}
