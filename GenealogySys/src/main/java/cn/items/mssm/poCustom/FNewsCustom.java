package cn.items.mssm.poCustom;

import java.util.List;

import cn.items.mssm.po.FNews;

public class FNewsCustom extends FNews{
    
	private String url;
	
	private String memo;
	
	private List<String> urlList;

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}