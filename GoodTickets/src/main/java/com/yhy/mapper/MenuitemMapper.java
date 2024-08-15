package com.yhy.mapper;

import com.yhy.entity.po.MenuItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuitemMapper {
    @Select("select * from menuitem where restaurant_id=#{id}")
    List<MenuItem> selectMenuitem(String id) throws Exception;
    @Select("select * from menuitem where id=#{id}")
    MenuItem selectMenuitemById(String id) throws Exception;
    @Insert("insert into menuitem(id,restaurant_id,NAME,description,price,imageUrl) values(UUID(),?,?,?,?,?)")
    void insertMenuitem(MenuItem menuitem) throws Exception;
}
