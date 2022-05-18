package com.wy.employee.dao;

import com.wy.employee.po.Dept;
import com.wy.employee.po.Staff;
import com.wy.employee.util.ReturnAaggregate;


import java.util.List;

public interface DeptDao {

    public List<Dept> listDeptAll();//全查询
    public Dept getDeptById(int deptId);//根据员工id查询

    public int saveDept(Dept dept);//创建新部门

    public int updateDept(Dept dept);//修改部门信息

    public ReturnAaggregate removeDept(int deptId);//删除部门信息

}
