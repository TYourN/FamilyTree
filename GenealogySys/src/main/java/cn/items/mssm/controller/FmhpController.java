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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.ImgUtil;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FProfileCustom;
import cn.items.mssm.service.FproService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fmhp")
public class FmhpController{

	/** 
	 * @ClassName: FmhpController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月13日 下午1:19:09 
	 */
	
	@Resource 
	private FproService fproService;
	
	@Resource
	private FuserService fuserService;
	
	public static PicsSave pics=new PicsSave();
	
	/*public static Map<String, Object> usermap=new HashMap<>();*/
	
	//查找对应家族的家族简介信息 
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamPro")
	public FProfileCustom findFamPro(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		List<Integer> list=new ArrayList<>();
		FProfileCustom fProfileCustom=fproService.findFamPro(Integer.parseInt(usermap.get("userid").toString()));
		if(fProfileCustom.getUrl()==null){
			
		}else{
			fProfileCustom.setHtml(StringUtil.SpliceUrl(fProfileCustom.getUrl()));
			fProfileCustom.setUrlList(StringUtil.CutComma(fProfileCustom.getUrl()));
		}
		Map<String, Object> cmap=new HashMap<>();
		cmap.put("title","族人");
		cmap.put("dbid",Integer.parseInt(usermap.get("dbid").toString()));
		Integer allCount=fuserService.findAllCount(cmap);
		cmap.put("title","族管理员");
		Integer manaCount=fuserService.findAllCount(cmap);
		list.add(allCount);
		list.add(manaCount);
		list.add(allCount-manaCount);
		fProfileCustom.setCountList(list);
		return fProfileCustom;		
	}
	
	
	//对家族简介信息进行修改 
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateFamPro")
	public int updateFamPro(HttpSession session,@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		Map<String, Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType("0");
		String url=null;
		FProfileCustom fProfileCustom=new FProfileCustom();
		
		if(map.get("url")!=null){
			@SuppressWarnings("static-access")
			String Content=pics.uploadContent(map.get("url"));
			List<String> list=ImgUtil.getImgName(Content);
			for(int i=0;i<list.size();i++){
				if(url==null){
					url=list.get(i);
				}else{
					url=url+","+list.get(i);
				}
			}
		}
		
		fProfileCustom.setFulname(map.get("fulname"));
		fProfileCustom.setCreateage(map.get("createage"));
		fProfileCustom.setIntroduce(map.get("introduce"));
		fProfileCustom.setUrl(url);
		fProfileCustom.setFamlocal(map.get("famlocal"));
		fProfileCustom.setDbid(Integer.parseInt(usermap.get("dbid").toString()));   //后期修改
		int flag=fproService.updateFamPro(fProfileCustom);
		return flag;		
	}
}
