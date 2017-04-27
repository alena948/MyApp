package server;

/**
 * Created by Алёна on 27.04.2017.
 */
public interface DAO {
    int addImage(String string);//будет возвращать id изображения, чтобы его по нему потом ?открывать?
    void deleteImage(String string);
    String viewImage(int id);//у каждого изображения свой id
    void createComment(String string, int id);//по id прикрепляется comment
    void deleteComment(int id);
    void editComment(String string, int id);
}
