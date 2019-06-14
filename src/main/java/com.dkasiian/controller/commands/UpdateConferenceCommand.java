package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.services.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateConferenceCommand extends Command {

    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        if (request.getParameter("conferenceId") == null)
            return "redirect:/" + request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences");

        ConferenceDto conference =
                conferenceService.getConferenceDtoById(Long.valueOf(request.getParameter("conferenceId")));

        if (conference == null)
            throw new RuntimeException((messages.getString("error.conference.not.found")));

        request.setAttribute("conferenceId", conference.getId());
        request.setAttribute("conferenceNameEn", conference.getNameEn());
        request.setAttribute("conferenceNameUa", conference.getNameUa());
        request.setAttribute("datetime", conference.getDateTime());
        request.setAttribute("locationEn", conference.getLocationEn());
        request.setAttribute("locationUa", conference.getLocationUa());

        return URL_BUNDLE.getString("url.forward.add.conference");
    }
}
