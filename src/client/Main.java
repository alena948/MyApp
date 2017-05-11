package client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class Main implements EntryPoint {

    public static GWTServiceAsync getService() {
        return GWT.create(GWTService.class);//создает доверенного клиента
    }

    public void onModuleLoad() {

        final FormPanel form = new FormPanel();
        form.setAction("/myFormHandler");
        form.setAction(GWT.getModuleBaseURL() + "fileupload");//после FileUpload на сервере

        //для работы с FileUpload widget
        form.setEncoding(FormPanel.ENCODING_MULTIPART);//способ кодирования отправляемой информации
        form.setMethod(FormPanel.METHOD_POST);//метод отправки файла

        //на ней висят все widgets
        final VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        form.setWidget(panel);

        Label idLabel = new Label("Input image's ID:");
        Label fileNameLabel = new Label("Input image's name:");
        Label commentLabel = new Label("Input image's comment:");
        Label methodLabel = new Label("Select the desired method:");

        panel.add(idLabel);
        final TextBox id = new TextBox();
        id.setName("IDTextBoxFormElement");
        panel.add(id);

        panel.add(fileNameLabel);
        final TextBox fileNameBox = new TextBox();
        fileNameBox.setName("FileNameTextBoxFormElement");
        panel.add(fileNameBox);

        panel.add(commentLabel);
        final TextBox comment = new TextBox();
        comment.setName("CommentTextBoxFormElement");
        panel.add(comment);

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

        //создание кнопок
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

        //создание асинхронных callback'ов для обработки результата
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

        //создание и добавлении кнопки submit
        panel.add(new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                form.submit();
            }
        }));

        //добавление обработчика события
        form.addSubmitHandler(new FormPanel.SubmitHandler() {
            public void onSubmit(FormPanel.SubmitEvent event) {
                //событие происходит после того, как форма submitted, используем для проверки
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
                }
            }
        });

        final String pathToImage = "C:\\Users\\Алёна\\.IntelliJIdea2016.3\\system\\gwt\\PhotoAlbum.fa65ba4c\\MyApp.3d7e65c6\\run\\www\\uploadedFiles\\";
        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                //событие проиходит, когда submit прошел успешно; если service вернул ответ в виде текста или html,
                //можем получить результат здесь
                if (isPressAddImageButton[0]) {
                    getService().saveImage(Integer.parseInt(id.getText()), fileNameBox.getText(), comment.getText(), callback);
                    //getService().getImage(Integer.parseInt(id.getText()), callbackString);
                    isPressAddImageButton[0] = false;
                } else if (isPressViewImageButton[0]) {
                    //Window.alert("Path: " + pathToImage + fileNameBox.getText());
                    panel.add(new Image(pathToImage + fileNameBox.getText() + ".jpeg"));
                    panel.add(new Label(comment.getText()));
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