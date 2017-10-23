package cn.items.mssm.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;


public class PicsSave {

	/** 
	 * @ClassName: SaveFile 
	 * @Description: TODO
	 * @author: Administrator
	 * @throws IOException 
	 * @date: 2017年10月19日 下午3:39:09 
	 */
	
	//读取图片的绝对保存路径
	public void saveFile(String newFileName, MultipartFile filedata) throws IOException{
		String picDir=PropertiesUtil.getProperties("/PicsStore.properties","savePicUrl");		
		String saveFilePath=picDir;
		File file=new File(saveFilePath);
		if(!file.exists()){
			file.mkdirs();
		}
		try {
			FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
			        + newFileName);
			out.write(filedata.getBytes());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//拼接图片的相对保存路径
	public String saveFileString(String newFileName){
		String IpDir=PropertiesUtil.getProperties("/IP.properties","IP");
		String RelaPath="http://"+IpDir+":8089/"+newFileName;		
		return RelaPath;		
	}
}
