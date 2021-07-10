package org.softcits.cn.model;

public class User {
    private Integer id;
    private String uname;
    private String passwd;

    public User() {
        this(null, null, null);
    }

    public User( String uname, String passwd) {
       this(null, uname, passwd);
    }

    public User(Integer id, String uname, String passwd) {
        this.id = id;
        this.uname = uname;
        this.passwd = passwd;
    }

    public Integer getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
