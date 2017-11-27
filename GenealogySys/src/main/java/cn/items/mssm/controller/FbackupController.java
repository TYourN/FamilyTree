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
	 * @date: 2017��11��21�� ����3:27:50 
	 */
	
	@Resource
	private FuserService fuserService;
	
	//��ȡ������Ա��Ǩ���
	@ResponseBody
	@RequestMapping("/findFamExo")
	public Map<String, Object> findFamExo(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		Map<String, Object> map=new HashMap<>();
		int allCount=fuserService.findFamCount();	
		int inCount=fuserService.findExCount(StringUtil.checkMap("in","�ڼ������ڵ���"));  //ͳ���ڼ������ڵص�����
		int outConut=allCount-inCount; //ͳ�Ʋ��ڼ������ڵص�����
		int outWarCount=fuserService.findExCount(StringUtil.checkMap("in","ս��ԭ��"));
		int outPolCount=fuserService.findExCount(StringUtil.checkMap("in","����ԭ��"));
		int outPerCount=fuserService.findExCount(StringUtil.checkMap("in","����ԭ��"));
		
		//15������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount15=fuserService.findExCount(StringUtil.checkMap("15","ս��ԭ��"));
		Integer outPolCount15=fuserService.findExCount(StringUtil.checkMap("15","����ԭ��"));
		Integer outPerCount15=fuserService.findExCount(StringUtil.checkMap("15","����ԭ��"));
		
		//16������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount16=fuserService.findExCount(StringUtil.checkMap("16","ս��ԭ��"));
		Integer outPolCount16=fuserService.findExCount(StringUtil.checkMap("16","����ԭ��"));
		Integer outPerCount16=fuserService.findExCount(StringUtil.checkMap("16","����ԭ��"));
		
		//17������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount17=fuserService.findExCount(StringUtil.checkMap("17","ս��ԭ��"));
		Integer outPolCount17=fuserService.findExCount(StringUtil.checkMap("17","����ԭ��"));
		Integer outPerCount17=fuserService.findExCount(StringUtil.checkMap("17","����ԭ��"));
		
		//18������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount18=fuserService.findExCount(StringUtil.checkMap("18","ս��ԭ��"));
		Integer outPolCount18=fuserService.findExCount(StringUtil.checkMap("18","����ԭ��"));
		Integer outPerCount18=fuserService.findExCount(StringUtil.checkMap("18","����ԭ��"));
		
		//19������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount19=fuserService.findExCount(StringUtil.checkMap("18","ս��ԭ��"));
		Integer outPolCount19=fuserService.findExCount(StringUtil.checkMap("18","����ԭ��"));
		Integer outPerCount19=fuserService.findExCount(StringUtil.checkMap("18","����ԭ��"));
		
		//20������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount20=fuserService.findExCount(StringUtil.checkMap("19","ս��ԭ��"));
		Integer outPolCount20=fuserService.findExCount(StringUtil.checkMap("19","����ԭ��"));
		Integer outPerCount20=fuserService.findExCount(StringUtil.checkMap("19","����ԭ��"));
		
		//20������Ϊ��ͬԭ���ڼ��������
		Integer outWarCount21=fuserService.findExCount(StringUtil.checkMap("20","ս��ԭ��"));
		Integer outPolCount21=fuserService.findExCount(StringUtil.checkMap("20","����ԭ��"));
		Integer outPerCount21=fuserService.findExCount(StringUtil.checkMap("20","����ԭ��"));
		
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
	
	//��ȡ������Ա��Ǩ���
	@ResponseBody
	@RequestMapping("/findFamSex")
	public Map<String, Object> findFamSex(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		Map<String, Object> map=new HashMap<>();
		Map<String, String> m=new HashMap<>();
		m.put("sex", "��");
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
	
	//���Ҽ����в�ͬ����ε��˵�����
	@ResponseBody
	@RequestMapping("/findFamAge")
	public Map<String, Object> findFamAge(HttpSession session,HttpServletResponse res)throws Exception{
		DBContextHolder.setDBType("1");
		Map<String, Object> map=new HashMap<>();
		int allCount=fuserService.findFamCount();
		
		Integer oneAge=fuserService.findFamAge(StringUtil.AgeMap("��"));
		Integer twoAge=fuserService.findFamAge(StringUtil.AgeMap("״"));
		Integer threeAge=fuserService.findFamAge(StringUtil.AgeMap("��"));
		Integer fourAge=fuserService.findFamAge(StringUtil.AgeMap("��"));
		Integer fiveAge=fuserService.findFamAge(StringUtil.AgeMap("��"));
		Integer sixAge=fuserService.findFamAge(StringUtil.AgeMap("��"));
		
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
