package cn.items.mssm.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;


public class PicsSave {

	/** 
	 * @ClassName: SaveFile 
	 * @Description: TODO
	 * @author: Administrator
	 * @throws IOException 
	 * @date: 2017��10��19�� ����3:39:09 
	 */
	
	//��ͼƬ�����ƽ��д���
	public String picsProcess(MultipartFile file){
		//��ȡͼƬ���ļ���
	    String fileName=file.getOriginalFilename();
		//��ȡͼƬ����չ��
		String extensionName=fileName.substring(fileName.lastIndexOf(".")+1);
		//��ͼƬ��=��ȡʱ���+ͼƬ��չ��
		String newFileName=String.valueOf(System.currentTimeMillis())+"."+extensionName; 
		
		return newFileName;
	}
	
	//��ȡͼƬ�ľ��Ա���·��
	public String saveFile(String newFileName, MultipartFile filedata) throws IOException{
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
		return picDir+"/"+newFileName;
	}
	
	//�ϴ�����ͼƬ�ļ�����ţ����ȥ
	public static void uploadFile(String filePath,String fileName){
		String accessKey=PropertiesUtil.getProperties("/PicsStore.properties","AccessKey");
		String secretKey=PropertiesUtil.getProperties("/PicsStore.properties","SecretKey");
		String backname=PropertiesUtil.getProperties("/PicsStore.properties","BUCKETNAME");
		Mac mac=new Mac(accessKey, secretKey);
		PutPolicy putPolicy=new PutPolicy(backname);
		String uptoken = null;  
		try {
			uptoken=putPolicy.token(mac);
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PutExtra putExtra=new PutExtra();
		PutRet putRet=IoApi.putFile(uptoken, fileName, filePath, putExtra);
	}
	
	//ƴ��ͼƬ����Ա���·��
	public String saveFileString(String newFileName){
		String IpDir=PropertiesUtil.getProperties("/PicsStore.properties","SeparateImages");
		String RelaPath="http://"+IpDir+"/"+newFileName;		
		return RelaPath;		
	}
	
	//��Ueditor�е�ͼƬ�����ϴ�����
	public static String saveContent(String content){
		FileProcess fileProcess=new FileProcess();
		String Content=ImgUtil.getImageSrc(content);
		FileProcess.delAllFile(PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		fileProcess.CopyFile(PropertiesUtil.getProperties("/PicsStore.properties","oldPicUrl"), PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		return Content;		
	}
	
	//��Ueditor�ϴ������е�ͼƬ������ȡ����
	public static String uploadContent(String content){
		String Content=ImgUtil.getImageSrc(content);
		List<String> list=ImgUtil.getImgName(content);
		if(list.size()==0){
			return Content;
		}else{
			String filePath=PropertiesUtil.getProperties("/PicsStore.properties", "oldPicUrl");
			for(String path:list){
				String name=StringUtil.Cut(path);
				PicsSave.uploadFile(filePath+"/"+name,name);
			}
			return Content;
		}				
	}
}
