package com.wy.employee.dao;

import com.wy.employee.po.Staff;

import java.util.List;

public interface StaffDao {
    public List<Staff> listStaffAll();//全查询

    public Staff getStaffById(int staffId);//根据员工id查询

    public int saveStaff(String eName);//创建新员工

    public int updateStaff(Staff staff);//修改员工信息

    public int removeStaff(int staffId);//删除员工信息


    public List<Staff> getStaffByDate(String into,String out);//根据入职时间查询



}
