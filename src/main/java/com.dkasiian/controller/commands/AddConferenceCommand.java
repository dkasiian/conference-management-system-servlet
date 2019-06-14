package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.FormValidationUtil;
import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.services.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddConferenceCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(AddConferenceCommand.class);
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);
        ResourceBundle regexBundle = ResourceBundle.getBundle(ResourceName.REGEXP_BUNDLE, locale);

        if (FormValidationUtil.isFormDataValid(request, regexBundle, messageBundle))
            return URL_BUNDLE.getString("url.forward.add.conference");

        LocalDateTime conferenceDateTime = LocalDateTime.parse(
                request.getParameter("datetime").replace(" ", "T"));
        if (conferenceDateTime.compareTo(LocalDateTime.now()) < 0) {
            request.setAttribute("incorrect_datetime", messageBundle.getString("html.validation.incorrect.datetime.early.date"));
            return URL_BUNDLE.getString("url.forward.add.conference");
        }

        // Update
        if (request.getParameter("conferenceId") != null && !request.getParameter("conferenceId").isEmpty()) {
            conferenceService.update(
                    new ConferenceDto.Builder()
                            .setId(Long.valueOf(request.getParameter("conferenceId")))
                            .setNameEn(request.getParameter("conferenceNameEn"))
                            .setNameUa(request.getParameter("conferenceNameUa"))
                            .setLocationEn(request.getParameter("locationEn"))
                            .setLocationUa(request.getParameter("locationUa"))
                            .setDateTime(conferenceDateTime)
                            .build()
            );
            return "redirect:/" +
                    request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences");
        }

        // Add new
        if (!conferenceService.add(
                new ConferenceDto.Builder()
                        .setNameEn(request.getParameter("conferenceNameEn"))
                        .setNameUa(request.getParameter("conferenceNameUa"))
                        .setLocationEn(request.getParameter("locationEn"))
                        .setLocationUa(request.getParameter("locationUa"))
                        .setDateTime(conferenceDateTime)
                        .build()
        )) {
            throw new RuntimeException();
        }

        return "redirect:/" +
                request.getSession().getAttribute("role") +
                URL_BUNDLE.getString("url.redirect.conferences");
    }
}

