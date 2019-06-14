package com.dkasiian.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(LogOutCommand.class);

    @Override
    public String process(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            LOG.info("LogOutCommand :: process :: User with login: " + session.getAttribute("login") + " logged out");
            session.getServletContext().removeAttribute((String) session.getAttribute("login"));
            session.invalidate();
        }
        return  "redirect:" + URL_BUNDLE.getString("url.redirect.index");
    }
}
