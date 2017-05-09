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
    public void saveImage(String id, String comment) {
        try {
            Integer int_id = Integer.parseInt(id);
            dao.addImage(int_id, FileUpload.name);
            dao.editComment(int_id, comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}