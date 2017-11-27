package cn.items.mssm.poCustom;

import java.util.List;

import cn.items.mssm.po.FProfile;

public class FProfileCustom extends FProfile{

	/** 
	 * @ClassName: FProfileCustom 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月13日 下午12:37:56 
	 */
	
	private String famlocal;
	
	private List<String> urlList;
	
	private List<Integer> countList;
	
	private String html;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public List<Integer> getCountList() {
		return countList;
	}

	public void setCountList(List<Integer> countList) {
		this.countList = countList;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getFamlocal() {
		return famlocal;
	}

	public void setFamlocal(String famlocal) {
		this.famlocal = famlocal;
	}
}
