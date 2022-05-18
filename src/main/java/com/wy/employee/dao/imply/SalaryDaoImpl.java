package com.wy.employee.dao.imply;

import com.wy.employee.dao.SalaryDao;
import com.wy.employee.po.Job;
import com.wy.employee.po.SalaryGrade;
import com.wy.employee.util.DBUtil;
import com.wy.employee.util.ReturnAaggregate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDaoImpl implements SalaryDao {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<SalaryGrade> listSalaryAll() {
        List<SalaryGrade> salaryList = new ArrayList<>();
        String sql = "SELECT * FROM salarygrade ORDER BY grade";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SalaryGrade salaryGrade = new SalaryGrade();
                salaryGrade.setGrade(rs.getInt("grade"));
                salaryGrade.setLosalary(rs.getInt("losalary"));
                salaryGrade.setHisalary(rs.getInt("hisalary"));
                salaryList.add(salaryGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return salaryList;


    }

    @Override
    public SalaryGrade getSalaryByGrade(int Grade) {
        SalaryGrade salaryGrade = null;
        String sql = "SELECT * FROM salarygrade WHERE grade=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Grade);
            rs = pst.executeQuery();
            while (rs.next()) {
                salaryGrade = new SalaryGrade();
                salaryGrade.setGrade(rs.getInt("grade"));
                salaryGrade.setLosalary(rs.getInt("losalary"));
                salaryGrade.setHisalary(rs.getInt("hisalary"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return salaryGrade;
    }

    @Override
    public int saveGrade(SalaryGrade salaryGrade) {
        int id = 0;
        String sql = "INSERT INTO salarygrade(losalary, hisalary) values(?,?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, salaryGrade.getLosalary());
            pst.setInt(2, salaryGrade.getHisalary());
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
    public int updateGrade(SalaryGrade salaryGrade) {
        int result = 0;
        String sql = "UPDATE salarygrade SET losalary=?,hisalary=?  where grade = ?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaryGrade.getLosalary());
            pst.setInt(2, salaryGrade.getHisalary());
            pst.setInt(3, salaryGrade.getGrade());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int removeGrade(int Grade) {
        int result = 0;

        String delStaffSql = "DELETE FROM salarygrade WHERE grade=?";

        try {
            con = DBUtil.getConnection();
            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(delStaffSql);
            pst.setInt(1, Grade);
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
}
