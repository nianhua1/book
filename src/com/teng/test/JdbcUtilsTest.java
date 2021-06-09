package com.teng.test;

import com.teng.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author
 * @create 2021-04-17 16:32
 */
public class JdbcUtilsTest {
    @Test
    public void TestJdbc(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println("conn = " + conn);
    }
}
