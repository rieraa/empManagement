package com.wy.employee.dao;

import com.wy.employee.po.SalaryGrade;
import com.wy.employee.util.ReturnAaggregate;

import java.util.List;

public interface SalaryDao {
    public List<SalaryGrade> listSalaryAll();//全查询

    public SalaryGrade getSalaryByGrade(int Grade);//根据grade查询

    public int saveGrade(SalaryGrade salaryGrade);//创建新等级

    public int updateGrade(SalaryGrade salaryGrade);//修改等级信息

    public int removeGrade(int Grade);//删除等级信息
}
