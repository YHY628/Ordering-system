package com.yhy.service;

import com.yhy.entity.po.MenuItem;

import java.util.List;

public interface MenuitemService {
    List<MenuItem> getMenuitems(String id) throws Exception;
    void addMenuitems(MenuItem menuItem) throws Exception;
}
