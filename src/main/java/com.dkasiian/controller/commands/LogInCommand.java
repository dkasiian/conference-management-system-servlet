package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogInCommand extends Command {

    private final static Logger LOG = LogManager.getLogger(LogInCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Locale locale = (Locale) request.getSession().getAttribute("locale");

        LOG.trace("LogInCommand :: process :: locale " + locale);

        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        if (login == null || password == null) {
            LOG.trace("LogInCommand :: process :: Login or password is null");
            if (request.getSession().getAttribute("isSuccessRegistration") != null) {
                request.setAttribute("isSuccessRegistration", true);
                request.removeAttribute("isSuccessRegistration");
            }
            return URL_BUNDLE.getString("url.forward.login");
        }
        if (login.isEmpty()) {
            LOG.info("LogInCommand :: process :: Login is empty");
            request.setAttribute("incorrect_login", messageBundle.getString("html.validation.empty.login"));
            return URL_BUNDLE.getString("url.forward.login");
        }
        if (password.isEmpty()) {
            LOG.info("LogInCommand :: process :: Password is empty");
            request.setAttribute("incorrect_password", messageBundle.getString("html.validation.empty.password"));
            return URL_BUNDLE.getString("url.forward.login");
        }
        if (!userService.isUserExist(login)) {
            LOG.info("LogInCommand :: process :: User with such login: " + login + ", dose not exist");
            request.setAttribute("incorrect_login", messageBundle.getString("html.validation.no.registered.user.with.login"));
            return URL_BUNDLE.getString("url.forward.login");
        }
        if (userService.checkPassword(login, password)) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", userService.getRole(login).name().toLowerCase());
            request.getSession().getServletContext().setAttribute(login, request.getSession());
            LOG.info("LogInCommand :: process :: User with login: " + login + ", signed in");
            return URL_BUNDLE.getString("url.redirect.root");
        } else {
            LOG.warn("LogInCommand :: process :: Non-existent password");
            request.setAttribute("incorrect_password", messageBundle.getString("html.validation.fail.check.password"));
        }

        return URL_BUNDLE.getString("url.forward.login");
    }
}
