package server;

import java.sql.*;

/**
 * Created by Алёна on 24.04.2017.
 */
public class DBServer implements DAO {
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

    @Override
    public void addImage(int id, String name) throws SQLException {
        getDBConnection();
        String sql = "INSERT INTO images(id,name) VALUES (" + id + ", '" + name + "')";
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.close();
        getDBConnection().close();
    }

    @Override
    public void deleteImage(int id) throws SQLException {
        getDBConnection();
        String sql = "DELETE FROM images where id = " + id;
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.close();
        getDBConnection().close();
    }

    @Override
    public String viewImage(int id) throws SQLException {
        getDBConnection();
        String sql = "SELECT * FROM images WHERE id = " + id;
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String nameOfImage = "";
        if (resultSet != null) {
            nameOfImage = resultSet.getString("name");
        }
        resultSet.close();
        getDBConnection().close();
        return nameOfImage;
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        getDBConnection();
        String sql = "UPDATE images SET comment = NULL WHERE id = " + id;
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.close();
        getDBConnection().close();
    }

    @Override
    public void editComment(String newComment, int id) throws SQLException {
        getDBConnection();
        String sql = "UPDATE images SET comment = '" + newComment + "' WHERE id = " + id;
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.close();
        getDBConnection().close();
    }
}
