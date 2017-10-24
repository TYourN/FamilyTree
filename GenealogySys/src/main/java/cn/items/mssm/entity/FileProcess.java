package cn.items.mssm.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileProcess {

	/**
	 * @ClassName: FileProcess
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月24日 下午1:55:16
	 */

	// 删除一个文件夹中所有的文件
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
				flag = true;
			}
		}
		return flag;
	}

	// 复制文件
	public void CopyFile(String oldpath, String newpath) {
		File file = new File(oldpath);
		File file1 = new File(newpath);
		if (!file.exists()) {
			
		} else {
			
		}
		byte[] b = new byte[(int) file.length()];
		if (file.isFile()) {
			try {
				FileInputStream is = new FileInputStream(file);
				FileOutputStream ps = new FileOutputStream(file1);
				is.read(b);
				ps.write(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (file.isDirectory()) {
			if (!file.exists())
				file.mkdir();
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				this.CopyFile(oldpath + "/" + list[i], newpath + "/" + list[i]);
			}
		}
	}
}
