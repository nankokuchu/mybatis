package com.atzzazz.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 14:10
 */
public class School implements Serializable {

    private static final long serialVersionUID = 42121233L;
    private Integer id;
    private String schoolName;
    private List<Employee> emps;

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public School() {
    }

    public School(Integer id, String schoolName) {
        this.id = id;
        this.schoolName = schoolName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
