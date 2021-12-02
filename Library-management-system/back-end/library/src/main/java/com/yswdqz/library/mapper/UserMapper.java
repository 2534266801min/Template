package com.yswdqz.library.mapper;

import com.yswdqz.library.model.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from Administrator" +
            " where name =#{username}" +
            " and password = #{password}")
    Administrator loginByAdmin(String username, String password);

    Integer loginByReader(String username,String password);
}
