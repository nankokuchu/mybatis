package com.atzzazz;

import com.atzzazz.mapper.EmployeeDynamicSQLMapper;
import com.atzzazz.mapper.EmployeeMapper;
import com.atzzazz.mapper.SchoolMapper;
import com.atzzazz.pojo.Employee;
import com.atzzazz.pojo.School;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 13:26
 */
public class EmployeeMapperTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory;
    }


    @Test
    public void testGetEmpById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
        session.close();
    }

    @Test
    public void getEmpAndSchool() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmpAndSchool(13);
        System.out.println(employee);
    }

    @Test
    public void testGetEmpByIdStep() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmpByIdStep(8);
        System.out.println(employee.getLastName());
        System.out.println(employee.getSchool());
        session.close();
    }

    @Test
    public void testGetById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SchoolMapper mapper = session.getMapper(SchoolMapper.class);
        School school = mapper.getById(2);
        System.out.println(school);
        session.close();
    }

    @Test
    public void testGetEmployees() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeDynamicSQLMapper mapper = session.getMapper(EmployeeDynamicSQLMapper.class);
        Employee employee = new Employee(null, null, "1", null);
        List<Employee> employees = mapper.getEmployees(employee);
        for (Employee emp :
                employees) {
            System.out.println(emp);
            System.out.println(emp.getSchool());
        }
    }

    @Test
    public void testGetEmpsBYConditionForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeDynamicSQLMapper mapper = session.getMapper(EmployeeDynamicSQLMapper.class);
        List<Employee> employees = mapper.getEmpsBYConditionForeach(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (Employee employee :
                employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetEmpsBYConditionChoose() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeDynamicSQLMapper mapper = session.getMapper(EmployeeDynamicSQLMapper.class);
        Employee employee = new Employee(null, null, "0", null);
        List<Employee> employees = mapper.getEmpsBYConditionChoose(employee);
        employees.forEach(System.out::println);
    }

    @Test
    public void testCeshe() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee emp01 = mapper.getEmpById(1);
        System.out.println(emp01);
        Employee emp02 = mapper.getEmpById(2);
        System.out.println(emp02);
        session.clearCache();
        Employee emp03 = mapper.getEmpById(1);
        System.out.println(emp03);
        System.out.println("emp01 == emp03:" + (emp01 == emp03));
    }

}

