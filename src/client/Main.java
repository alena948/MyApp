package client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
        final VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        form.setWidget(panel);

        Label idLabel = new Label("Input image's ID:");
        final Label fileNameLabel = new Label("Input image's name:");
        Label commentLabel = new Label("Input image's comment:");

        panel.add(idLabel);
        // Create a TextBox, giving it a id name so that it will be submitted.
        final TextBox id = new TextBox();
        id.setName("IDTextBoxFormElement");
        panel.add(id);

        panel.add(fileNameLabel);
        // Create a TextBox, giving it a fileName name so that it will be submitted.
        final TextBox fileNameBox = new TextBox();
        fileNameBox.setName("FileNameTextBoxFormElement");
        panel.add(fileNameBox);

        panel.add(commentLabel);
        // Create a TextBox, giving it a comment name so that it will be submitted.
        final TextBox comment = new TextBox();
        comment.setName("CommentTextBoxFormElement");
        panel.add(comment);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(10);
        panel.add(hPanel);
        //form.setWidget(hPanel);

        Button normalButton = new Button("Normal button", new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("Button pressed!");
            }
        });
        //normalButton.ensureDebugId("cwBasicButton-normal");
        hPanel.add(normalButton);

        // Create a FileUpload widget.
        FileUpload upload = new FileUpload();
        final String fileName = upload.getFilename();
        upload.setName(fileName);
        panel.add(upload);

        // Create an asynchronous callback to handle the result.
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            public void onSuccess(Void result) {
                /*Label label = new Label();
                label.setText("Success");
                panel.add(label);*/
                Window.alert("Success");
            }

            public void onFailure(Throwable caught) {
                /*Label label = new Label();
                label.setText("Communication failed");
                panel.add(label);*/
                Window.alert("Communication failed");
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

                if (fileNameBox.getText().length() == 0) {
                    Window.alert("The text box file name must not be empty");
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
                getService().saveImage(Integer.parseInt(id.getText()), fileNameBox.getText(), comment.getText(), callback);
                //getService().delImage(Integer.parseInt(id.getText()), callback);
            }
        });

        RootPanel.get().add(form);
    }
}