package cn.items.mssm.po;

public class FDatabase {
    private Integer databaseid;

    private String famtitle;

    private String famlocal;

    private String databasekey;

    private String driverclass;

    private String url;

    private String username;

    private String password;

    public Integer getDatabaseid() {
        return databaseid;
    }

    public void setDatabaseid(Integer databaseid) {
        this.databaseid = databaseid;
    }

    public String getFamtitle() {
        return famtitle;
    }

    public void setFamtitle(String famtitle) {
        this.famtitle = famtitle == null ? null : famtitle.trim();
    }

    public String getFamlocal() {
        return famlocal;
    }

    public void setFamlocal(String famlocal) {
        this.famlocal = famlocal == null ? null : famlocal.trim();
    }

    public String getDatabasekey() {
        return databasekey;
    }

    public void setDatabasekey(String databasekey) {
        this.databasekey = databasekey == null ? null : databasekey.trim();
    }

    public String getDriverclass() {
        return driverclass;
    }

    public void setDriverclass(String driverclass) {
        this.driverclass = driverclass == null ? null : driverclass.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}