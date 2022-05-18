package com.wy.employee.dao.imply;

import com.wy.employee.dao.JobDao;
import com.wy.employee.po.Dept;
import com.wy.employee.po.Job;
import com.wy.employee.po.Staff;
import com.wy.employee.util.DBUtil;
import com.wy.employee.util.ReturnAaggregate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Job> listJobAll() {

        List<Job> JobList = new ArrayList<>();
        String sql = "SELECT * FROM job ORDER BY id";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setJname(rs.getString("jname"));
                job.setDescription(rs.getString("description"));
                JobList.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return JobList;
    }

    @Override
    public Job getJobById(int jobID) {
        Job job = null;
        String sql = "SELECT * FROM job WHERE id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, jobID);
            rs = pst.executeQuery();
            while (rs.next()) {
                job = new Job();
                job.setId(rs.getInt("id"));
                job.setJname(rs.getString("jname"));
                job.setJname(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return job;
    }

    @Override
    public int saveJob(Job job) {
        int id = 0;
        String sql = "INSERT INTO job(jname, description) values(?,?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, job.getJname());
            pst.setString(2, job.getDescription());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
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
    public int updateJob(Job job) {
        int result = 0;
        String sql = "UPDATE job SET jname=?,description=?  where id = ?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, job.getJname());
            pst.setString(2, job.getDescription());
            pst.setInt(3, job.getId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }


    @Override
    public ReturnAaggregate removeDept(int jobID) {
        ReturnAaggregate Aaggregate = new ReturnAaggregate();

        int result = 0;
        String listAffectRow =  "select * from  staff  WHERE job_id = ?";
        String delDeptSql = "DELETE FROM job WHERE id=?";

        try {
            con = DBUtil.getConnection();

            List<Staff> staffArrayList = new ArrayList<>();
            pst = con.prepareStatement(listAffectRow);
            pst.setInt(1, jobID);
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
            pst.setInt(1, jobID);
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
