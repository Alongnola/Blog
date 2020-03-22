package com.jwm.blog.service;

import com.jwm.blog.pojo.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getTagsForPage();

    int updateTag(Long id, Tag tag);

    int deleteTag(Long id);

    List<Tag> getTagByString(String text);

    List<Tag> getTagsByBlog();

}
