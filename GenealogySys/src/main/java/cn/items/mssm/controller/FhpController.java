package cn.items.mssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	 * @date: 2017��10��16�� ����7:34:22 
	 */
	@Resource
	private FhomePageService fhomePageService;
	
	public static PicsSave pics=new PicsSave();
	
	//��ȡ��ҳ��һҳ��ͼƬ
	@RequestMapping("/findFirstPics")
	public String findFirstPics(HttpServletResponse res)throws Exception{
		List<String> list=fhomePageService.findFirstPics();
		JSONArray array=JSONArray.fromObject(list);
		ResponseUtil.write(res, array);
		return null;		
	}
	
	//��ȡ��ҳ�ڶ�ҳ��ͼƬ
	@RequestMapping("/findSecondPics")
	public String findSecondPics(HttpServletResponse res)throws Exception{
		String PicOne=fhomePageService.findSecondPic("���߱���");
		String PicTwo=fhomePageService.findSecondPic("��ʱ�鿴");
		String PicThree=fhomePageService.findSecondPic("����Ȧ");
		List<String> list=new ArrayList<String>();
		list.add(PicOne);
		list.add(PicTwo);
		list.add(PicThree);
		JSONArray array=JSONArray.fromObject(list);
		ResponseUtil.write(res, array);
		return null;		
	}
	
	//��ȡ��ҳ��ҳ������
	@RequestMapping("/findForthPics")
	public String findForthPics(HttpServletResponse res)throws Exception{
		List<FFamnewsCustom> list=fhomePageService.findHPNews();
		JSONArray array=JSONArray.fromObject(list);
		ResponseUtil.write(res, array);
		return null;		
	}
	
	//��ҳ��һҳͼƬ���ϴ�
	@ResponseBody
	@RequestMapping("/addFirstPics")
	public String addFirstPics(@RequestParam MultipartFile file,@RequestParam("Flag") String Flag,HttpServletResponse res)throws Exception{
		//��ȡͼƬ���ļ���
	    String fileName=file.getOriginalFilename();
		//��ȡͼƬ����չ��
		String extensionName=fileName.substring(fileName.lastIndexOf(".")+1);
		//��ͼƬ��=��ȡʱ���+ͼƬ��չ��
		String newFileName=String.valueOf(System.currentTimeMillis())+"."+extensionName;
		
		pics.saveFile(newFileName, file);
		String url=pics.saveFileString(newFileName);
		
		Map<String, String> map=StringUtil.JudgeFlag(Integer.parseInt(Flag));
		
		
		return null;		
	}
}
