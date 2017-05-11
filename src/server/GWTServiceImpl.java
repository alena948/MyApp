package server;

import client.GWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.SQLException;

public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {
    static PhotoDAO photoDAO = new PhotoDAOImpl();
    public static String nameOfViewImage = "";

    @Override
    public void saveImage(int id, String fileName, String comment) {
        try {
            photoDAO.addImage(id, fileName);
            photoDAO.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getImage(int id) {
        String nameOfImage = "";
        try {
            nameOfImage = photoDAO.viewImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameOfViewImage = nameOfImage;
        return nameOfImage;
    }

    @Override
    public void delImage(int id) {
        try {
            photoDAO.deleteImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editComm(int id, String comment) {
        try {
            photoDAO.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComm(int id) {
        try {
            photoDAO.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}