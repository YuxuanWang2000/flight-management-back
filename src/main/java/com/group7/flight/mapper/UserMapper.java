package com.group7.flight.mapper;


import com.group7.flight.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserById(@Param("id") long id);

    User getUserByUsername(@Param("username") String username);

    int insertUser(User user);
}
