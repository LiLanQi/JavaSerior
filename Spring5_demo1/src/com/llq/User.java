package com.llq;

public class User {

    private String userName;
    private String age;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User() {
    }

    public User(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }

    public void add(){
        System.out.println("userName=" + userName);
    }

    public  void show(){
        System.out.println(userName + "今年" + age + "岁");
    }
}
