package com.wy.employee.view.impl;

import com.wy.employee.dao.DeptDao;
import com.wy.employee.dao.JobDao;
import com.wy.employee.dao.imply.DeptDaoImpl;
import com.wy.employee.dao.imply.JobDaoImpl;
import com.wy.employee.po.Dept;
import com.wy.employee.po.Job;
import com.wy.employee.po.Staff;
import com.wy.employee.util.ReturnAaggregate;
import com.wy.employee.view.JobView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class JobViewImpl implements JobView {

    Scanner input = new Scanner(System.in);

    @Override
    public void listJob() {
        JobDao jobDao = new JobDaoImpl();
        List<Job> list = jobDao.listJobAll();
        System.out.println("职位id\t\t职位名称\t\t职位介绍\t\t");
        for (Job job : list) {

            System.out.printf("%d\t\t\t%4s\t\t%4s", job.getId(), job.getJname(), job.getDescription());
            System.out.println();
        }

        try {
            System.out.println("输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }//输入回车键继续
    }

    @Override
    public void getJobById() {
        System.out.println("请输入职位编号：");
        int jobID = input.nextInt();
        JobDao jobDao = new JobDaoImpl();
        Job job = jobDao.getJobById(jobID);
        if (job != null) {
            System.out.println("职位id\t\t职位名称\t\t职位介绍\t\t");
            System.out.printf("%d\t\t\t%4s\t\t%4s", job.getId(), job.getJname(), job.getDescription());
            System.out.println();
        } else {
            System.out.println("\n不存在该职位");


        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveJob() {
        Job job = new Job();
        System.out.println("请输入职位名称：");
        job.setJname(input.next());
        System.out.println("请输入职位介绍：");
        job.setDescription(input.next());
        JobDao jobDao = new JobDaoImpl();
        int id = jobDao.saveJob(job);


        if (id > 0) {
            System.out.println("\n新增职位成功！职位初始信息如下：\n");
            System.out.println("职位id\t\t职位姓名\t\t职位所在地\t\t");
            System.out.printf("%d\t\t\t%4s\t\t%4s", id, job.getJname(), job.getDescription());

        } else {
            System.out.println("\n新增职位失败！\n");
        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editJob() {
        System.out.println("请输入职位id：");
        int id = input.nextInt();
        JobDao jobDao = new JobDaoImpl();
        Job job = jobDao.getJobById(id);
        System.out.println(job);

        String inputStr;
        System.out.println("是否修改职位名称(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的部门名称：");
            job.setJname(input.next());
        }

        System.out.println("是否修改职位介绍y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入职位介绍：");
            job.setDescription(input.next());
        }

        int result = jobDao.updateJob(job);
        if (result > 0) {
            System.out.println("\n修改员工信息成功！\n");
        } else {
            System.out.println("\n修改员工信息失败！\n");
        }

    }

    @Override
    public void removeJob() {
        System.out.println("请输入职位编号：");
        int jobId = input.nextInt();
        JobDao dao = new JobDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            ReturnAaggregate re;
            re = dao.removeDept(jobId);
            int result = re.getN();
            List<Staff> list= re.getList();
            if(result==1) {
                System.out.println("删除职位成功！");
                System.out.println("staff表中以下行的job_id值为设为空");
                for (Staff staff : list) {
                    System.out.println("员工id\t\t员工姓名\t\t职务id\t\t上级领导\t\t入职日期\t\t\t工资\t\t奖金\t\t所在部门编号\t\t");
                    System.out.printf(" %-12d%-10s%-8d%-10d%-18s%-18.1f%-10.1f%-10d", staff.getId(), staff.getEname(), staff.getJob_id(), staff.getMgr(), staff.getJoinDate(), staff.getSalary(), staff.getBonus(), staff.getDept_id());
                }
            }else {
                System.out.println("删除职位失败！");
            }
        }

    }
}
