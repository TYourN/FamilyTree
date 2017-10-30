package cn.items.mssm.po;

import java.util.Date;

public class FCul {
    private Integer culid;

    private String title;

    private String content;

    private Date opertime;

    public Integer getCulid() {
        return culid;
    }

    public void setCulid(Integer culid) {
        this.culid = culid;
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

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
}