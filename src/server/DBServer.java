package server;

import java.sql.*;

/**
 * Created by Алёна on 24.04.2017.
 */
public class DBServer {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/database";//name of DB
    public static final String USER = "postgres";
    public static final String PASSWORD = "";

    private static Connection getDBConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /*public int addImage(String string){
        return 0;
    }*/

}
