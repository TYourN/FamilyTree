package cn.items.mssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.PicsSave;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.entity.TotalPrompt;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FNewsCustom;
import cn.items.mssm.service.FnewsService;

@Controller
@RequestMapping("/fnews")
public class FnewsController {

	/** 
	 * @ClassName: FnewsController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��17�� ����2:11:49 
	 */
	@Resource
	private FnewsService fnewsService;
	
	public static TotalPrompt totalPrompt=new TotalPrompt();
	
	public static PicsSave pics=new PicsSave();
	
	private static String NewsServerId;
	
	//�ҳ����е���ҳ������
	@ResponseBody
	@RequestMapping("/findNewsAll")
	public Map<String, Object> findNewsAll(@RequestParam int pages,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		PageHelper.startPage(pages, 10);
		Map<String, Object> map=new HashMap<>();
		List<FFamnewsCustom> list=fnewsService.findAllNews();
		PageInfo<FFamnewsCustom> pageInfo=new PageInfo<FFamnewsCustom>(list);
		long total=pageInfo.getTotal();
		map.put("pages", total);
		map.put("locate",totalPrompt.newsObject());
		map.put("Content",list);
		return map;		
	}
	
	//����Id�������ŵľ�������
	@ResponseBody
	@RequestMapping("/findNewDety")
	public FFamnewsCustom findNewDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		FFamnewsCustom famnewsCustom=fnewsService.findNewDety(newsid);
		famnewsCustom.setLocate(totalPrompt.newsObject());
		return famnewsCustom;		
	}
	
	//�ϴ�չʾ��ҳ������
	@ResponseBody
	@RequestMapping("/addMainNews")
	public int addMainNews(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String memo,@RequestParam String content,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("0");
		//��ͼƬ��=��ȡʱ���+ͼƬ��չ��
		String newFileName=pics.picsProcess(file); 	
		String fileUrl=pics.saveFile(newFileName, file);
		PicsSave.uploadFile(fileUrl, newFileName);
		String url=pics.saveFileString(newFileName);
		FFamnewsCustom famnewsCustom=new FFamnewsCustom();
		famnewsCustom.setTitle(title);
		famnewsCustom.setContent(PicsSave.uploadContent(content));
		famnewsCustom.setOpertime(StringUtil.StringTransfor(StringUtil.getDate()));
		famnewsCustom.setMemo(memo);
		famnewsCustom.setUrl(url);
		
		int flag=fnewsService.addMainNews(famnewsCustom);
		
		return flag;		
	}
	
	//ɾ����ҳ��ҳ��һ������
    @ResponseBody
    @RequestMapping("/deleteMainNews")
    public int deleteMainNews(@RequestParam int newsid,HttpServletResponse res)throws Exception{
    	DBContextHolder.setDBType("0");
    	int flag=fnewsService.deleteMainNews(newsid);
		return flag;   	
    }
    
	//��ѯ�����еļ�������
	@ResponseBody
	@RequestMapping("/findFamNews")
	public List<FNewsCustom> findFamNews(HttpServletResponse res)throws Exception{
		List<FNewsCustom> list=fnewsService.findFamNews();
		return list;		
	}
	
	//����Id���ҳ��������ŵľ�����Ϣ
	@ResponseBody
	@RequestMapping("/findFamNewsDety")
	public FNewsCustom findFamNewsDety(@RequestParam int newsid,HttpServletResponse res)throws Exception{
		FNewsCustom fNewsCustom=fnewsService.findFamNewsDety(newsid);
		return fNewsCustom;		
	}
    
    //�ϴ���Ӧ�����һ������
    @ResponseBody
    @RequestMapping("/addFamNews")
    public int addFamNews(@RequestParam MultipartFile file[],@RequestParam String title,@RequestPart String memo,@RequestParam String content,HttpSession session,HttpServletResponse res)throws Exception{
    	@SuppressWarnings("unchecked")
		Map<String,String> map=(Map<String, String>) session.getAttribute("user");
		NewsServerId=map.get("serverId");
		DBContextHolder.setDBType(NewsServerId);
		
		int operid=Integer.parseInt(map.get("userid"));
		
		String url=null;
		for(MultipartFile f:file){
			String newFileName=pics.picsProcess(f); 	

			String fileUrl=pics.saveFile(newFileName, f);
			PicsSave.uploadFile(fileUrl, newFileName);
			
			String u=pics.saveFileString(newFileName);
			
			if(url==null||url==""){
				url=u;
			}else{
				url=url+","+u;
			}
		}
		
		String Content=PicsSave.uploadContent(content);
		
		int flag=fnewsService.addFamNews(title, Content, operid, url, memo);
		return flag;   	
    }
    
    //ɾ��һ����Ӧ�����������Ϣ
    @ResponseBody
    @RequestMapping("/deleteFamNews")
    public int deleteFamNews(@RequestParam int newsid,HttpServletResponse res)throws Exception{
    	int flag=fnewsService.deleteFamNews(newsid);
		return flag;  	
    }
}
