package cn.items.mssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dynamic.mssm.DBContextHolder;
import cn.items.mssm.entity.StringUtil;
import cn.items.mssm.service.FuserService;

@Controller
@RequestMapping("/fbackup")
public class FbackupController {

	/** 
	 * @ClassName: FbackupController 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月21日 下午3:27:50 
	 */
	
	@Resource
	private FuserService fuserService;
	
	/*public static Map<String,Object> usermap=new HashMap<>();*/
	
	//获取家族人员外迁情况
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamExo")
	public Map<String, Object> findFamExo(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		Map<String, Object> map=new HashMap<>();
		int allCount=fuserService.findFamCount();	
		int inCount=fuserService.findExCount(StringUtil.checkMap("in","在家族所在地中"));  //统计在家族所在地的人数
		int outConut=allCount-inCount; //统计不在家族所在地的人数
		int outWarCount=fuserService.findExCount(StringUtil.checkMap("in","战争原因"));
		int outPolCount=fuserService.findExCount(StringUtil.checkMap("in","政治原因"));
		int outPerCount=fuserService.findExCount(StringUtil.checkMap("in","个人原因"));
		
		//15世纪因为不同原因不在家族的人数
		Integer outWarCount15=fuserService.findExCount(StringUtil.checkMap("15","战争原因"));
		Integer outPolCount15=fuserService.findExCount(StringUtil.checkMap("15","政治原因"));
		Integer outPerCount15=fuserService.findExCount(StringUtil.checkMap("15","其他原因"));
		
		//16世纪因为不同原因不在家族的人数
		Integer outWarCount16=fuserService.findExCount(StringUtil.checkMap("16","战争原因"));
		Integer outPolCount16=fuserService.findExCount(StringUtil.checkMap("16","政治原因"));
		Integer outPerCount16=fuserService.findExCount(StringUtil.checkMap("16","其他原因"));
		
		//17世纪因为不同原因不在家族的人数
		Integer outWarCount17=fuserService.findExCount(StringUtil.checkMap("17","战争原因"));
		Integer outPolCount17=fuserService.findExCount(StringUtil.checkMap("17","政治原因"));
		Integer outPerCount17=fuserService.findExCount(StringUtil.checkMap("17","其他原因"));
		
		//18世纪因为不同原因不在家族的人数
		Integer outWarCount18=fuserService.findExCount(StringUtil.checkMap("18","战争原因"));
		Integer outPolCount18=fuserService.findExCount(StringUtil.checkMap("18","政治原因"));
		Integer outPerCount18=fuserService.findExCount(StringUtil.checkMap("18","其他原因"));
		
		//19世纪因为不同原因不在家族的人数
		Integer outWarCount19=fuserService.findExCount(StringUtil.checkMap("18","战争原因"));
		Integer outPolCount19=fuserService.findExCount(StringUtil.checkMap("18","政治原因"));
		Integer outPerCount19=fuserService.findExCount(StringUtil.checkMap("18","其他原因"));
		
		//20世纪因为不同原因不在家族的人数
		Integer outWarCount20=fuserService.findExCount(StringUtil.checkMap("19","战争原因"));
		Integer outPolCount20=fuserService.findExCount(StringUtil.checkMap("19","政治原因"));
		Integer outPerCount20=fuserService.findExCount(StringUtil.checkMap("19","其他原因"));
		
		//20世纪因为不同原因不在家族的人数
		Integer outWarCount21=fuserService.findExCount(StringUtil.checkMap("20","战争原因"));
		Integer outPolCount21=fuserService.findExCount(StringUtil.checkMap("20","政治原因"));
		Integer outPerCount21=fuserService.findExCount(StringUtil.checkMap("20","其他原因"));
		
		List<Integer> listWar=new ArrayList<>();
		List<Integer> listPol=new ArrayList<>();
		List<Integer> listPer=new ArrayList<>();
		
		listWar.add(outWarCount15);
		listWar.add(outWarCount16);
		listWar.add(outWarCount17);
		listWar.add(outWarCount18);
		listWar.add(outWarCount19);
		listWar.add(outWarCount20);
		listWar.add(outWarCount21);
		
		listPol.add(outPolCount15);
		listPol.add(outPolCount16);
		listPol.add(outPolCount17);
		listPol.add(outPolCount18);
		listPol.add(outPolCount19);
		listPol.add(outPolCount20);
		listPol.add(outPolCount21);
		
		listPer.add(outPerCount15);
		listPer.add(outPerCount16);
		listPer.add(outPerCount17);
		listPer.add(outPerCount18);
		listPer.add(outPerCount19);
		listPer.add(outPerCount20);
		listPer.add(outPerCount21);
		
		map.put("infam",inCount);
		map.put("outfam",outConut);
		map.put("war", outWarCount);
		map.put("pol", outPolCount);
		map.put("per", outPerCount);
		map.put("warPer",StringUtil.percent(outWarCount,allCount));
		map.put("polPer",StringUtil.percent(outPolCount,allCount));
		map.put("perPer",StringUtil.percent(outPerCount,allCount));
		map.put("warList",listWar);
		map.put("polList",listPol);
		map.put("perList",listPer);
		
		return map;		
	}
	
