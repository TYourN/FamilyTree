import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	/** 
	 * @ClassName: ConnectionTest 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年12月3日 下午1:48:55 
	 */	
	public String getConnection() {
		String state=null;
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				state="加载失败";
			}

			String url = "jdbc:mysql://localhost:3306/lujia_0?user=root&password=12345";
			conn = DriverManager.getConnection(url);
			state="连接成功";

		} catch (SQLException ex1) {
			state="连接失败";
		}
		return state;
	}
	
	public static void main(String[] args) {
		ConnectionTest connectionTest=new ConnectionTest();
		String state=connectionTest.getConnection();
		System.out.println(state);
	}
}
