package cn.items.mssm.poCustom;

import cn.items.mssm.po.FCul;

public class FCulCustom extends FCul{
    
	private String type;
	
	private String otime;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtime() {
		return otime;
	}

	public void setOtime(String otime) {
		this.otime = otime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}