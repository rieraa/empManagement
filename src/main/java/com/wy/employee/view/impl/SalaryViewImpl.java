package com.wy.employee.view.impl;

import com.wy.employee.dao.JobDao;
import com.wy.employee.dao.SalaryDao;
import com.wy.employee.dao.StaffDao;
import com.wy.employee.dao.imply.JobDaoImpl;
import com.wy.employee.dao.imply.SalaryDaoImpl;
import com.wy.employee.dao.imply.StaffDaoImpl;
import com.wy.employee.po.Job;
import com.wy.employee.po.SalaryGrade;
import com.wy.employee.po.Staff;
import com.wy.employee.util.ReturnAaggregate;
import com.wy.employee.view.SalaryView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SalaryViewImpl implements SalaryView {

    Scanner input = new Scanner(System.in);
    @Override
    public void listSalary() {
        SalaryDao salaryDao = new SalaryDaoImpl();
        List<SalaryGrade> list = salaryDao.listSalaryAll();
        System.out.println("工资等级\t\t最低工资\t\t最高工资\t\t");
        for (SalaryGrade salaryGrade : list) {

            System.out.printf("%d\t\t\t%4s\t\t%4s", salaryGrade.getGrade(), salaryGrade.getLosalary(), salaryGrade.getHisalary());
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
    public void getSalaryByGrade() {
        System.out.println("请输入工资等级：");
        int grade = input.nextInt();
        SalaryDao salaryDao = new SalaryDaoImpl();
        SalaryGrade salaryGrade = salaryDao.getSalaryByGrade(grade);
        if (salaryGrade != null) {
            System.out.println("工资等级\t\t最低工资\t\t最高工资\t\t");
            System.out.printf("%d\t\t\t%4s\t\t%4s", salaryGrade.getGrade(), salaryGrade.getLosalary(), salaryGrade.getHisalary());
        } else {
            System.out.println("\n不存在该工资等级");


        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveSalary() {

        SalaryGrade salaryGrade = new SalaryGrade();
        System.out.println("请输入最低工资：");
        salaryGrade.setLosalary(input.nextInt());
        System.out.println("请输入最高工资：");
        salaryGrade.setHisalary(input.nextInt());
        SalaryDao  salaryDao = new SalaryDaoImpl();
        int id = salaryDao.saveGrade(salaryGrade);


        if (id > 0) {
            System.out.println("\n新增工资等级成功！初始信息如下：\n");
            System.out.println("工资等级\t\t最低工资\t\t最高工资\t\t");
            System.out.printf("%d\t\t\t%4d\t\t%4d",id, salaryGrade.getLosalary(), salaryGrade.getHisalary());

        } else {
            System.out.println("\n新增工资等级失败！\n");
        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editSalary() {
        System.out.println("请输入工资等级：");
        int grade = input.nextInt();
        SalaryDao  salaryDao = new SalaryDaoImpl();
        SalaryGrade salaryGrade = salaryDao.getSalaryByGrade(grade);
        System.out.println(salaryGrade);

        String inputStr;
        System.out.println("是否修改最低工资(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的最低工资：");
            salaryGrade.setLosalary(input.nextInt());
        }

        System.out.println("是否修改最高工资y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入最高工资：");
            salaryGrade.setHisalary(input.nextInt());
        }

        int result = salaryDao.updateGrade(salaryGrade);
        if (result > 0) {
            System.out.println("\n修改工资等级成功！\n");
        } else {
            System.out.println("\n修改工资等级失败！\n");
        }
    }

    @Override
    public void removeSalary() {
        System.out.println("请输入工资等级：");
        int grade = input.nextInt();
        SalaryDao  salaryDao = new SalaryDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {

            int result = salaryDao.removeGrade(grade);

            if(result==1) {
                System.out.println("删除工资等级成功！");

            }else {
                System.out.println("删除工资等级失败！");
            }
        }
    }
}
