package client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import server.GWTServiceImpl;

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
        Label fileNameLabel = new Label("Input image's name:");
        Label commentLabel = new Label("Input image's comment:");
        Label methodLabel = new Label("Select the desired method:");

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

        // Create a FileUpload widget.
        final FileUpload upload = new FileUpload();
        upload.setName("fileUploadFormElement");
        panel.add(upload);

        panel.add(methodLabel);
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(10);
        panel.add(hPanel);

        final boolean[] isPressAddImageButton = {false};
        final boolean[] isPressViewImageButton = {false};
        final boolean[] isPressDeleteImageButton = {false};
        final boolean[] isPressEditCommentButton = {false};
        final boolean[] isPressDeleteCommentButton = {false};

        //Create buttons
        Button addImageButton = new Button("Add image", new ClickHandler() {
            public void onClick(ClickEvent event) {
                isPressAddImageButton[0] = true;
                //Window.alert("Button 'add' pressed!");
            }
        });
        hPanel.add(addImageButton);

        Button viewImageButton = new Button("View image by name", new ClickHandler() {
            public void onClick(ClickEvent event) {
                isPressViewImageButton[0] = true;
                //Window.alert("Button 'view' pressed!");
            }
        });
        hPanel.add(viewImageButton);

        Button deleteImageButton = new Button("Delete image by ID", new ClickHandler() {
            public void onClick(ClickEvent event) {
                isPressDeleteImageButton[0] = true;
                //Window.alert("Button 'delete' pressed!");
            }
        });
        hPanel.add(deleteImageButton);

        Button editCommentButton = new Button("Edit comment by ID", new ClickHandler() {
            public void onClick(ClickEvent event) {
                isPressEditCommentButton[0] = true;
                //Window.alert("Button 'edit comment' pressed!");
            }
        });
        hPanel.add(editCommentButton);

        Button deleteCommentButton = new Button("Delete comment by ID", new ClickHandler() {
            public void onClick(ClickEvent event) {
                isPressDeleteCommentButton[0] = true;
                //Window.alert("Button 'delete commit' pressed!");
            }
        });
        hPanel.add(deleteCommentButton);

        // Create an asynchronous callback to handle the result.
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            public void onSuccess(Void result) {
                Window.alert("Success");
            }

            public void onFailure(Throwable caught) {
                Window.alert("Communication failed");
            }
        };

        final AsyncCallback<String> callbackString = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                Window.alert("Success");
            }

            public void onFailure(Throwable caught) {
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
                if (!isPressViewImageButton[0]) {
                    if (id.getText().length() == 0) {
                        Window.alert("The text box ID must not be empty");
                        event.cancel();
                    }
                }
                if (isPressAddImageButton[0]) {
                    if (fileNameBox.getText().length() == 0) {
                        Window.alert("The text box file name must not be empty");
                        event.cancel();
                    }
                    //if (upload.)
                }
//                if (isPressAddImageButton[0]) {
//                    if (upload.isAttached()) {
//                        Window.alert("Upload widget attached. Name of file: " + fileName);
//                        event.cancel();
//                    }
//                }
            }
        });

        final String pathToImage = "C:\\Users\\Алёна\\.IntelliJIdea2016.3\\system\\gwt\\PhotoAlbum.fa65ba4c\\MyApp.3d7e65c6\\run\\www\\uploadedFiles\\";
        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                // When the form submission is successfully completed, this event is
                // fired. Assuming the service returned a response of type text/html,
                // we can get the result text here (see the FormPanel documentation for
                // further explanation)
                if (isPressAddImageButton[0]) {
                    getService().saveImage(Integer.parseInt(id.getText()), fileNameBox.getText(), comment.getText(), callback);
                    //getService().getImage(Integer.parseInt(id.getText()), callbackString);
                    isPressAddImageButton[0] = false;
                } else if (isPressViewImageButton[0]) {
                    panel.add(new HTML("<body><p><img src = " + pathToImage + fileNameBox.getText() + " alt = ''></p></body>"));
                    Window.alert("Path: " + pathToImage + fileNameBox.getText() + ".jpeg");
                    //panel.add(image);
                    isPressViewImageButton[0] = false;
                } else if (isPressDeleteImageButton[0]) {
                    getService().delImage(Integer.parseInt(id.getText()), callback);
                    isPressDeleteImageButton[0] = false;
                } else if (isPressEditCommentButton[0]) {
                    getService().editComm(Integer.parseInt(id.getText()), comment.getText(), callback);
                    isPressEditCommentButton[0] = false;
                } else if (isPressDeleteCommentButton[0]) {
                    getService().deleteComm(Integer.parseInt(id.getText()), callback);
                    isPressDeleteCommentButton[0] = false;
                }
            }
        });

        RootPanel.get().add(form);
    }
}