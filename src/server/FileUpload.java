package server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Алёна on 02.05.2017.
 */
public class FileUpload extends HttpServlet {

    //public static String name = "start name";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            List items = upload.parseRequest(request);
            //FileItemIterator iter = upload.getItemIterator(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    Window.alert("Got a form field");
                    String name = item.getFieldName();
                    String value = item.getString();
                } else {//handling file loads
                    Window.alert("Not form field");
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    if (fileName != null) {
                        fileName = FilenameUtils.getName(fileName);
                    }
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    Window.alert("Field Name:" + fieldName
                            + ",File Name:" + fileName);
                    Window.alert("Content Type:" + contentType
                            + ",Is In Memory:" + isInMemory + ",Size:" + sizeInBytes);
                    byte[] data = item.get();
                    fileName = getServletContext()
                            .getRealPath("/uploadedFiles/" + fileName);
                    Window.alert("File name:" + fileName);
                    FileOutputStream fileOutSt = new FileOutputStream(fileName);
                    fileOutSt.write(data);
                    fileOutSt.close();
                    Window.alert("File Uploaded Successfully!");
                }
            }
                /*//name = item.getName();
                //name = item.getFieldName();
                InputStream stream = item.openStream();


                // Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                }

                int maxFileSize = 10 * (1024 * 1024); //10 megs max
                if (out.size() > maxFileSize) {
                    throw new RuntimeException("File is > than " + maxFileSize);
                }
            }*/
        } catch (Exception e) {
            Window.alert("File uploading failed!");
            //throw new RuntimeException(e);
        }

    }
}
