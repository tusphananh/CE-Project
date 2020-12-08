package Main.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    private static String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456789";

    public static boolean checkJDBC() {
        return getConnection(DB_URL,USER_NAME,PASSWORD) != null ;
    }

    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(){
        return getConnection(DB_URL,USER_NAME,PASSWORD);
    }
}
