package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Алёна on 24.03.2017.
 */
public interface GWTServiceAsync {
    void saveImage(String id, String comment, AsyncCallback<String> async);
}