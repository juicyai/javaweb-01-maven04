package com.koki.entity;

import java.sql.*;
import java.util.Arrays;

public class jdbcDemo01 {
    public static void main(String[] args) throws SQLException {

        String url="jdbc:mysql://localhost:3306/javaweb-01-maven01?useSSL=false";
        String user="root";
        String password="Aa123456";
        String sql1="SELECT * FROM student";
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            try(PreparedStatement ps=conn.prepareStatement(sql1)){
                try(ResultSet rs=ps.executeQuery()) {
                    while (rs.next()){
                        System.out.print("name"+rs.getNString("name")+";");
                        System.out.print("age"+rs.getLong("age")+":");
                        System.out.print("address:"+rs.getNString("address")+"\n");
                    }

                }
            }
        }

    }

}
