package com.wy.employee.view;

public interface JobView {
    public void listJob();//查询所有职位信息
    public void getJobById();//通过职位id查询
    public void saveJob();//添加新的职位
    public void editJob();//编辑职位信息
    public void removeJob();//删除职位信息
}
