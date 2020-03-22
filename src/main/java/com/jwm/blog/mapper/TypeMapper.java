package com.jwm.blog.mapper;

import com.github.pagehelper.Page;
import com.jwm.blog.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    @Insert("INSERT INTO blog.t_type VALUES (#{id},#{name})")
    int saveType(Type type);

    @Select("SELECT * FROM blog.t_type WHERE id = #{id}")
    Type getTypeById(Long id);

    @Select("SELECT * FROM blog.t_type WHERE name = #{name}")
    Type getTypeByName(String name);

    @Select("SELECT * FROM blog.t_type")
    List<Type> getTypes();

    @Delete("DELETE FROM blog.t_type WHERE id = #{id}")
    int deleteType(Long id);

    @Update("UPDATE blog.t_type SET name = #{type.name} WHERE id = #{id}")
    int updateType(@Param("id") Long id, @Param("type") Type type);

    List<Type> getTypesByBlog();
}
