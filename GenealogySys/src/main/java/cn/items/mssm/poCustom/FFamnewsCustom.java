package cn.items.mssm.poCustom;

import cn.items.mssm.po.FFamnews;
import net.sf.json.JSONArray;

public class FFamnewsCustom extends FFamnews{
	
	private String memo;
	
	private String url;
	
	private Integer pagesize;//第几页
	
	private Integer pagenum;//每页多少条
	
	private JSONArray locate;//页脚存储

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public JSONArray getLocate() {
		return locate;
	}

	public void setLocate(JSONArray locate) {
		this.locate = locate;
	}
	
}
