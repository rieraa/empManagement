package com.wy.employee.util;

import java.sql.*;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_information?characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "256889";

    //获取Connection 静态方法 使用方便
    public static Connection getConnection() {
        Connection con = null;
        try {

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //关闭资源
    public static void close(ResultSet rs, PreparedStatement pst, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pst = null;
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }
}
