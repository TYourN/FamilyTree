/**
 * 
 */
package cn.dynamic.mssm;

/**
 * @author Administrator
 *
 */
public class DBContextHolder {

	/** 
	 * @ClassName: DBContextHolder 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017��10��10�� ����2:23:07 
	 */
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }
    public static String getDBType() {
        return (String) contextHolder.get();
    }
    public static void clearDBType() {
        contextHolder.remove();
    } 
}
