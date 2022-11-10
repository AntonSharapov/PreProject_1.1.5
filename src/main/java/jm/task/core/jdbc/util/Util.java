package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dr_URL = "jdbc:mysql://localhost:3306/pr_bd";
    private static final String dr_Username = "root";
    private static final String dr_password = "lolypop7991_";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dr_URL, dr_Username, dr_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
