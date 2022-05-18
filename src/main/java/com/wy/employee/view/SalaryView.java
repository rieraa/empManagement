package com.wy.employee.view;

public interface SalaryView {
    public void listSalary();//无条件查询所有薪水信息
    public void getSalaryByGrade();//通过员工grade查询
    public void saveSalary();//添加新的薪水
    public void editSalary();//编辑薪水信息
    public void removeSalary();//删除薪水信息
}
