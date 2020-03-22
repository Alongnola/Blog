package com.jwm.blog.service.Impl;

import com.jwm.blog.mapper.TagMapper;
import com.jwm.blog.pojo.Tag;
import com.jwm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper dao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return dao.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        return dao.getTagById(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return dao.getTagByName(name);
    }

    @Transactional
    @Override
    public List<Tag> getTagsForPage() {
        return dao.getTags();
    }

    @Transactional
    @Override
    public int updateTag(Long id, Tag tag) {
        return dao.updateTag(id, tag);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return dao.deleteTag(id);
    }

    @Transactional
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(dao.getTagById(aLong));
        }
        return tags;

    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public List<Tag> getTagsByBlog() {
        return dao.getTagsByBlog();
    }
}
