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
	 * @date: 2017年10月19日 下午3:39:09 
	 */
	
	//对图片的名称进行处理
	public String picsProcess(MultipartFile file){
		//获取图片的文件名
	    String fileName=file.getOriginalFilename();
		//获取图片的扩展名
		String extensionName=fileName.substring(fileName.lastIndexOf(".")+1);
		//新图片名=获取时间戳+图片扩展名
		String newFileName=String.valueOf(System.currentTimeMillis())+"."+extensionName; 
		
		return newFileName;
	}
	
	//读取图片的绝对保存路径
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
	
	//上传本地图片文件到七牛云上去
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
	
	//拼接图片的相对保存路径
	public String saveFileString(String newFileName){
		String IpDir=PropertiesUtil.getProperties("/PicsStore.properties","SeparateImages");
		String RelaPath="http://"+IpDir+"/"+newFileName;		
		return RelaPath;		
	}
	
	//将Ueditor中的图片进行上传保存
	public static String saveContent(String content){
		FileProcess fileProcess=new FileProcess();
		String Content=ImgUtil.getImageSrc(content);
		FileProcess.delAllFile(PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		fileProcess.CopyFile(PropertiesUtil.getProperties("/PicsStore.properties","oldPicUrl"), PropertiesUtil.getProperties("/PicsStore.properties","newPicUrl"));
		return Content;		
	}
	
	//将Ueditor上传内容中的图片内容提取出来
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
