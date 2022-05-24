package com.atzzazz;

import com.atzzazz.mapper.SchoolMapper;
import com.atzzazz.pojo.School;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 16:00
 */
public class SchoolMapperTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(resourceAsStream);
        return  sqlSessionFactory;
    }

    @Test
    public void testGetByIdAndEmps() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SchoolMapper mapper = session.getMapper(SchoolMapper.class);
        School school = mapper.getByIdAndEmps(1);
        System.out.println(school);
        System.out.println(school.getEmps());
    }
}
