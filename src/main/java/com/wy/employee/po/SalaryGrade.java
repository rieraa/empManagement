package com.wy.employee.po;

public class SalaryGrade {
    private int grade;
    private int losalary;
    private int hisalary;

    @Override
    public String toString() {
        return "SalaryGrade{" +
                "grade=" + grade +
                ", losalary=" + losalary +
                ", hisalary=" + hisalary +
                '}';
    }

    public int getGrade() {
        return grade;
    }

    public int getHisalary() {
        return hisalary;
    }

    public int getLosalary() {
        return losalary;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setHisalary(int hisalary) {
        this.hisalary = hisalary;
    }

    public void setLosalary(int losalary) {
        this.losalary = losalary;
    }
}
