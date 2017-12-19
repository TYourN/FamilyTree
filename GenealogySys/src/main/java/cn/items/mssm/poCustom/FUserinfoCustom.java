package cn.items.mssm.poCustom;

import cn.items.mssm.po.FUserinfo;

public class FUserinfoCustom extends FUserinfo{
    
	private String username;
	
	private String password;
	
	private String birth;
	
	private String exoducs;
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getExoducs() {
		return exoducs;
	}
	public void setExoducs(String exoducs) {
		this.exoducs = exoducs;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}