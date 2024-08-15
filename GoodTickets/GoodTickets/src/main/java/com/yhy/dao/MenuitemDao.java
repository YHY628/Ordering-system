package com.yhy.dao;

import com.yhy.model.MenuItem;

import java.util.List;

public interface MenuitemDao {
    List<MenuItem> selectMenuitem(String id) throws Exception;
    MenuItem selectMenuitemById(String id) throws Exception;
    void insertMenuitem(MenuItem menuitem) throws Exception;
}
