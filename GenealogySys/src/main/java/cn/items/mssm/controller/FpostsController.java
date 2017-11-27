package cn.items.mssm.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.poCustom.FFamnewsCustom;
import cn.items.mssm.poCustom.FPostsCustom;
import cn.items.mssm.service.FostsService;
import cn.items.mssm.service.FrepliesService;

@Controller
@RequestMapping("/fposts")
public class FpostsController {

	/** 
	 * @ClassName: FpostsController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月15日 下午1:51:55 
	 */
	
	@Resource
	private FostsService fostsService;
	
	@Resource
	private FrepliesService frepliesService;
	
	//查找出最新更新的几条家族公告信息
	@ResponseBody
	@RequestMapping("/findReportsTop")
	public List<FPostsCustom> findReportsTop(/*HttpSession session,*/HttpServletResponse res)throws Exception{
		/*Map<String,String> map=(Map<String, String>) session.getAttribute("user");*/
		DBContextHolder.setDBType("1");
		List<FPostsCustom> list=fostsService.findReportsTop();
		return list;		
	}
	
	//查找出BBS页所需的统计数据
	@ResponseBody
	@RequestMapping("/findAllCount")
	public Map<String, Object> findAllCount(/*HttpSession session,*/HttpServletResponse res)throws Exception{
		/*Map<String,String> map=(Map<String, String>) session.getAttribute("user");*/
		DBContextHolder.setDBType("1");
		Map<String, Object> map=new HashMap<>();
		map.put("Totie",fostsService.findTotie());
		map.put("Yestie",fostsService.findYestie());
		map.put("Alltie", fostsService.findAlltie());
		map.put("Allreports", fostsService.findAllreports());
		map.put("Allreplies", frepliesService.findAllreplies());
		map.put("Latesttie", fostsService.findLatesttie());
		map.put("LatestReport", fostsService.findLatestReport());
		return map;	
	}
	
	//查找出所有的水贴相关信息
	@ResponseBody
	@RequestMapping("/findAllInfo")
	public Map<String, Object> findAlltieInfo(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam int flag,HttpServletResponse res)throws Exception{		
		/*Map<String,String> map=(Map<String, String>) session.getAttribute("user");*/
		DBContextHolder.setDBType("1");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FPostsCustom> list=fostsService.findAlltieInfo();
		PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setCtimeString(StringUtil.changeTime(list.get(i).getCreatetime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	//查找出所有的水贴相关信息
	@ResponseBody
	@RequestMapping("/findAllReportsInfo")
	public Map<String, Object> findAllReportsInfo(HttpSession session,@RequestParam int pageNumber,@RequestParam int pageSize,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		PageHelper.startPage(pageNumber, pageSize);
		Map<String, Object> map=new HashMap<>();
		List<FPostsCustom> list=fostsService.findAllReportsInfo();
		PageInfo<FPostsCustom> pageInfo=new PageInfo<FPostsCustom>(list);
		long total=pageInfo.getTotal();
		for(int i=0;i<list.size();i++){
			list.get(i).setCtimeString(StringUtil.changeTime(list.get(i).getCreatetime()));
		}
		map.put("page",pageNumber);
		map.put("rows",list);
		map.put("total", total);
		return map;	
	}
	
	//查找出对应id的公告或水贴的信息
	@ResponseBody
	@RequestMapping("/findDetialById")
	public FPostsCustom findDetialById(@RequestParam int postid,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		FPostsCustom fPostsCustom=fostsService.findDetialById(postid);
		return fPostsCustom;		
	}
	
	//删除一条对应的水贴或者公告
	@ResponseBody
	@RequestMapping("/deleteDetialById")
	public int deleteDetialById(@RequestParam int postid,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		int flag=fostsService.deleteDetialById(postid);
		return flag;		
	}
	
	//更新对应id的公告内容
	@ResponseBody
	@RequestMapping("/updateRepById")
	public int updateRepById(@RequestBody Map<String, String> map,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		FPostsCustom fPostsCustom=new FPostsCustom();
		fPostsCustom.setTitle(map.get("title"));
		fPostsCustom.setContent(map.get("content"));
		fPostsCustom.setPostid(Integer.parseInt(map.get("postid")));
		int flag=fostsService.updateRepById(fPostsCustom);
		return flag;		
	}
	
	//发布公告
	@ResponseBody
	@RequestMapping("/addPosts")
	public int addPosts(@RequestBody Map<String, String> map,HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		FPostsCustom fPostsCustom=new FPostsCustom();
		fPostsCustom.setTitle(map.get("title"));
		fPostsCustom.setContent(map.get("content"));
		fPostsCustom.setPosterid(1);
		fPostsCustom.setCreatetime(StringUtil.StringTransfor(StringUtil.getDate()));
		fPostsCustom.setType("公告");
		int flag=fostsService.addPosts(fPostsCustom);
		return flag;		
	}
}
