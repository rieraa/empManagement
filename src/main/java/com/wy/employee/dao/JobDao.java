package com.wy.employee.dao;

import com.wy.employee.po.Dept;
import com.wy.employee.po.Job;
import com.wy.employee.util.ReturnAaggregate;

import java.util.List;

public interface JobDao {

    public List<Job> listJobAll();//全查询
    public Job getJobById(int jobID);//根据职位id查询

    public int saveJob(Job job);//创建新职位

    public int updateJob(Job job);//修改职位信息

    public ReturnAaggregate removeDept(int jobID);//删除职位信息


}
