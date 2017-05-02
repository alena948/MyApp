package server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.Iterator;

import static java.lang.System.out;

/**
 * Created by Алёна on 30.04.2017.
 */
public class ImageServer {
    // Create a factory for disk-based file items
    FileItemFactory factory = new DiskFileItemFactory();
    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    try {
        // Parse the request
        List items = upload.parseRequest(request);

        // Process the uploaded items
        Iterator iter = items.iterator();

        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            //handling a normal form-field
            if (item.isFormField()) {
                out.println("Got a form field");
                String name = item.getFieldName();
                String value = item.getString();
                out.print("Name:" + name + ",Value:" + value);
            } else {//handling file loads
                out.println("Not form field");
                String fieldName = item.getFieldName();
                String fileName = item.getName();
                if (fileName != null) {
                    fileName = FilenameUtils.getName(fileName);
                }
                String contentType = item.getContentType();
                boolean isInMemory = item.isInMemory();
                long sizeInBytes = item.getSize();
                out.print("Field Name:" + fieldName
                        + ",File Name:" + fileName);
                out.print("Content Type:" + contentType
                        + ",Is In Memory:" + isInMemory + ",Size:" + sizeInBytes);
                byte[] data = item.get();
                fileName = getServletContext()
                        .getRealPath("/uploadedFiles/" + fileName);
                out.print("File name:" + fileName);
                FileOutputStream fileOutSt = new FileOutputStream(fileName);
                fileOutSt.write(data);
                fileOutSt.close();
                out.print("File Uploaded Successfully!");
            }
        }
    } catch(Exception e)

    {
        out.print("File Uploading Failed!" + e.getMessage());
    }
}
