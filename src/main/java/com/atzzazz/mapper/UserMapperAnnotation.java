package com.atzzazz.mapper;

import com.atzzazz.pojo.Users;
import org.apache.ibatis.annotations.Select;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/9 - 14:57
 */
public interface UserMapperAnnotation {
    
    @Select("select * from users where id=#{id}")
    Users selectUserById(Integer id);
}
