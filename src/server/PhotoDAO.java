package server;

import java.sql.SQLException;

public interface PhotoDAO {
    void addImage(int id, String name) throws SQLException;
    void deleteImage(int id) throws SQLException;
    String viewImage(int id) throws SQLException;//у каждого изображения свой id
    void deleteComment(int id) throws SQLException;
    void editComment(int id, String newComment) throws SQLException;
}
