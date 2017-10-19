package cn.items.mssm.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FNews {
    private Integer newsid;

    private String title;

    private String content;

    private Integer operid;

    private Integer famid;
     
    private Date opertime;

    private Integer newsopen;

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
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

    public Integer getFamid() {
        return famid;
    }

    public void setFamid(Integer famid) {
        this.famid = famid;
    }
   
    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Integer getNewsopen() {
        return newsopen;
    }

    public void setNewsopen(Integer newsopen) {
        this.newsopen = newsopen;
    }
}