package server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FileUpload extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            String fileName = "";
            boolean isEmptyID = false;

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {//может ли представить собой простое поле формы
                    System.out.println();
                    System.out.println("Got a form field...");
                    String name = item.getFieldName();//возвращает имя поля в форме
                    String value = item.getString();//возвращает содержимое файла в виде строки,
                    // используя кодировку по умолчанию
                    if (name.equals("IDTextBoxFormElement") && value.equals(""))
                        isEmptyID = true;
                    if (name.equals("FileNameTextBoxFormElement") && (!value.equals("")) && !isEmptyID) {
                        fileName = value + ".jpeg";
                    } else if (name.equals("FileNameTextBoxFormElement") && value.equals("") || isEmptyID)
                        break;
                        // throw new Exception("There's no adding file!");
                    System.out.print("Name:" + name + ",Value:" + value);
                } else {//обработка загрузки файла
                    System.out.println();
                    System.out.println("Loading image...");
                    String fieldName = item.getFieldName();
                    if (fileName != null) {
                        fileName = FilenameUtils.getName(fileName);
                    }
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    System.out.println("Field Name:" + fieldName
                            + ",File Name:" + fileName);
                    System.out.println("Content Type:" + contentType
                            + ",Is In Memory:" + isInMemory + ",Size:" + sizeInBytes);
                    byte[] data = item.get();
                    fileName = getServletContext()
                            .getRealPath("/uploadedFiles/" + fileName);
                    System.out.println("File name:" + fileName);
                    FileOutputStream fileOutSt = new FileOutputStream(fileName);
                    fileOutSt.write(data);
                    fileOutSt.close();
                    System.out.println("File Uploaded Successfully!");
                }
            }
        } catch (Exception e) {
            System.out.println("File uploading failed!");
            e.printStackTrace();
        }
    }
}
