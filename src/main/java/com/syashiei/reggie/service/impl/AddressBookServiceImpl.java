package com.syashiei.reggie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.entity.AddressBook;
import com.syashiei.reggie.mapper.AddressBookMapper;
import com.syashiei.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
