package cn.items.mssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.service.FhomePageService;

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
	@ResponseBody
	@RequestMapping("/findFirstPics")
	public List<String> findFirstPics(HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		List<String> list=fhomePageService.findFirstPics();
		return list;		
	}
	
	//��ȡ��ҳ�ڶ�ҳ��ͼƬ
	/*@ResponseBody
	@RequestMapping("/findSecondPics")
	public List<Map<String,Object>> findSecondPics(HttpServletResponse res)throws Exception{
		String PicOne=fhomePageService.findSecondPic("���߱���");
		String PicTwo=fhomePageService.findSecondPic("��ʱ�鿴");
		String PicThree=fhomePageService.findSecondPic("����Ȧ");
		String PicOneChild=fhomePageService.findSecondPic("���߱�����");
		String PicTwoChild=fhomePageService.findSecondPic("��ʱ�鿴��");
		String PicThreeChild=fhomePageService.findSecondPic("����Ȧ��");
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> m=new HashMap<>();
		m.put("p","���߱���");
		m.put("img",PicOne);
		map.put("title",m);
		m.put("p","�򵥲��������߱��ޡ�");
		m.put("img",PicOneChild);
		map.put("content",);
		
		return list;		
	}*/
	
	//��ȡ��ҳ��ҳ������
	@ResponseBody
	@RequestMapping("/findForthPics")
	public List<FFamnewsCustom> findForthPics(HttpServletResponse res)throws Exception{
		List<FFamnewsCustom> list=fhomePageService.findHPNews();
		return list;		
	}
	
	//��ҳ��һҳͼƬ���ϴ�
	@ResponseBody
	@RequestMapping("/addFirstPics")
	public Map<String,String> addFirstPics(@RequestParam String flag,@RequestParam MultipartFile img,HttpServletResponse res)throws Exception{
		//��ͼƬ��=��ȡʱ���+ͼƬ��չ��
		DBContextHolder.setDBType("0");
		
		if(flag.equals("չʾ")){
			fhomePageService.deletePicsByFLag(flag);
		}
		
		String newFileName=pics.picsProcess(img); 	

		String fileUrl=pics.saveFile(newFileName,img);
		PicsSave.uploadFile(fileUrl, newFileName);
		
		String url=pics.saveFileString(newFileName);
		
		Map<String,String> map=new HashMap<>();
		fhomePageService.addFSPics("",url,"",flag);
		
		map.put("img",url);
		return map;		
	}
	
	@ResponseBody
	@RequestMapping("/deleteMianPics")
	public int deleteMianPics(@RequestParam int picid,HttpServletResponse res)throws Exception{
		int flag=fhomePageService.deleteMianPics(picid);
		return flag;		
	}
	
	//��ȡ����ͼƬ��Դ�������
	@ResponseBody
	@RequestMapping("/findPicsByAu")
	public List<Map<String,Object>> findPicsByAu(HttpServletResponse res) throws Exception{
		DBContextHolder.setDBType("0");
		List<Map<String,Object>> picslist=new ArrayList<>();
		
		Map<String,Object> map1=new HashMap<>();
		Map<String,String> m1=new HashMap<>();
		m1.put("flag","��һҳ");
		List<String> list1=fhomePageService.findPicsByAu(m1);
		map1.put("id","homebg");
		map1.put("item",list1);
		picslist.add(map1);
		
		Map<String,Object> map2=new HashMap<>();
		Map<String,String> m2=new HashMap<>();
		m2.put("flag","չʾ");
		List<String> list2=fhomePageService.findPicsByAu(m2);
		map2.put("id","personbg");
		map2.put("item",list2);
		picslist.add(map2);
		
		Map<String,Object> map3=new HashMap<>();
		Map<String,String> m3=new HashMap<>();
		m3.put("flag","�ֻ�");
		List<String> list3=fhomePageService.findPicsByAu(m3);
		map3.put("id","phonebg");
		map3.put("item",list3);
		picslist.add(map3);
		
		return picslist;		
	}
}
