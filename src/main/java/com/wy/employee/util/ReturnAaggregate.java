package com.wy.employee.util;

import com.wy.employee.po.Dept;
import com.wy.employee.po.Job;
import com.wy.employee.po.Staff;

import java.util.ArrayList;
import java.util.List;

public class ReturnAaggregate {

    private List<Staff> list = new ArrayList<>();
    private List<Job> listJob = new ArrayList<>();
    private int  n;

    public void setList(List<Staff> list) {
        this.list = list;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setListJob(List<Job> listJob) {
        this.listJob = listJob;
    }

    public int getN() {
        return n;
    }

    public List<Staff> getList() {
        return list;
    }

    public List<Job> getListJob() {
        return listJob;
    }
}
