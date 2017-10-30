package cn.items.mssm.controller;

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

import cn.items.mssm.entity.FileProcess;
import cn.items.mssm.entity.ImgUtil;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.PropertiesUtil;
import cn.items.mssm.service.FSnameService;


@Controller
@RequestMapping("/ftest")
public class FTestController {
	@Resource
	private FSnameService fSnameService;
	
	private static FileProcess fileProcess=new FileProcess();
	
	@RequestMapping("/Test")
	public String Test(@RequestBody Map<String, String> map ,HttpServletResponse res){
		String content=PicsSave.uploadContent(map.get("content"));
		
		System.out.println(content);
		return null;		
	}
	
	@ResponseBody
	@RequestMapping("/Upload")
	public int Upload(@RequestParam MultipartFile[] file,HttpServletResponse res)throws Exception{
		System.out.println();
		return 0;		
	}
}
