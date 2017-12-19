package cn.items.mssm.entity;

import java.io.File;
import java.io.IOException;

public class DBoperation {

	/** 
	 * @ClassName: DBoperation 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��11��27�� ����8:23:17 
	 */
	//���ݿⱸ��
	public static boolean DBbackup(String hostIP, String userName, String password, String savePath, String fileName, String databaseName){
		File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// ���Ŀ¼������  
            saveFile.mkdirs();// �����ļ���  
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
            if (process.waitFor() == 0) {// 0 ��ʾ�߳�������ֹ��  
                return true;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return false;
	}
	
	//���ݿ⵼��
	public static boolean DBimport(String hostIP, String userName, String password, String importFilePath, String sqlFileName, String databaseName) {  
        File saveFile = new File(importFilePath);  
        if (!saveFile.exists()) {// ���Ŀ¼������  
            saveFile.mkdirs();// �����ļ���  
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
            Process process = Runtime.getRuntime().exec("cmd /c "+stringBuilder.toString());//����Ҫ�С�cmd /c ��  
            if (process.waitFor() == 0) {// 0 ��ʾ�߳�������ֹ��  
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
            System.out.println("���ݿⱸ�ݳɹ�������");  
        } else {  
            System.out.println("���ݿⱸ��ʧ�ܣ�����");  
        }  
	}*/
}
