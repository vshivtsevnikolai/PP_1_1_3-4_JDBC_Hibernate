package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/BDfirst";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "kbTFDydmuBn321";
    public static Connection getConnection () {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {}
        return connection;
    }
}
