package com.atzzazz.pojo;

import java.io.Serializable;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 13:18
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 42123L;
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private School school;

    public Employee() {
    }

    public Employee(Integer id, String last_name, String gender, String email) {
        this.id = id;
        this.lastName = last_name;
        this.gender = gender;
        this.email = email;
    }


    public School getSchool() {
        return school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
