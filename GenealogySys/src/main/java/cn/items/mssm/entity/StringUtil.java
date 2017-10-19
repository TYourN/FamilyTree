package cn.items.mssm.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
