package com.wy.employee.view.impl;

import com.wy.employee.dao.AdminDao;
import com.wy.employee.dao.imply.AdminDaoImpl;
import com.wy.employee.po.Admin;
import com.wy.employee.view.AdminView;

import java.util.Scanner;


public class AdminViewImpl implements AdminView {
    Scanner input = new Scanner(System.in);

    @Override
    public Admin login() {
        System.out.println("请输入管理员名称：");
        String adminName = input.next();
        System.out.println("请输入密码：");
        String password = input.next();

        AdminDao dao = new AdminDaoImpl();
        return dao.getAdminByNameByPass(adminName, password);
    }
}
