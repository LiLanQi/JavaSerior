package com.llq;

/*
部门类
 */
public class Department {
    private  String dName;

    public void setdName(String dName) {
        this.dName = dName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dName='" + dName + '\'' +
                '}';
    }
}
