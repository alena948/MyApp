package client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by Алёна on 24.03.2017.
 */
@RemoteServiceRelativePath("sampleservice/gwtservice")//связывает service с путем по умолчанию относительно модуля базового URL.
public interface GWTService extends RemoteService {
    public String myMethod(String s);
}