package com.atzzazz.mapper;

import com.atzzazz.pojo.School;

/**
 * @Description：$
 * @Author： zzazz
 * @Date： 2021/11/10 - 15:11
 */
public interface SchoolMapper {
    School getById(Integer id);

    School getByIdAndEmps(Integer id);
}
