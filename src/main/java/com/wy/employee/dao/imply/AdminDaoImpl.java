package com.wy.employee.dao.imply;

import com.wy.employee.dao.AdminDao;
import com.wy.employee.po.Admin;
import com.wy.employee.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
        private Connection con = null;
        private PreparedStatement pst = null;
        private ResultSet rs = null;

    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {
        Admin admin = null;//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
        String sql = "select * from admin where adminName=? and password=?";//sql语句
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, adminName);
            pst.setString(2, password);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getString("password"));
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return admin;
    }
}
