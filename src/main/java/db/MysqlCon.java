package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ywc on 2017/5/13.
 */
public enum MysqlCon {
    ;
    private static Connection conn;
    private static String dbName = "duba";
    private static String userName = "root";
    private static String password = "root";

    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=" + userName + "&password=" + password;
                conn = DriverManager.getConnection(url);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
