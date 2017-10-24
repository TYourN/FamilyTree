package cn.items.mssm.entity;

import java.util.ArrayList;
import java.util.List;

public class StringChange {

	/** 
	 * @ClassName: StringChange 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月24日 下午1:13:02 
	 */
	
	public static List<String> Change(List list){
		String ip = PropertiesUtil.getProperties("/IP.properties","IP");
		
		List<String> l=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			String strb = list.get(i).toString();
			System.out.println(list.get(i).toString());
			strb = "http://"+ip+":8089"+strb;	
			String m=strb.toString();
			l.add(m);
		}	
		return l;
	}
}
