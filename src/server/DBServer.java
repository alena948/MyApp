package server;

import java.sql.*;

/**
 * Created by Алёна on 24.04.2017.
 */
public class DBServer {
    public static final String JDBC_DRIVER = "org.post-gresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/database";//name of DB
    public static final String USER = "postgres";
    public static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //System.out.println("Open connection");
            String sql = "select * from dual";
            Statement statement = connection.createStatement();
            //System.out.println("Create statement");
            ResultSet resultSet = statement.executeQuery(sql);
            //System.out.println("Execute query");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                Date birth_date = resultSet.getDate("birth_date");
                //System.out.println("id -> " + id);
                //System.out.println("first_name -> " + first_name);
                //System.out.println("last_name -> " + last_name);
                //System.out.println("birth_date -> " + birth_date);
            }
            resultSet.close();
        }
        catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

}
