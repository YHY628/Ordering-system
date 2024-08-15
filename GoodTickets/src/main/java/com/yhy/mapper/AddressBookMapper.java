package com.yhy.mapper;

import com.yhy.entity.po.AddressBook;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressBookMapper {
        /**
         * 条件查询
         */
        public List<AddressBook> list(AddressBook addressBook);

        /**
         * 新增
         */
        public void insert(AddressBook addressBook);

        /**
         * 根据id查询
         */
        public AddressBook getById(Long id);

        /**
         * 根据id修改
         */
        public void update(AddressBook addressBook);

        /**
         * 根据 用户id修改 是否默认地址
         */
        public void updateIsDefaultByUserId(AddressBook addressBook);

        /**
         * 根据id删除地址
         */
        public void deleteById(Long id);

}
