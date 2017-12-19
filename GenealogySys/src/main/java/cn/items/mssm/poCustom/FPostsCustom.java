package cn.items.mssm.poCustom;

import cn.items.mssm.po.FPosts;

public class FPostsCustom extends FPosts{
	
	private String name;
	
	private String pic;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String ctimeString;

	public String getCtimeString() {
		return ctimeString;
	}

	public void setCtimeString(String ctimeString) {
		this.ctimeString = ctimeString;
	}
	
}