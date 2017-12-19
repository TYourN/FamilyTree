package cn.items.mssm.poCustom;

import cn.items.mssm.po.FReplies;

public class FRepliesCustom extends FReplies{
    
	private Integer parentid;
	
	private String pic;
	
	private String name;

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

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}