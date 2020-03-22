package com.jwm.blog.service.Impl;

import com.github.pagehelper.Page;
import com.jwm.blog.mapper.TypeMapper;
import com.jwm.blog.pojo.Type;
import com.jwm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper dao;

    //事务注解
    @Transactional
    @Override
    public int saveType(Type type) {
        return dao.saveType(type);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return dao.getTypeById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return dao.getTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> getTypesForPage() {
        return dao.getTypes();
    }

    @Transactional
    @Override
    public int updateType(Long id, Type type) {
        return dao.updateType(id, type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return dao.deleteType(id);
    }

    @Transactional

    @Override
    public List<Type> getTypesByBlog() {
        return dao.getTypesByBlog();
    }
}
