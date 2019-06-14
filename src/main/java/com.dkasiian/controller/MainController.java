package com.dkasiian.controller;

import com.dkasiian.controller.commands.Command;
import com.dkasiian.controller.commands.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(MainController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command commandFromRequest = CommandFactory.getCommandFromRequest(request);

        String pageUrl = commandFromRequest.process(request);

        if (pageUrl.contains("redirect:")) {
            LOG.info("MainController :: process :: Redirect to page: " + pageUrl);
            response.sendRedirect(request.getContextPath() + pageUrl.replace("redirect:", ""));
        } else {
            LOG.info("MainController :: process :: Forward to page: " + pageUrl);
            request.getRequestDispatcher(pageUrl).forward(request, response);
        }
    }
}
