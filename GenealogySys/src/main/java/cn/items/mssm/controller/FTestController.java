package cn.items.mssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.items.mssm.entity.FileProcess;
import cn.items.mssm.entity.ImgUtil;
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
		String content=ImgUtil.getImageSrc(map.get("content"));
		FileProcess.delAllFile(PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		fileProcess.CopyFile(PropertiesUtil.getProperties("/PicsStore.properties","oldPicUrl"), PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		
		return null;		
	}
}
