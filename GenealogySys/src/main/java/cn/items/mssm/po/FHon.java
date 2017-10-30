package cn.items.mssm.po;

import java.util.Date;

public class FHon {
    private Integer honid;

    private String title;

    private String content;

    private Integer operid;

    private Date opertime;

    public Integer getHonid() {
        return honid;
    }

    public void setHonid(Integer honid) {
        this.honid = honid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
}