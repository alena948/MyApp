package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {
    void saveImage(int id, String fileName, String comment, AsyncCallback<Void> async);

    void getImage(int id, AsyncCallback<String> async);

    void delImage(int id, AsyncCallback<Void> async);

    void editComm(int id, String comment, AsyncCallback<Void> async);

    void deleteComm(int id, AsyncCallback<Void> async);
}