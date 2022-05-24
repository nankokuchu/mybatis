package com.atzzazz.mapper;

import com.atzzazz.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/8 - 18:43
 */
public interface UserMapper {

    List<Users> getUsersByUserName(String userName);

    Users getUserByMap(Map<String, Object> map);

    Users selectUserById(Integer id);

    List<Users> selectAllUsers();

    Users selectUsersByIdAndUserName(@Param("id") Integer id, @Param("userName") String userName);

    int addUsers(Users users);

    int updateUsers(Users users);

    int deleteUsers(Integer id);
}
