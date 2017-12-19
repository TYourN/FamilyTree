package cn.items.mssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FRepliesCustom;
import cn.items.mssm.poCustom.FUserCustom;
import cn.items.mssm.service.FrepliesService;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/frep")
public class FrepliesController {

	/** 
	 * @ClassName: FrepliesController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年12月3日 上午11:17:10 
	 */
	
	@Resource
	private FrepliesService frepliesService;
	
	@Resource
	private FuserService fuserService;
	
	public static PicsSave pics=new PicsSave();
	
	//手机端对某一个帖子发表回复
	@ResponseBody
	@RequestMapping("/addReplies")
	public int addReplies(@RequestParam MultipartFile[] img,int id,int postid,int parentid, String content,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");		
		String urlContent="";
		if(img.length!=0){			
			for(int i=0;i<img.length;i++){
				String newFileName=pics.picsProcess(img[i]); 	

				String fileUrl=pics.saveFile(newFileName, img[i]);
				PicsSave.uploadFile(fileUrl, newFileName);
				
				String url=pics.saveFileString(newFileName);
				urlContent=StringUtil.SplicePRUrl(urlContent, url);
			}
		}
		content=content+urlContent;
		
		FRepliesCustom fRepliesCustom=new FRepliesCustom();
		fRepliesCustom.setReplierid(fUserCustom.getUserinfoid());
		fRepliesCustom.setPostid(Integer.valueOf(postid));
		fRepliesCustom.setParentid(Integer.valueOf(parentid));
		fRepliesCustom.setContent(content);
		fRepliesCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		int flag=frepliesService.addReplies(fRepliesCustom);
		return flag;		
	}
	
	//展示端对某一个帖子发表回复
	@ResponseBody
	@RequestMapping("/addShReplies")
	public int addShReplies(@RequestParam MultipartFile[] img,int id,int itemid,int parentid,String content,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FUserCustom fUserCustom=fuserService.findUserById(id);
		DBContextHolder.setDBType(fUserCustom.getDbid()+"");		
		String urlContent="";
		if(img.length!=0){			
			for(int i=0;i<img.length;i++){
				String newFileName=pics.picsProcess(img[i]); 	

				String fileUrl=pics.saveFile(newFileName, img[i]);
				PicsSave.uploadFile(fileUrl, newFileName);
				
				String url=pics.saveFileString(newFileName);
				urlContent=StringUtil.SplicePRUrl(urlContent, url);
			}
		}
		content=content+urlContent;
		
		FRepliesCustom fRepliesCustom=new FRepliesCustom();
		fRepliesCustom.setReplierid(fUserCustom.getUserinfoid());
		fRepliesCustom.setPostid(Integer.valueOf(itemid));
		fRepliesCustom.setParentid(Integer.valueOf(parentid));
		fRepliesCustom.setContent(content);
		fRepliesCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		int flag=frepliesService.addReplies(fRepliesCustom);
		return flag;		
	}
}
