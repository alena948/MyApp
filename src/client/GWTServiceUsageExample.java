package client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created by Алёна on 24.03.2017.
 */
public class GWTServiceUsageExample extends VerticalPanel {
    private Label lblServerReply = new Label();
    private TextBox txtUserInput = new TextBox();
    private Button btnSend = new Button("Send to server");

    public GWTServiceUsageExample() {
        add(new Label("Bus schedule: "));
        add(txtUserInput);
        add(btnSend);
        add(lblServerReply);

        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                lblServerReply.setText(result);
            }

            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };

        btnSend.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getService().myMethod(txtUserInput.getText(), callback);
            }
        });
    }

    public static GWTServiceAsync getService() {
        return GWT.create(GWTService.class);
    }
}