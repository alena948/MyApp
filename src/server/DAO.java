package server;

import java.sql.SQLException;

/**
 * Created by Алёна on 27.04.2017.
 */
public interface DAO {
    void addImage(int id, String name) throws SQLException;
    void deleteImage(int id) throws SQLException;
    String viewImage(int id) throws SQLException;//у каждого изображения свой id
    void deleteComment(int id) throws SQLException;
    void editComment(int id, String newComment) throws SQLException;
}
