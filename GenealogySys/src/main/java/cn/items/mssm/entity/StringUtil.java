package cn.items.mssm.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	/**
     * 判断是否是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 判断是否不是�?
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 格式化模糊查�?
     * @param str
     * @return
     */
    public static String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }else{
            return null;
        }
    }
    /**
     * InputStream转String
     * @param str
     * @return
     */
    public static String Inputstr2Str_Reader(InputStream is){
    	String str=null;
    	BufferedReader reader=new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb=new StringBuilder();
    	try{
    		while((str=reader.readLine())!=null){
    			sb.append(str);
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}finally{
    		try{
    			is.close();
    		}catch(IOException e){
    			e.printStackTrace();
    		}
    	}
		return sb.toString();    	
    }
    
    //String转Date
    public static Date StringTransfor(String time){
    	Date date=new Date();
    	try  
    	{  
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	    date = sdf.parse(time);  
    	}  
    	catch (ParseException e)  
    	{  
    	    System.out.println(e.getMessage());  
    	}  
		return date;   	
    }
    
    //获取当前时间
    public static String getDate(){
    	Date date=new Date();
    	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time=format.format(date);
		return time;  	
    }
    
    //时间格式化
    public static String changeTime(Date d){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(d);
		return s;	
    }
    
    //判断图片属于哪一页中的哪一类的
    public static Map<String, String> JudgeFlag(int flag){
    	Map<String, String> map=new HashMap<>();
    	if(flag==1){
    		map.put("Memo", "");
    		map.put("Flag", "第一页");
    	}else if(flag==2){
    		map.put("Memo","在线编修");
    		map.put("Flag", "第二页");
    	}else if(flag==3){
    		map.put("Memo","随时查看");
    		map.put("Flag", "第二页");
    	}else if(flag==4){
    		map.put("Memo","家族圈");
    		map.put("Flag", "第二页");
    	}
		return map; 	
    }
    
    //通过分隔符来截取String
    public static String CutString(String src){
    	String[] sArray1=src.split("/");
		return "/"+sArray1[sArray1.length-2]+"/"+sArray1[sArray1.length-1];    	
    }
    
    // "/"分隔符截取
    public static String Cut(String src){
    	String[] sArray1=src.split("/");
		return sArray1[sArray1.length-1];    	
    }
    
    // ","分隔符截取
    public static List<String> CutComma(String url){
    	String[] sArray=url.split(",");
    	List<String> list=new ArrayList<>();
    	for(String i:sArray){
    		list.add(i);
    	}
		return list;  	
    }
    
    // ","分隔符截取,并拼接html
    public static String SpliceUrl(String url){
    	String s="<p>";
    	String[] sArray=url.split(",");
    	for(String i:sArray){
    		s=s+"<img src="+"\""+i+"\""+"/"+">";
    	}
    	String surl=s+"</p>";
		return surl;  	
    }
    
    //拼接html
    public static String SplicePRUrl(String content,String url){
    	String s="<img src="+"\""+url+"\""+"/"+">";
    	content=content+s;
		return content;   	
    }
    
    //生成对应条件的map
    public static Map<String, String> checkMap(String Check,String Conditions){
    	Map<String, String> map=new HashMap<>();
		if(Check=="in"){
			map.put("exoducs", Conditions);
		}else if(Check=="15"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1400");
			map.put("birthend", "1499");
		}else if(Check=="16"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1500");
			map.put("birthend", "1599");
		}else if(Check=="17"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1600");
			map.put("birthend", "1699");
		}else if(Check=="18"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1700");
			map.put("birthend", "1799");
		}else if(Check=="19"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1800");
			map.put("birthend", "1899");
		}else if(Check=="20"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "1900");
			map.put("birthend", "1999");
		}else if(Check=="21"){
			map.put("exoducs", Conditions);
			map.put("birthstart", "2000");
			map.put("birthend", "2099");
		}
    	return map;   	
    }
    
    //计算两个数的百分比
    public static String percent(int num1,int num2){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setMaximumFractionDigits(2);
    	String result = numberFormat.format((float) num1 / (float) num2 * 100);  
		return result+"%";   	
    }
    
  //计算两个数的百分比
    public static String percentNo(int num1,int num2){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setMaximumFractionDigits(2);
    	String result = numberFormat.format((float) num1 / (float) num2 * 100);  
		return result+""; 
    }
    
    //查询年龄段时生成对应的Map
    public static Map<String, Integer> AgeMap(String type){
    	Map<String, Integer> map=new HashMap<>();
    	if(type=="青"){
    		map.put("ageone",0);
    		map.put("agetwo",20);
    	}else if(type=="状"){
    		map.put("ageone",21);
    		map.put("agetwo",35);
    	}else if(type=="中"){
    		map.put("ageone",36);
    		map.put("agetwo",50);
    	}else if(type=="更"){
    		map.put("ageone",51);
    		map.put("agetwo",65);
    	}else if(type=="老"){
    		map.put("ageone",66);
    		map.put("agetwo",100);
    	}else if(type=="死"){
    		map.put("ageone",101);
    	}
		return map;    	
    }
    
}
