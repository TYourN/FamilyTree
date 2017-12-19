/**
 * 
 */
package cn.dynamic.mssm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{	

	private Logger log = Logger.getLogger(this.getClass());
    private Map<Object, Object> _targetDataSources; 
	
	/** 
	 * @ClassName: DynamicDataSource 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月10日 下午2:23:58 
	 */
	
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		
		String dataSourceName = DBContextHolder.getDBType(); 
		if (dataSourceName == null) {
            dataSourceName = "dataSourceOne";
        } else {
            this.selectDataSource(Integer.valueOf(dataSourceName));
            if (dataSourceName.equals("0"))
                dataSourceName = "dataSourceOne";
        }
        log.debug("--------> use datasourceOne " + dataSourceName);
        return dataSourceName; 
	}
	
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this._targetDataSources = targetDataSources;
        super.setTargetDataSources(this._targetDataSources);
        afterPropertiesSet();
    } 
	
	public void addTargetDataSource(String key, BasicDataSource dataSource) {
        this._targetDataSources.put(key, dataSource);
        this.setTargetDataSources(this._targetDataSources);
    } 
	
	public BasicDataSource createDataSource(String driverClassName, String url,
            String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestWhileIdle(false);
        return dataSource;
    } 
	
	public void selectDataSource(Integer serverId) {
        Object sid = DBContextHolder.getDBType();
        if ("0".equals(serverId + "")) {
            DBContextHolder.setDBType("0");
            return;
        }
        Object obj = this._targetDataSources.get(serverId);
        if (obj != null && sid.equals(serverId + "")) {
            return;
        } else {
            BasicDataSource dataSource = this.getDataSource(serverId);
            if (null != dataSource)
                this.setDataSource(serverId, dataSource);
        }
    } 
	
	public BasicDataSource getDataSource(Integer serverId) {
        this.selectDataSource(0);
        this.determineCurrentLookupKey();
        Connection conn = null;
        HashMap<String, Object> map = null;
        try {
            conn = this.getConnection();
            PreparedStatement ps = conn
                    .prepareStatement("SELECT * FROM f_database WHERE DatabaseId = ?");
            ps.setInt(1, serverId);
            ResultSet rs = ps.executeQuery();
            map = new HashMap<String, Object>();
            if (rs.next()) {
                map.put("DBS_ID", rs.getInt("DatabaseId"));
                map.put("DBS_DriverClassName", rs
                        .getString("DriverClass"));
                map.put("DBS_URL", rs.getString("url"));
                map.put("DBS_UserName", rs.getString("UserName"));
                map.put("DBS_Password", rs.getString("password"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        if (null != map) {
            String driverClassName = map.get("DBS_DriverClassName").toString();
            String url = map.get("DBS_URL").toString();
            String userName = map.get("DBS_UserName").toString();
            String password = map.get("DBS_Password").toString();
            BasicDataSource dataSource = this.createDataSource(driverClassName,
                    url, userName, password);
            return dataSource;
        }
        return null;
    }
 
    public void setDataSource(Integer serverId, BasicDataSource dataSource) {
        this.addTargetDataSource(serverId + "", dataSource);
        DBContextHolder.setDBType(serverId + "");
    } 
}
