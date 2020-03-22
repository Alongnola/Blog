package com.jwm.blog.service;

import com.jwm.blog.pojo.Type;

import java.util.List;

public interface TypeService {
    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getTypesForPage();

    int updateType(Long id,Type type);

    int deleteType(Long id);

    List<Type> getTypesByBlog();
}
