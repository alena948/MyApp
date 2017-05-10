package server;

import client.GWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.SQLException;

/**
 * Created by Алёна on 24.03.2017.
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {
    static DAO dao = new DBServer();

    @Override
    public void saveImage(int id, String comment) {
        try {
            //Integer int_id = Integer.parseInt(id);
            dao.addImage(id, FileUpload.name);
            dao.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getImage(int id) {
        try {
            dao.viewImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delImage(int id) {
        try {
            dao.deleteImage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editComm(int id, String comment) {
        try {
            dao.editComment(id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComm(int id) {
        try {
            dao.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}