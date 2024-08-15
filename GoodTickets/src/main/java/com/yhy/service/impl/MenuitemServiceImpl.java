package com.yhy.service.impl;

import com.yhy.dao.impl.MenuitemDaoImpl;
import com.yhy.model.MenuItem;
import com.yhy.service.MenuitemService;
import lombok.Setter;

import java.util.List;

@Setter
public class MenuitemServiceImpl implements MenuitemService {
    private MenuitemDaoImpl dao;


    @Override
    public List<MenuItem> getMenuitems(String id) throws Exception {

        System.out.println("55555");
        return dao.selectMenuitem(id);
    }

    @Override
    public void addMenuitems(MenuItem menuItem) throws Exception {
        dao.insertMenuitem(menuItem);
    }
}
