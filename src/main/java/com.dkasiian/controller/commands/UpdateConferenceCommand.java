package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.services.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateConferenceCommand implements Command {

    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request){

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        Pattern pattern = Pattern.compile("(past-conferences|future-conferences|conferences)");
        Matcher matcher = pattern.matcher(request.getParameter("conferencesLink"));
        if (matcher.find())
            request.getSession().setAttribute("conferencesLink", matcher.group(1));

        int conferenceCount = conferenceService.getConferencesCount();
        Map<String, Integer> paginationAttributes =
                new PaginationUtil().getAttributes(request, conferenceCount);
        request.getSession().setAttribute("paginationAttributes", paginationAttributes);

        request.getSession().setAttribute("isRedirect", true);

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
