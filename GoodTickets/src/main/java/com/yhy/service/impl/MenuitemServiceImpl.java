package com.yhy.service.impl;

import com.yhy.mapper.MenuitemMapper;
import com.yhy.entity.po.MenuItem;
import com.yhy.service.MenuitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuitemServiceImpl implements MenuitemService {
    @Autowired
    private MenuitemMapper menuitemMapper ;

    @Override
    public List<MenuItem> getMenuitems(String id) throws Exception {

        return menuitemMapper.selectMenuitem(id);
    }

    @Override
    public void addMenuitems(MenuItem menuItem) throws Exception {
        menuitemMapper.insertMenuitem(menuItem);
    }
}
