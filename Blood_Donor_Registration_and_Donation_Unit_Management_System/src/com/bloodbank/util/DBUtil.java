package com.bloodbank.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
        "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "bloodbank";
    private static final String PASSWORD = "bloodbank";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
