package com.hhf.alaska.spring.boot.starter.durid.demo.dao;

import com.hhf.alaska.spring.boot.starter.durid.demo.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author huang hong fei
 * @date 2019/6/17
 * @description
 **/
public class DatasourceTests extends BaseTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void getType(){
        try {
            System.err.println(dataSource.getConnection().getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
