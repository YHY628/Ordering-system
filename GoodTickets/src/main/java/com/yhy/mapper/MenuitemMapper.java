package com.yhy.mapper;

import com.yhy.model.MenuItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuitemMapper {
    @Select("select * from menuitem where restaurant_id=#{id}")
    List<MenuItem> selectMenuitem(String id) throws Exception;
    @Select("select * from menuitem where id=#{id}")
    MenuItem selectMenuitemById(String id) throws Exception;
    @Insert("insert into menuitem(id,restaurant_id,NAME,description,price,imageUrl) values(UUID(),?,?,?,?,?)")
    void insertMenuitem(MenuItem menuitem) throws Exception;
}
