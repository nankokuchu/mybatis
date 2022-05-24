package com.atzzazz.mapper;

import com.atzzazz.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 18:05
 */
public interface EmployeeDynamicSQLMapper {


    List<Employee> getEmployees(Employee employee);

    List<Employee> getEmpsBYConditionForeach(@Param("ids") List<Integer> IdList);

    List<Employee> getEmpsBYConditionChoose(Employee employee);
}
