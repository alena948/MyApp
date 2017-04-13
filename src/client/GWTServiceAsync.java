package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Алёна on 24.03.2017.
 */
public interface GWTServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);
}