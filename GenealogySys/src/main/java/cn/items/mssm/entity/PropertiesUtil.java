package cn.items.mssm.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mchange.v2.util.PropertiesUtils;

public class PropertiesUtil {

	/** 
	 * @ClassName: PropertiesUtil 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��20�� ����9:33:15 
	 */
	
	//��ȡProperties�ļ�
	public static String getProperties(String url,String key){
		Properties properties=new Properties();
		InputStream in=PropertiesUtils.class.getResourceAsStream(url);
		try {
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String PicUrl=properties.getProperty(key);
		return PicUrl;		
	}
}
