package client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.server.GwtServletBase;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.server.GwtLocaleImpl;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Алёна on 24.03.2017.
 */
public class Main implements EntryPoint {

    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }

    public void onModuleLoad() {

        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction("/myFormHandler");
        form.setAction(GWT.getModuleBaseURL() + "fileupload");//добавлено после работы с FileUpload на сервере

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        form.setEncoding(FormPanel.ENCODING_MULTIPART);//способ кодирования отправляемой информации
        form.setMethod(FormPanel.METHOD_POST);//метод отправки файла

        // Create a panel to hold all of the form widgets.
        VerticalPanel panel = new VerticalPanel();
        form.setWidget(panel);

        Label idLabel = new Label("Input image's ID:");
        Label commentLabel = new Label("Input image's comment:");

        panel.add(idLabel);
        // Create a TextBox, giving it a id name so that it will be submitted.
        final TextBox id = new TextBox();
        id.setName("textBoxFormElement");
        panel.add(id);

        panel.add(commentLabel);
        // Create a TextBox, giving it a comment name so that it will be submitted.
        final TextBox comment = new TextBox();
        comment.setName("textBoxFormElement");
        panel.add(comment);

        // Create a FileUpload widget.
        FileUpload upload = new FileUpload();
        upload.setName("uploadFormElement");
        panel.add(upload);

        // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                //lblServerReply.setText(result);
                Label label = new Label();
                label.setText("Success");
            }

            public void onFailure(Throwable caught) {
                Label label = new Label();
                label.setText("Communication failed");
                //lblServerReply.setText("Communication failed");
            }
        };

        // Add a 'submit' button.
        panel.add(new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                form.submit();
            }
        }));

        // Add an event handler to the form.
        form.addSubmitHandler(new FormPanel.SubmitHandler() {
            public void onSubmit(FormPanel.SubmitEvent event) {
                // This event is fired just before the form is submitted. We can take
                // this opportunity to perform validation.
                if (id.getText().length() == 0) {
                    Window.alert("The text box ID must not be empty");
                    event.cancel();
                }

                if (comment.getText().length() == 0) {
                    Window.alert("The text box comment must not be empty");
                    event.cancel();
                }

            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                // When the form submission is successfully completed, this event is
                // fired. Assuming the service returned a response of type text/html,
                // we can get the result text here (see the FormPanel documentation for
                // further explanation)
                getService().saveImage(id.getText(), comment.getText(), callback);
            }
        });
        RootPanel.get().add(form);
    }
}