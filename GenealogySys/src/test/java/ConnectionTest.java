import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	/** 
	 * @ClassName: ConnectionTest 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��12��3�� ����1:48:55 
	 */	
	public String getConnection() {
		String state=null;
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				state="����ʧ��";
			}

			String url = "jdbc:mysql://localhost:3306/lujia_0?user=root&password=12345";
			conn = DriverManager.getConnection(url);
			state="���ӳɹ�";

		} catch (SQLException ex1) {
			state="����ʧ��";
		}
		return state;
	}
	
	public static void main(String[] args) {
		ConnectionTest connectionTest=new ConnectionTest();
		String state=connectionTest.getConnection();
		System.out.println(state);
	}
}
