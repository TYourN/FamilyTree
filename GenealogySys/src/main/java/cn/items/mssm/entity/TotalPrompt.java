package cn.items.mssm.entity;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class TotalPrompt {

	/** 
	 * @ClassName: TotalPrompt 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月17日 下午2:25:39 
	 */
	
	//新闻页的页脚提示
	public JSONArray newsObject(){
		List<Location> list=new ArrayList<>();
		Location l1=new Location("首页","Homepage.jsp");
		Location l2=new Location("新闻中心","news.jsp");
		list.add(l1);
		list.add(l2);
		JSONArray array=JSONArray.fromObject(list);
		return array;		
	}
	
	//百家姓的页脚提示
	public JSONArray snamesObject(){
		List<Location> list=new ArrayList<>();
		Location l1=new Location("首页","Homepage.jsp");
		Location l2=new Location("姓氏群英","sname.jsp");
		Location l3=new Location("百家姓","snames.jsp");
		list.add(l1);
		list.add(l2);
		list.add(l3);
		JSONArray array=JSONArray.fromObject(list);
		return array;		
	}
	
	//
}
