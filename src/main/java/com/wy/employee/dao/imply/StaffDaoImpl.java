package com.wy.employee.dao.imply;

import com.wy.employee.dao.StaffDao;
import com.wy.employee.po.Admin;
import com.wy.employee.po.Staff;
import com.wy.employee.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Staff> listStaffAll() {

        List<Staff> staffList = new ArrayList<>();//通过长度判断是否查询到结果
        String sql = "SELECT * FROM staff ORDER BY id";//sql语句
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                Staff staff = new Staff();//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
                staff.setId(rs.getInt("id"));
                staff.setEname(rs.getString("ename"));
                staff.setJob_id(rs.getInt("job_id"));
                staff.setMgr(rs.getInt("mgr"));
                staff.setJoinDate(rs.getString("joinDate"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setBonus(rs.getDouble("bonus"));
                staff.setDept_id(rs.getInt("dept_id"));
                staffList.add(staff);
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return staffList;
    }

    @Override
    public Staff getStaffById(int staffId) {
        Staff staff = null;//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
        String sql = "SELECT * FROM staff WHERE id=?";//sql语句
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, staffId);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                staff = new Staff();//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
                staff.setId(rs.getInt("id"));
                staff.setEname(rs.getString("ename"));
                staff.setJob_id(rs.getInt("job_id"));
                staff.setMgr(rs.getInt("mgr"));
                staff.setJoinDate(rs.getString("joinDate"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setBonus(rs.getDouble("bonus"));
                staff.setDept_id(rs.getInt("dept_id"));
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return staff;
    }

    @Override
    public int saveStaff(String eName) {
        int ID = 0;
        String sql = "INSERT INTO staff(ename) values(?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,eName);
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                ID = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }

        return ID;
    }

    @Override
    public int updateStaff(Staff staff) {
        int result = 0;
        String sql = "update staff set ename=?,job_id=?,mgr=?,joinDate=?,salary=?,bonus=?,dept_id=? where id = ?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, staff.getEname());
            pst.setInt(2, staff.getJob_id());
            pst.setInt(3, staff.getMgr());
            pst.setString(4, staff.getJoinDate());
            pst.setDouble(5, staff.getSalary());
            pst.setDouble(6, staff.getBonus());
            pst.setInt(7, staff.getDept_id());
            pst.setInt(8, staff.getId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;

    }

    @Override
    public int removeStaff(int staffId) {
        int result = 0;
        String setMgrNull =  "UPDATE staff SET mgr=0000 WHERE mgr = ?";
        String delStaffSql = "DELETE FROM staff WHERE id=?";

        try {
            con = DBUtil.getConnection();
            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(setMgrNull);
            pst.setInt(1, staffId);
            pst.executeUpdate();


            pst = con.prepareStatement(delStaffSql);
            pst.setInt(1, staffId);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public List<Staff> getStaffByDate(String into, String out) {
        List<Staff> staffList = new ArrayList<>();//通过长度判断是否查询到结果
        String sql = "SELECT * FROM staff where joinDate>=? and joinDate<=? order by joinDate";//sql语句
        try {

            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, into);
            pst.setString(2,out);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                Staff staff = new Staff();//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
                staff.setId(rs.getInt("id"));
                staff.setEname(rs.getString("ename"));
                staff.setJob_id(rs.getInt("job_id"));
                staff.setMgr(rs.getInt("mgr"));
                staff.setJoinDate(rs.getString("joinDate"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setBonus(rs.getDouble("bonus"));
                staff.setDept_id(rs.getInt("dept_id"));
                staffList.add(staff);
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return staffList;

    }
}
