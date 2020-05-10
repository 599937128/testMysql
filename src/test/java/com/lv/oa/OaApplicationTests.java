package com.lv.oa;

import com.lv.oa.dao.StudentMapper;
import com.lv.oa.dto.Student;
import com.lv.oa.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class OaApplicationTests {


    String URL = "jdbc:mysql://127.0.0.1:3306/oatest?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "root";
    Connection conn;

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    PreparedStatement statement = null;

    @Autowired
    private StudentMapper studentMapper;
    @Test
    void contextLoads() {
        Student student = new Student();
        student.setId("hdusahfuihuih");
        student.setName("张三");
        studentMapper.addStudent(student);
    }

    @Test
    void tests() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {

                insert();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                try {
////                    statement.close();
////                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }

            }
        }
        long over = System.currentTimeMillis();
        System.out.println("business used " + (over - start) + " ms");
    }


    public void insert() throws Exception {

        ResultSet rs = null;
        // 1.加载驱动程序
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            // 2.获得数据库链接
//             conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            UUID id = UUID.randomUUID();
            String name = "中国";
            //预编译
            String sql = " insert delayed into T_STUDENT(ID_,NAME_)\n" +
                    "        values (?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, UUIDUtil.getUuid());
            statement.setString(2, name);
            statement.executeUpdate();

            // 关闭资源
//            rs.close();
        } catch (Exception e) {
            throw e;
        }
    }


    public void insertMapper() throws Exception {
        Student student = new Student();
        student.setId(UUIDUtil.getUuid());
        student.setName("张三");
        studentMapper.addStudent(student);
    }



}
