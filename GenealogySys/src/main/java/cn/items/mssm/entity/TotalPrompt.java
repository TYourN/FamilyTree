package cn.items.mssm.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TotalPrompt {

	/** 
	 * @ClassName: TotalPrompt 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��17�� ����2:25:39 
	 */
	
	//����ҳ��ҳ����ʾ
	public JSONArray newsObject(){
		List<Location> list=new ArrayList<>();
		Location l1=new Location("��ҳ","Homepage.jsp");
		Location l2=new Location("��������","news.jsp");
		list.add(l1);
		list.add(l2);
		JSONArray array=JSONArray.fromObject(list);
		return array;		
	}
	
	//�ټ��յ�ҳ����ʾ
	public JSONArray snamesObject(){
		List<Location> list=new ArrayList<>();
		Location l1=new Location("��ҳ","Homepage.jsp");
		Location l2=new Location("����ȺӢ","sname.jsp");
		Location l3=new Location("�ټ���","snames.jsp");
		list.add(l1);
		list.add(l2);
		list.add(l3);
		JSONArray array=JSONArray.fromObject(list);
		return array;		
	}
	
	//
}
