package cn.items.mssm.poCustom;

import cn.items.mssm.po.FFamnews;
import net.sf.json.JSONArray;

public class FFamnewsCustom extends FFamnews{
	
	private String memo;
	
	private String url;
	
	private Integer pagesize;//�ڼ�ҳ
	
	private Integer pagenum;//ÿҳ������
	
	private JSONArray locate;//ҳ�Ŵ洢

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
