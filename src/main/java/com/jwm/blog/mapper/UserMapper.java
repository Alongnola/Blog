package com.jwm.blog.mapper;

import com.jwm.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM blog.t_user WHERE username=#{username} AND password=#{password}")
    public User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
