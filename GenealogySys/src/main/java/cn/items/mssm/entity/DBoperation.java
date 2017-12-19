package cn.items.mssm.entity;

import java.io.File;
import java.io.IOException;

public class DBoperation {

	/** 
	 * @ClassName: DBoperation 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月27日 下午8:23:17 
	 */
	//数据库备份
	public static boolean DBbackup(String hostIP, String userName, String password, String savePath, String fileName, String databaseName){
		File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if (!savePath.endsWith(File.separator)) {  
            savePath = savePath + File.separator;  
        } 
        StringBuilder stringBuilder = new StringBuilder();  
        stringBuilder.append("mysqldump").append(" --opt").append(" -h").append(hostIP);  
        stringBuilder.append(" --user=").append(userName) .append(" --password=").append(password).append(" --lock-all-tables=true");  
        stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ").append(databaseName);  
        try {  
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());  
            if (process.waitFor() == 0) {// 0 表示线程正常终止。  
                return true;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return false;
	}
	
	//数据库导入
	public static boolean DBimport(String hostIP, String userName, String password, String importFilePath, String sqlFileName, String databaseName) {  
        File saveFile = new File(importFilePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if (!importFilePath.endsWith(File.separator)) {  
            importFilePath = importFilePath + File.separator;  
        }         
  
        StringBuilder stringBuilder=new StringBuilder();  
        stringBuilder.append("mysql").append(" -h").append(hostIP);  
        stringBuilder.append(" -u").append(userName).append(" -p").append(password);  
        stringBuilder.append(" ").append(databaseName);  
        stringBuilder.append(" <").append(importFilePath).append(sqlFileName);  
        try {  
            Process process = Runtime.getRuntime().exec("cmd /c "+stringBuilder.toString());//必须要有“cmd /c ”  
            if (process.waitFor() == 0) {// 0 表示线程正常终止。  
                return true;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }
	
	/*public static void main(String[] args) {
		if (DBbackup("localhost", "root", "12345", "E:/DBbackup", "lujia_0.sql", "lujia_0")) {  
            System.out.println("数据库备份成功！！！");  
        } else {  
            System.out.println("数据库备份失败！！！");  
        }  
	}*/
}
