package cn.items.mssm.poCustom;

import java.util.Date;

import cn.items.mssm.po.FPictures;

public class FPicturesCustom extends FPictures{
	
	private String title;
	
	private Date opertime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}			
}
