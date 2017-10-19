package cn.items.mssm.po;

import java.util.Date;

public class FPosts {
    private Integer postid;

    private String title;

    private String content;

    private Integer posterid;

    private Integer famid;

    private Date createtime;

    private Date updatetime;

    private Integer goodcount;

    private Integer badcount;

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
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

    public Integer getPosterid() {
        return posterid;
    }

    public void setPosterid(Integer posterid) {
        this.posterid = posterid;
    }

    public Integer getFamid() {
        return famid;
    }

    public void setFamid(Integer famid) {
        this.famid = famid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(Integer goodcount) {
        this.goodcount = goodcount;
    }

    public Integer getBadcount() {
        return badcount;
    }

    public void setBadcount(Integer badcount) {
        this.badcount = badcount;
    }
}