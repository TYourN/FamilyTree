package cn.items.mssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.items.mssm.entity.ImgUtil;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.ResponseUtil;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.service.FhomePageService;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/fhp")
public class FhpController {

	/** 
	 * @ClassName: FhpController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月16日 下午7:34:22 
	 */
	@Resource
	private FhomePageService fhomePageService;
	
	public static PicsSave pics=new PicsSave();
	
	//读取首页第一页的图片
	@ResponseBody
	@RequestMapping("/findFirstPics")
	public List<String> findFirstPics(HttpServletResponse res)throws Exception{
		List<String> list=fhomePageService.findFirstPics();
		return list;		
	}
	
	//读取首页第二页的图片
	@ResponseBody
	@RequestMapping("/findSecondPics")
	public List<String> findSecondPics(HttpServletResponse res)throws Exception{
		String PicOne=fhomePageService.findSecondPic("在线编修");
		String PicTwo=fhomePageService.findSecondPic("随时查看");
		String PicThree=fhomePageService.findSecondPic("家族圈");
		List<String> list=new ArrayList<String>();
		list.add(PicOne);
		list.add(PicTwo);
		list.add(PicThree);
		return list;		
	}
	
	//获取首页三页的数据
	@ResponseBody
	@RequestMapping("/findForthPics")
	public List<FFamnewsCustom> findForthPics(HttpServletResponse res)throws Exception{
		List<FFamnewsCustom> list=fhomePageService.findHPNews();
		return list;		
	}
	
	//首页第一页图片的上传
	@ResponseBody
	@RequestMapping("/addFirstPics")
	public int addFirstPics(@RequestParam MultipartFile file,@RequestParam("Flag") String Flag,HttpServletResponse res)throws Exception{
		//新图片名=获取时间戳+图片扩展名
		String newFileName=pics.picsProcess(file);
		
		String fileUrl=pics.saveFile(newFileName, file);
		PicsSave.uploadFile(fileUrl, newFileName);
		String url=pics.saveFileString(newFileName);
		
		Map<String, String> map=StringUtil.JudgeFlag(Integer.parseInt(Flag));
		
		int flag=fhomePageService.addFSPics("",url,map.get("Memo"),map.get("Flag"));
		return flag;		
	}
	
	@ResponseBody
	@RequestMapping("/deleteMianPics")
	public int deleteMianPics(@RequestParam int picid,HttpServletResponse res)throws Exception{
		int flag=fhomePageService.deleteMianPics(picid);
		return 0;		
	}
}
