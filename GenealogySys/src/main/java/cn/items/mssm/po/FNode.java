package cn.items.mssm.po;

public class FNode {
    private Integer nodeid;

    private String name;

    private Integer userinfoid;

    private Integer parentid;

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserinfoid() {
        return userinfoid;
    }

    public void setUserinfoid(Integer userinfoid) {
        this.userinfoid = userinfoid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}