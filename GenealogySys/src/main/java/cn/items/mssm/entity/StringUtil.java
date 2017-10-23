package cn.items.mssm.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
}
