package com.wy.employee.view;

public interface StaffView {
    public void listStaff();//无条件查询所有员工信息
    public void getStaffById();//通过员工id查询
    public void saveStaff();//添加新的员工
    public void editStaff();//编辑员工信息
    public void removeStaff();//删除员工信息
    public void getStaffByDate();//通过员工id查询

}
