package com.hz.mapper;

import com.hz.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_name=#{uname} and user_pwd=#{upwd}")
    public User login(
            @Param("uname") String uname,
            @Param("upwd") String upwd
    );
}
