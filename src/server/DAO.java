package server;

import java.sql.SQLException;

/**
 * Created by Алёна on 27.04.2017.
 */
public interface DAO {
    void addImage(int id, String name) throws SQLException;//будет возвращать id изображения, чтобы его по нему потом ?открывать?
    void deleteImage(int id) throws SQLException;
    String viewImage(int id) throws SQLException;//у каждого изображения свой id
    void deleteComment(int id) throws SQLException;
    void editComment(String newComment, int id) throws SQLException;
}
