package cn.items.mssm.entity;

import java.util.ArrayList;
import java.util.List;

public class StringChange {

	/** 
	 * @ClassName: StringChange 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��24�� ����1:13:02 
	 */
	
	public static List<String> Change(List<String> list){
		String ip = PropertiesUtil.getProperties("/PicsStore.properties","SeparateImages");
		
		List<String> l=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			String strb = list.get(i).toString();
			String[] s=strb.split("/");
			String str=s[s.length-1];
			str = "http://"+ip+"/"+str;	
			String m=str.toString();
			l.add(m);
		}	
		return l;
	}
}
