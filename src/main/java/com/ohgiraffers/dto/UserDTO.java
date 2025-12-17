package com.ohgiraffers.dto;

public class UserDTO {

    private int no;
    private String id;
    private String pwd;
    private String name;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int no, String id, String pwd, String name, String role) {
        this.no = no;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.role = role;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
