package server;

import java.sql.*;

public class PhotoDAOImpl implements PhotoDAO {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";//name of DB in the end
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

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

    private void execSql(String sql) throws SQLException {
        getDBConnection();
        Statement statement = getDBConnection().createStatement();
        statement.execute(sql);
        //ResultSet resultSet = statement.executeQuery(sql);
        //resultSet.close();
        getDBConnection().close();
    }

    @Override
    public void addImage(int id, String name) throws SQLException {
        String sql = "INSERT INTO images(id,name) VALUES (" + id + ", '" + name + "')";
        execSql(sql);
    }

    @Override
    public void deleteImage(int id) throws SQLException {
        String sql = "DELETE FROM images where id = " + id;
        execSql(sql);
    }

    @Override
    public String viewImage(int id) throws SQLException {
        String sql = "SELECT * FROM images WHERE id = " + id;
        getDBConnection();
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
        String sql = "UPDATE images SET comment = NULL WHERE id = " + id;
        execSql(sql);
    }

    @Override
    public void editComment(int id, String newComment) throws SQLException {
        String sql = "UPDATE images SET comment = '" + newComment + "' WHERE id = " + id;
        execSql(sql);
    }
}
