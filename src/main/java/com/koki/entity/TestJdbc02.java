package com.koki.entity;
import org.junit.*;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class TestJdbc02 {
    String url="jdbc:mysql://localhost:3306/javaweb-01-maven01?useSSL=false";
    String user="root";
    String password="Aa123456";
    @Test
    public void test01(){

        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            String sql1="UPDATE account SET money=money-100 WHERE username='Alice'";
            String sql2="UPDATE account SET money=money+100 WHERE username='Phil'";
//            sql3="SELECT money from account WHERE username='Alice'";
//            sql4="SELECT money from account WHERE username='Phil'";
            conn.prepareStatement(sql1).executeUpdate();
            conn.prepareStatement(sql2).executeUpdate();
            conn.commit();
        }catch (ClassNotFoundException | SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    @Test
    public void test02() throws SQLException {
        try(Connection conn=DriverManager.getConnection(url,user,password)){
            String sql3="SELECT money from account WHERE username='Alice'";
            String sql4="SELECT money from account WHERE username='Phil'";
            try(PreparedStatement ps1=conn.prepareStatement(sql3);PreparedStatement ps2=conn.prepareStatement(sql4)){
                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2=ps2.executeQuery();
                while (rs1.next()){
                    System.out.println(rs1.getLong("money"));
                    assertEquals(rs1.getLong("money"),900);
                }
                while (rs2.next()){
                    System.out.println(rs2.getLong("money"));
                    assertEquals(rs2.getLong("money"),1100);
                }
            }
        }
    }

}
