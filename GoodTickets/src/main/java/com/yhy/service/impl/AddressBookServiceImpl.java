package com.yhy.service.impl;

import com.yhy.entity.po.AddressBook;
import com.yhy.service.AddressBookService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        return Collections.emptyList();
    }

    @Override
    public void save(AddressBook addressBook) {

    }

    @Override
    public AddressBook getById(Long id) {
        return null;
    }

    @Override
    public void update(AddressBook addressBook) {

    }

    @Override
    public void setDefault(AddressBook addressBook) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
