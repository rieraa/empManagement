package com.wy.employee.dao.imply;

import com.wy.employee.dao.DeptDao;
import com.wy.employee.po.Dept;
import com.wy.employee.po.Staff;
import com.wy.employee.util.DBUtil;
import com.wy.employee.util.ReturnAaggregate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DeptDaoImpl implements DeptDao {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Dept> listDeptAll() {

        List<Dept> DeptList = new ArrayList<>();//通过长度判断是否查询到结果
        String sql = "SELECT * FROM Dept ORDER BY id";//sql语句
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                Dept dept = new Dept();//返回单个值时设置为null  如果输入账号密码错误则不能够从数据库中提取出数据 能够作为判断输入是否正确的条件
                dept.setId(rs.getInt("id"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                DeptList.add(dept);
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return DeptList;
    }

    @Override
    public Dept getDeptById(int deptId) {
        Dept dept_ = null;
        String sql = "SELECT * FROM dept WHERE id=?";//sql语句
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, deptId);
            rs = pst.executeQuery();//将sql语句进行编译
            while(rs.next()) {
                dept_ = new Dept();
                dept_.setId(rs.getInt("id"));
                dept_.setDname(rs.getString("dname"));
                dept_.setLoc(rs.getString("loc"));
            }
            //返回值将数据库中的数据封装在实例化的一个对象中 表数据转化为对象数据
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);//避免资源泄露，需要关闭链接
        }
        return dept_;
    }

    @Override
    public int saveDept(Dept dept) {
        int id = 0;
        String sql = "INSERT INTO dept(dname, loc) values(?,?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,dept.getDname());
            pst.setString(2,dept.getLoc());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }

        return id;

    }

    @Override
    public int updateDept(Dept dept) {
        int result = 0;
        String sql = "UPDATE dept SET dname=?,loc=?  where id = ?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, dept.getDname());
            pst.setString(2, dept.getLoc());
            pst.setInt(3, dept.getId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public ReturnAaggregate removeDept(int deptId) {
        ReturnAaggregate Aaggregate = new ReturnAaggregate();

        int result = 0;
        String listAffectRow =  "select * from  staff  WHERE dept_id = ?";
        String delDeptSql = "DELETE FROM dept WHERE id=?";

        try {
            con = DBUtil.getConnection();

            List<Staff> staffArrayList = new ArrayList<>();
            pst = con.prepareStatement(listAffectRow);
            pst.setInt(1, deptId);
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
                staffArrayList.add(staff);
            }
            Aaggregate.setList(staffArrayList);
            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(delDeptSql);
            pst.setInt(1, deptId);
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
        Aaggregate.setN(result);


        return Aaggregate;

    }
}
