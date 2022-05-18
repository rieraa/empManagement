package com.wy.employee.view.impl;

import com.wy.employee.dao.DeptDao;
import com.wy.employee.dao.StaffDao;
import com.wy.employee.dao.imply.DeptDaoImpl;
import com.wy.employee.dao.imply.StaffDaoImpl;
import com.wy.employee.po.Dept;
import com.wy.employee.po.Staff;
import com.wy.employee.util.ReturnAaggregate;
import com.wy.employee.view.DeptView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeptViewImpl implements DeptView {
    Scanner input = new Scanner(System.in);

    @Override
    public void listDept() {
        DeptDao deptDao = new DeptDaoImpl();
        List<Dept> list = deptDao.listDeptAll();
        System.out.println("部门id\t\t部门名称\t\t部门所在地\t\t");
        for (Dept dept : list) {
//
            System.out.printf("%d\t\t\t%4s\t\t%4s", dept.getId(), dept.getDname(), dept.getLoc());
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
    public void getDeptById() {
        System.out.println("请输入部门编号：");
        int deptId = input.nextInt();
        DeptDao deptDao = new DeptDaoImpl();
        Dept dept = deptDao.getDeptById(deptId);
        if (dept != null) {
            System.out.println("部门id\t\t部门名称\t\t部门所在地\t\t");
            System.out.printf("%d\t\t\t%4s\t\t%4s", dept.getId(), dept.getDname(), dept.getLoc());
            System.out.println();
        } else {
            System.out.println("\n不存在该部门");


        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDept() {
        Dept dept = new Dept();
        System.out.println("请输入部门名称：");
        dept.setDname(input.next());
        System.out.println("请输入部门位置：");
        dept.setLoc(input.next());
        DeptDao deptDao = new DeptDaoImpl();
        int id = deptDao.saveDept(dept);


        if(id>0) {
            System.out.println("\n新增部门成功！部门初始信息如下：\n");
            System.out.println("部门id\t\t部门名称\t\t部门所在地\t\t");
            System.out.printf("%d\t\t\t%4s\t\t%4s", id, dept.getDname(), dept.getLoc());

        }else {
            System.out.println("\n新增部门失败！\n");
        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editDept() {
        System.out.println("请输入部门id：");
        int id = input.nextInt();
        DeptDao deptDao = new DeptDaoImpl();
        Dept dept = deptDao.getDeptById(id);
        System.out.println(dept);

        String inputStr;
        System.out.println("是否修改部门名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的部门名称：");
            dept.setDname(input.next());
        }

        System.out.println("是否修改部门所在地(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入部门所在地：");
            dept.setLoc(input.next());
        }

        int result = deptDao.updateDept(dept);
        if (result > 0) {
            System.out.println("\n修改员工信息成功！\n");
        } else {
            System.out.println("\n修改员工信息失败！\n");
        }


    }

    @Override
    public void removeDept() {
        System.out.println("请输入部门编号：");
        int deptId = input.nextInt();
        DeptDao dao = new DeptDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            ReturnAaggregate re;
            re = dao.removeDept(deptId);
            int result = re.getN();
            List<Staff> list= re.getList();
            if(result==1) {
                System.out.println("删除部门成功！");
                System.out.println("staff表中以下行的dept_id值为设为空");
                for (Staff staff : list) {
                    System.out.println("员工id\t\t员工姓名\t\t职务id\t\t上级领导\t\t入职日期\t\t\t工资\t\t奖金\t\t所在部门编号\t\t");
                    System.out.printf(" %-12d%-10s%-8d%-10d%-18s%-18.1f%-10.1f%-10d", staff.getId(), staff.getEname(), staff.getJob_id(), staff.getMgr(), staff.getJoinDate(), staff.getSalary(), staff.getBonus(), staff.getDept_id());
                }
            }else {
                System.out.println("删除部门失败！");
            }
        }

    }
}
