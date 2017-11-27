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

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.ImgUtil;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FProfileCustom;
import cn.items.mssm.service.FproService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fmhp")
public class FmhpController {

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
	
	//查找对应家族的家族简介信息 
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamPro")
	public FProfileCustom findFamPro(/*HttpSession session,*/HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		/*Map<String,String> map=(Map<String, String>) session.getAttribute("user");
		FProfileCustom fProfileCustom=fproService.findFamPro(Integer.parseInt(map.get("userid")));*/
		List<Integer> list=new ArrayList<>();
		FProfileCustom fProfileCustom=fproService.findFamPro(1);
		if(fProfileCustom.getUrl()==null){
			
		}else{
			fProfileCustom.setHtml(StringUtil.SpliceUrl(fProfileCustom.getUrl()));
			fProfileCustom.setUrlList(StringUtil.CutComma(fProfileCustom.getUrl()));
		}
		Map<String, Object> cmap=new HashMap<>();
		cmap.put("title","族人");
		/*cmap.put("dbid", Integer.parseInt(map.get("serverId")));*/
		cmap.put("dbid",1);
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
	@ResponseBody
	@RequestMapping("/updateFamPro")
	public int updateFamPro(@RequestBody Map<String, String> map,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		String url=null;
		FProfileCustom fProfileCustom=new FProfileCustom();
		
		if(map.get("url")!=null){
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
		fProfileCustom.setDbid(1);   //后期修改
		int flag=fproService.updateFamPro(fProfileCustom);
		return flag;		
	}
}
