package com.fym.service;

import com.fym.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //注入Dao,按照类型注入
    @Autowired
    private BookDao bookDao;
}
