package com.yhy.controller;

import com.yhy.entity.po.AddressBook;
import com.yhy.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

public class AddressBookController {
@Resource
private AddressBookService addressService;




    @PostMapping("/address")
    public ResponseEntity<AddressBook> addAddress(@RequestBody AddressBook addressBook) {
        AddressBook newAddress = addressService.addAddress(addressBook);
        return ResponseEntity.ok(newAddress);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressBook> updateAddress(@PathVariable Long id, @RequestBody AddressBook addressBook) {
        addressBook.setId(id);
        AddressBook updatedAddress = addressService.updateAddress(addressBook);
        return ResponseEntity.ok(updatedAddress);
    }


    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressBook>> listAddresses(@RequestParam Long userId) {
        List<AddressBook> addresses = addressService.listAddresses(userId);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/address/default")
    public ResponseEntity<AddressBook> getDefaultAddress(@RequestParam Long userId) {
        AddressBook defaultAddress = addressService.getDefaultAddress(userId);
        return ResponseEntity.ok(defaultAddress);
    }



}
