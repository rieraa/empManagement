package com.wy.employee;

import com.wy.employee.po.Admin;
import com.wy.employee.view.*;
import com.wy.employee.view.impl.*;

import java.util.Scanner;
import java.util.concurrent.TransferQueue;

public class Entry {

    static int menu = 0;
    static Scanner input = new Scanner(System.in);

    public static void work() {


        AdminView adminView = new AdminViewImpl();
        Admin admin = adminView.login();


        if (admin!=null) {
            while (menu != 5) {
                //输出主菜单
                System.out.println("-----------------------------------------------------------------");
                System.out.println("*****************************************************************");
                System.out.println(" \t\t\t\t\t\t  员工信息管理系统  \t\t\t\t\t\t ");
                System.out.println("*****************************************************************");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("\n");
                System.out.println("=============================功能选择=============================");
                System.out.println("                           1.员工信息                           ");
                System.out.println("                           2.部门信息                           ");
                System.out.println("                           3.职位信息                           ");
                System.out.println("                           4.薪水信息                           ");
                System.out.println("                           5.退出系统                           ");
                System.out.println("请输入你的选择(1~5)：");

                menu = input.nextInt();
                switch (menu) {
                    case 1 -> staffManager();
                    case 2 -> deptManager();
                    case 3 -> jobManager();
                    case 4 -> salaryManager();
                    case 5 -> System.out.println("************************欢迎下次光临员工信息管理系统************************");
                    default -> System.out.println("没有这个选项！\n");
                }
            }
        } else {
            System.out.println("error");
        }
    }

    public static void staffManager() {
        while (menu != 7) {
            //输出主菜单
            System.out.println("\n");
            System.out.println("-------------------------------员工信息-------------------------------");
            System.out.println("1.查询所有员工|2.按照员工编号查询员工|3.添加员工|4.修改员工|5.删除员工|6.通过日期筛选员工7.返回上一级");
            System.out.println("请输入你的选择(1~6):");
            menu = input.nextInt();

            StaffView staffView = new StaffViewImpl();

            switch (menu) {
                case 1 -> staffView.listStaff();
                case 2 -> staffView.getStaffById();
                case 3 -> staffView.saveStaff();
                case 4 -> staffView.editStaff();
                case 5 -> staffView.removeStaff();
                case 6 -> staffView.getStaffByDate();
                case 7->{}
                default -> System.out.println("没有这个选项！\n");
            }
        }


    }

    public static void deptManager() {
        while (menu != 6) {
            //输出主菜单
            System.out.println("\n");
            System.out.println("-------------------------------部门信息-------------------------------");
            System.out.println("1.查询所有部门|2.按照部门编号查询员工|3.添加部门|4.修改部门|5.删除部门|6.返回上一级");
            System.out.println("请输入你的选择(1~6):");
            menu = input.nextInt();

            DeptView deptView = new DeptViewImpl();

            switch (menu) {
                case 1 -> deptView.listDept();
                case 2 -> deptView.getDeptById();
                case 3 -> deptView.saveDept();
                case 4 -> deptView.editDept();
                case 5 -> deptView.removeDept();
                case 6 -> {
                }
                default -> System.out.println("没有这个选项！\n");
            }
        }
    }

    public static void jobManager() {
        while (menu != 6) {
            //输出主菜单
            System.out.println("\n");
            System.out.println("-------------------------------职位信息-------------------------------");
            System.out.println("1.查询所有职位|2.按照职位编号查询员工|3.添加职位|4.修改职位|5.删除职位|6.返回上一级");
            System.out.println("请输入你的选择(1~6):");
            menu = input.nextInt();

            DeptView deptView = new DeptViewImpl();
            JobView jobView = new JobViewImpl();

            switch (menu) {
                case 1 -> jobView.listJob();
                case 2 -> jobView.getJobById();
                case 3 -> jobView.saveJob();
                case 4 -> jobView.editJob();
                case 5 -> jobView.removeJob();
                case 6 -> {
                }
                default -> System.out.println("没有这个选项！\n");
            }
        }
    }

    public static void salaryManager() {
        while (menu != 6) {
            //输出主菜单
            System.out.println("\n");
            System.out.println("-------------------------------工资等级信息-------------------------------");
            System.out.println("1.查询所有薪水等级|2.按照工资等级编号查询工资|3.添加工资等级|4.修改工资等级|5.删除工资等级|6.返回上一级");
            System.out.println("请输入你的选择(1~6):");
            menu = input.nextInt();


            SalaryView salaryView = new SalaryViewImpl();

            switch (menu) {
                case 1 -> salaryView.listSalary();
                case 2 -> salaryView.getSalaryByGrade();
                case 3 -> salaryView.saveSalary();
                case 4 -> salaryView.editSalary();
                case 5 -> salaryView.removeSalary();
                case 6 -> {
                }
                default -> System.out.println("没有这个选项！\n");
            }
        }
    }


    public static void main(String[] args) {
        work();


    }


}

