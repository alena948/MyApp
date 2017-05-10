package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Алёна on 24.03.2017.
 */
public interface GWTServiceAsync {
    void saveImage(int id, String comment, AsyncCallback<Void> async);

    void getImage(int id, AsyncCallback<Void> async);

    void delImage(int id, AsyncCallback<Void> async);

    void editComm(int id, String comment, AsyncCallback<Void> async);

    void deleteComm(int id, AsyncCallback<Void> async);

}