package com.atzzazz;

import com.atzzazz.mapper.UserMapper;
import com.atzzazz.mapper.UserMapperAnnotation;
import com.atzzazz.pojo.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/8 - 17:17
 */
public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void testFirst() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        Users user = session.selectOne("com.atzzazz.pojo.UsersMapper.selectUser", 1);
        System.out.println(user);
    }

    @Test
    public void testByInterface() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            Users users = mapper.selectUserById(1);
            System.out.println(users);
        } finally {
            session.close();
        }
    }

    @Test
    public void testInterfaceAnnotation() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapperAnnotation mapper = session.getMapper(UserMapperAnnotation.class);
            Users users = mapper.selectUserById(1);
            System.out.println(users);
        } finally {
            session.close();
        }
    }

    @Test
    public void testAllSelect() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<Users> users = mapper.selectAllUsers();
        System.out.println(users.toString());
        session.close();
    }

    @Test
    public void testAdd() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Users users = new Users(null,
                "东方不败",
                "dongfangbubai",
                "葵花宝典",
                "明教",
                123456789);
        int i = mapper.addUsers(users);
        System.out.println(users);
        session.commit();
        System.out.println(i);
        session.close();
    }

    @Test
    public void testUpdateUsers() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Users users = mapper.selectUserById(5);
        users.setNation("火星");
        int i = mapper.updateUsers(users);
        session.commit();
        System.out.println(i);
        session.close();
    }

    @Test
    public void testDeleteUsers() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.deleteUsers(3);
        session.commit();
        System.out.println(i);
        session.close();
    }

    @Test
    public void testSelectUsersByIdAndUserName() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Users user = mapper.selectUsersByIdAndUserName(5, "张三丰");
        System.out.println(user);
        session.close();
    }

    @Test
    public void testGetUserByMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 5);
        map.put("userName", "张三丰");
        Users userByMap = mapper.getUserByMap(map);
        System.out.println(userByMap);
        session.close();
    }

    @Test
    public void testGetUsersByUserName(){

    }
}
