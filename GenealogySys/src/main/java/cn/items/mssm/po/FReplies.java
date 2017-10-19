package cn.items.mssm.po;

import java.util.Date;

public class FReplies {
    private Integer replyid;

    private Integer replierid;

    private Integer postid;

    private String content;

    private Date createtime;

    private Integer goodcount;

    private Integer badcount;

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public Integer getReplierid() {
        return replierid;
    }

    public void setReplierid(Integer replierid) {
        this.replierid = replierid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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