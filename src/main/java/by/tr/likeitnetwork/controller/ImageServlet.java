package by.tr.likeitnetwork.controller;


import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ImageServlet extends HttpServlet {
    private final String DIRECTORY = "/img";
    private String PATH;

    private final Logger logger = LogManager.getLogger(ImageServlet.class);

    @Override
    public void init() throws ServletException {
        PATH = getServletContext().getRealPath(DIRECTORY);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try{
            List<FileItem> fileItems = sf.parseRequest(request);
            FileItem fileItem = fileItems.get(0);




            String name = fileItem.getName();

            if (ServiceFactory.getInstance().getUserService().changeImg((Integer)request.getSession().getAttribute(AttributeKey.ID), name)){
                fileItem.write(new File(PATH + File.separator + name));
            }




            //File old= new File(PATH + File.separator + file);
            //old.delete();
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        } catch (Exception ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String file = request.getParameter("file");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(PATH + File.separator + file));
        byte[] content = new byte[in.available()];
        in.read(content);
        in.close();
        response.getOutputStream().write(content);
    }

}
