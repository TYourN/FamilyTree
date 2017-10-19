package cn.items.mssm.po;

public class FGeneration {
    private Integer geneid;

    private String genename;

    public Integer getGeneid() {
        return geneid;
    }

    public void setGeneid(Integer geneid) {
        this.geneid = geneid;
    }

    public String getGenename() {
        return genename;
    }

    public void setGenename(String genename) {
        this.genename = genename == null ? null : genename.trim();
    }
}