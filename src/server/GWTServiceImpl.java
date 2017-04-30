package server;

import client.GWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by Алёна on 24.03.2017.
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    public String myMethod(String s) {
        return "Server says: " + s;
    }
}