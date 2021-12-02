package com.yswdqz.library.mapper;

import com.yswdqz.library.model.Administrator;
import com.yswdqz.library.model.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from Administrator" +
            " where accountNumber =#{accountNumber}" +
            " and password = #{password}")
    Administrator loginByAdmin(String accountNumber, String password);

    @Select("select * from Reader" +
            " where accountnumber =#{accountNumber}" +
            " and password = #{password}")
    Reader loginByReader(String accountNumber, String password);
}
