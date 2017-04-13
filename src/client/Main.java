package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by Алёна on 24.03.2017.
 */
public class Main implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(new GWTServiceUsageExample());
    }
}
