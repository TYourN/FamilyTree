package cn.items.mssm.po;

public class FFunction {
    private Integer funcid;

    private String title;

    private Integer parentid;

    private String url;

    private String memo;
    
    private String flag;

    public Integer getFuncid() {
        return funcid;
    }

    public void setFuncid(Integer funcid) {
        this.funcid = funcid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMemo() {
        return memo;
    }   

	public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}
}