	//获取家族人员外迁情况
	@ResponseBody
	@RequestMapping("/findFamSex")
	public Map<String, Object> findFamSex(HttpSession session,HttpServletResponse res)throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		Map<String, Object> map=new HashMap<>();
		Map<String, String> m=new HashMap<>();
		m.put("sex", "男");
		int allCount=fuserService.findFamCount();
		int manCount=fuserService.findFamSex(m);
		String manpercent=StringUtil.percent(manCount, allCount);
		String womanpercent=StringUtil.percent(allCount-manCount, allCount);
		String manpercentNo=StringUtil.percentNo(manCount, allCount);
		String womanpercentNo=StringUtil.percentNo(allCount-manCount, allCount);
		
		map.put("manpercent",manpercent);
		map.put("womanpercent",womanpercent);
		map.put("man",Integer.parseInt(manpercentNo));
		map.put("woman",Integer.parseInt(womanpercentNo));
		
		return map;		
	}
	
	//查找家族中不同年龄段的人的数量
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findFamAge")
	public Map<String, Object> findFamAge(HttpSession session,HttpServletResponse res)throws Exception{
		Map<String,Object> usermap=(Map<String, Object>) session.getAttribute("userinfo");
		DBContextHolder.setDBType(usermap.get("dbid").toString());
		Map<String, Object> map=new HashMap<>();
		int allCount=fuserService.findFamCount();
		
		Integer oneAge=fuserService.findFamAge(StringUtil.AgeMap("青"));
		Integer twoAge=fuserService.findFamAge(StringUtil.AgeMap("状"));
		Integer threeAge=fuserService.findFamAge(StringUtil.AgeMap("中"));
		Integer fourAge=fuserService.findFamAge(StringUtil.AgeMap("更"));
		Integer fiveAge=fuserService.findFamAge(StringUtil.AgeMap("老"));
		Integer sixAge=fuserService.findFamAge(StringUtil.AgeMap("死"));
		
		List<Integer> list=new ArrayList<>();
		list.add(oneAge);
		list.add(twoAge);
		list.add(threeAge);
		list.add(fourAge);
		list.add(fiveAge);
		list.add(sixAge);
		
		map.put("one",StringUtil.percent(oneAge.intValue(),allCount));
		map.put("two",StringUtil.percent(twoAge.intValue(),allCount));
		map.put("three",StringUtil.percent(threeAge.intValue(),allCount));
		map.put("four",StringUtil.percent(fourAge.intValue(),allCount));
		map.put("five",StringUtil.percent(fiveAge.intValue(),allCount));
		map.put("six",StringUtil.percent(sixAge.intValue(),allCount));
		map.put("list",list);
		
		return map;		
	}
}
