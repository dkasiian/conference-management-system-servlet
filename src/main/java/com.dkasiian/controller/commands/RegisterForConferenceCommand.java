package com.dkasiian.controller.commands;

import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class RegisterForConferenceCommand extends Command {

    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        long conferenceId = Long.valueOf(request.getParameter("conferenceId"));
        long userId = userService.getUserId(login, locale.toString());

        userService.registerForConference(userId, conferenceId);

        return "redirect:/" +
                request.getSession().getAttribute("role") +
                URL_BUNDLE.getString("url.redirect.conferences");
    }
}
