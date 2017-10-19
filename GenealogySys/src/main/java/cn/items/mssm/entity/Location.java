package cn.items.mssm.entity;

public class Location {

	/** 
	 * @ClassName: Location 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月17日 下午3:42:33 
	 */
	
	private String title;
	private String attr;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}	
	
	public Location(String title,String attr){
		super();
		this.title=title;
		this.attr=attr;
	}
}
