package cn.items.mssm.po;

public class FFamnames {
    private Integer fnameid;

    private String title;

    private String content;

    private String memo;

    public Integer getFnameid() {
        return fnameid;
    }

    public void setFnameid(Integer fnameid) {
        this.fnameid = fnameid;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}