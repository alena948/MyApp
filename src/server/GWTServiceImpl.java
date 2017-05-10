package server;

import client.GWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.SQLException;

/**
 * Created by Алёна on 24.03.2017.
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {
    static PhotoDAO photoDao = new PhotoDAOImpl();

    @Override
    public void saveImage(int id, String fileName, String comment) {
        try {
            //Integer int_id = Integer.parseInt(id);
            photoDao.addImage(id, fileName);
            photoDao.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getImage(int id) {
        String nameOfImage = "";
        try {
            nameOfImage = photoDao.viewImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameOfImage;
    }

    @Override
    public void delImage(int id) {
        try {
            photoDao.deleteImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editComm(int id, String comment) {
        try {
            photoDao.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComm(int id) {
        try {
            photoDao.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}