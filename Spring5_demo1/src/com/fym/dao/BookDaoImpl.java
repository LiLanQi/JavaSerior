package com.fym.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements  BookDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;//在配置文件中会直接按照注解直接注入
}
