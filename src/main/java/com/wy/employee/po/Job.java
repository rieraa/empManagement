package com.wy.employee.po;

public class Job {
    private int id;
    private String jname;
    private String description;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jname='" + jname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getJname() {
        return jname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
