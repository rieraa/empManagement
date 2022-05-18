package com.wy.employee.dao;

import com.wy.employee.po.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPass(String adminName, String password);//返回单个对象使用get


}
