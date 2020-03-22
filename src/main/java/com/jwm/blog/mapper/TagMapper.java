package com.jwm.blog.mapper;

import com.jwm.blog.pojo.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    @Insert("INSERT INTO blog.t_tag VALUES (#{id},#{name})")
    int saveTag(Tag type);

    @Select("SELECT * FROM blog.t_tag WHERE id = #{id}")
    Tag getTagById(Long id);

    @Select("SELECT * FROM blog.t_tag WHERE name = #{name}")
    Tag getTagByName(String name);

    @Select("SELECT * FROM blog.t_tag")
    List<Tag> getTags();

    @Delete("DELETE FROM blog.t_tag WHERE id = #{id}")
    int deleteTag(Long id);

    @Update("UPDATE blog.t_tag SET name = #{tag.name} WHERE id = #{id}")
    int updateTag(@Param("id") Long id, @Param("tag") Tag tag);

    List<Tag> getTagsByBlog();
}
