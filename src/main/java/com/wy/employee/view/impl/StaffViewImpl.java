package com.wy.employee.view.impl;

import com.wy.employee.dao.AdminDao;
import com.wy.employee.dao.StaffDao;
import com.wy.employee.dao.imply.AdminDaoImpl;
import com.wy.employee.dao.imply.StaffDaoImpl;
import com.wy.employee.po.Staff;
import com.wy.employee.view.StaffView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffViewImpl implements StaffView {
    String m;
    Scanner input = new Scanner(System.in);

    @Override

    public void listStaff() {
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> list = staffDao.listStaffAll();
        System.out.println("员工id\t\t员工姓名\t\t   职务id\t\t上级领导\t\t\t  入职日期\t\t 工资\t\t奖金\t\t所在部门编号\t\t");
        for (Staff staff : list) {
            System.out.println(" " + staff.getId() + "\t\t" + " " + staff.getEname() + " \t\t\t" + staff.getJob_id() + "\t\t\t" + staff.getMgr() +
                    "\t\t\t" + staff.getJoinDate() + "\t\t" + staff.getSalary() + "\t\t" + staff.getBonus() + "\t\t\t" + staff.getDept_id());
//            System.out.printf(" %-12d%s%8d%-10d%-18s%-18.1f%-10.1f%-10d", staff.getId(), staff.getEname(), staff.getJob_id(), staff.getMgr(), staff.getJoinDate(), staff.getSalary(), staff.getBonus(), staff.getDept_id());
//            System.out.println();
        }

        try {
            System.out.println("输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }//输入回车键继续
    }

    @Override
    public void getStaffById() {
        System.out.println("请输入员工编号：");
        int staffId = input.nextInt();
        StaffDao staffDao = new StaffDaoImpl();
        Staff staff = staffDao.getStaffById(staffId);
        if (staff != null) {
            System.out.println("员工id\t\t员工姓名\t\t   职务id\t\t上级领导\t\t\t  入职日期\t\t 工资\t\t奖金\t\t所在部门编号\t\t");
            System.out.println(" " + staff.getId() + "\t\t" + " " + staff.getEname() + " \t\t\t" + staff.getJob_id() + "\t\t\t" + staff.getMgr() +
                    "\t\t\t" + staff.getJoinDate() + "\t\t" + staff.getSalary() + "\t\t" + staff.getBonus() + "\t\t\t" + staff.getDept_id());
//            System.out.printf(" %-12d%-10s%-8d%-10d%-18s%-18.1f%-10.1f%-10d", staff.getId(), staff.getEname(), staff.getJob_id(), staff.getMgr(), staff.getJoinDate(), staff.getSalary(), staff.getBonus(), staff.getDept_id());
        } else {
            System.out.println("\n不存在该员工");


        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveStaff() {
        System.out.println("请输入新员工姓名：");
        String ename = input.next();
        StaffDao staffDao = new StaffDaoImpl();
        int ID = staffDao.saveStaff(ename);
        Staff staff = staffDao.getStaffById(ID);

        if (ID > 0) {
            System.out.println("\n新增员工成功！新员工初始信息如下：\n");
            System.out.println("员工id\t\t员工姓名\t\t   职务id\t\t上级领导\t\t\t  入职日期\t\t 工资\t\t奖金\t\t所在部门编号\t\t");
            System.out.println(" " + staff.getId() + "\t\t" + " " + staff.getEname() + " \t\t\t" + staff.getJob_id() + "\t\t\t" + staff.getMgr() +
                    "\t\t\t" + staff.getJoinDate() + "\t\t" + staff.getSalary() + "\t\t" + staff.getBonus() + "\t\t\t" + staff.getDept_id());

        } else {
            System.out.println("\n新增员工失败！\n");
        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editStaff() {

        System.out.println("请输入员工id：");
        int id = input.nextInt();
        StaffDao staffDao = new StaffDaoImpl();
        Staff staff = staffDao.getStaffById(id);
        System.out.println(staff);

        String inputStr = "";
        System.out.println("是否修改员工姓名(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的员工姓名：");
            staff.setEname(input.next());
        }

        System.out.println("是否修改职务id(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的职务id：");
            staff.setJob_id(input.nextInt());
        }

        System.out.println("是否修改上级领导(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入上级领导：");
            staff.setMgr(input.nextInt());
        }

        System.out.println("是否修改入职日期(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的入职日期：");
            staff.setJoinDate(input.next());
        }

        System.out.println("是否修改工资(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入工资：");
            staff.setSalary(input.nextDouble());
        }

        System.out.println("是否修改奖金(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入奖金：");
            staff.setBonus(input.nextDouble());
        }

        System.out.println("是否修改所在部门编号(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入所在部门编号：");
            staff.setDept_id(input.nextInt());
        }

        int result = staffDao.updateStaff(staff);
        if (result > 0) {
            System.out.println("\n修改员工信息成功！\n");
        } else {
            System.out.println("\n修改员工信息失败！\n");
        }
    }


    @Override
    public void removeStaff() {
        System.out.println("请输入员工编号：");
        int staffId = input.nextInt();

        StaffDao dao = new StaffDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if (input.next().equals("y")) {
            int result = dao.removeStaff(staffId);
            if (result == 1) {
                System.out.println("删除员工成功！");
            } else {
                System.out.println("删除员工失败！");
            }
        }
    }

    @Override
    public void getStaffByDate() {
        System.out.println("请输入起始日期：");
        String into = input.next();
        System.out.println("请输入截止日期：");
        String out = input.next();
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> list = staffDao.getStaffByDate(into, out);
        System.out.println("员工id\t\t员工姓名\t\t   职务id\t\t上级领导\t\t\t  入职日期\t\t 工资\t\t奖金\t\t所在部门编号\t\t");
        for (Staff staff : list) {
            System.out.println(" " + staff.getId() + "\t\t" + " " + staff.getEname() + " \t\t\t" + staff.getJob_id() + "\t\t\t" + staff.getMgr() +
                    "\t\t\t" + staff.getJoinDate() + "\t\t" + staff.getSalary() + "\t\t" + staff.getBonus() + "\t\t\t" + staff.getDept_id());
//            System.out.printf(" %-12d%s%8d%-10d%-18s%-18.1f%-10.1f%-10d", staff.getId(), staff.getEname(), staff.getJob_id(), staff.getMgr(), staff.getJoinDate(), staff.getSalary(), staff.getBonus(), staff.getDept_id());
//            System.out.println();
        }
        try {
            System.out.println("\n输入回车继续：");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



