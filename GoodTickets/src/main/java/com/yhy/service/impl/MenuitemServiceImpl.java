package com.yhy.service.impl;

import com.yhy.mapper.MenuitemMapper;
import com.yhy.model.MenuItem;
import com.yhy.service.MenuitemService;
import com.yhy.utils.MyBatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class MenuitemServiceImpl implements MenuitemService {
    private SqlSession sqlSession= MyBatisSqlSessionFactory.getSqlSession();

    private MenuitemMapper menuitemMapper = sqlSession.getMapper(MenuitemMapper.class);

    @Override
    public List<MenuItem> getMenuitems(String id) throws Exception {

        return menuitemMapper.selectMenuitem(id);
    }

    @Override
    public void addMenuitems(MenuItem menuItem) throws Exception {
        menuitemMapper.insertMenuitem(menuItem);
    }
}
