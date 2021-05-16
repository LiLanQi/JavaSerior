package com.llq;
/*
员工类
 */
public class Employee {
    private String name;
    private String gender;

    //员工属于某一个部门
    private Department department;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                '}';
    }
}
