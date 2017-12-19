package cn.items.mssm.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnect {

	/** 
	 * @ClassName: SqlConnect 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年12月3日 下午2:00:20 
	 */
	
	public static String getConnection(String driverclass,String url,String username,String password) {
		String state=null;
		Connection conn = null;
		try {
			try {
				Class.forName(driverclass);
			} catch (ClassNotFoundException ex) {
				state="加载失败";
			}

			/*String sqlUrl = "jdbc:mysql://localhost:3306/lujia_0?user=root&password=12345";*/
			String sqlUrl=url+"?"+"user="+username+"&"+"password="+password;
			conn = DriverManager.getConnection(sqlUrl);
			state="连接成功";

		} catch (SQLException ex1) {
			state="连接失败";
		}
		return state;
	}
	
	public static void main(String[] args) {
		String state=SqlConnect.getConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/lujia_0","root","12345");
		System.out.println(state);
	}
}
