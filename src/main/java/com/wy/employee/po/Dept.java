package com.wy.employee.po;

public class Dept {
    private int id;
    private String dname;
    private String loc;

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getDname() {
        return dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
