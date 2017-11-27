package cn.items.mssm.po;

public class FProfile {
    private Integer proid;

    private Integer dbid;

    private String fulname;

    private String createage;

    private String introduce;

    private String url;

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getFulname() {
        return fulname;
    }

    public void setFulname(String fulname) {
        this.fulname = fulname == null ? null : fulname.trim();
    }

    public String getCreateage() {
        return createage;
    }

    public void setCreateage(String createage) {
        this.createage = createage == null ? null : createage.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}