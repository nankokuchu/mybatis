package com.atzzazz.mapper;

import com.atzzazz.pojo.Employee;

import java.util.List;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 13:18
 */
public interface EmployeeMapper {

    Employee getEmpByIdStep(Integer id);

    Employee getEmpById(Integer id);

    Employee getEmpAndSchool(Integer id);

    List<Employee> getEmpsBySchoolId(Integer schoolId);
}
