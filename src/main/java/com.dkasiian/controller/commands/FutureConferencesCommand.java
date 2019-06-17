package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.services.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FutureConferencesCommand extends Command {

    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request){
        Locale locale = (Locale) request.getSession().getAttribute("locale");

        Pattern pattern = Pattern.compile("(past-conferences|future-conferences|conferences(.*))");
        Matcher matcher = pattern.matcher(request.getRequestURI());
        if (matcher.find())
            request.getSession().setAttribute("conferencesLink", matcher.group(1));

        int conferenceCount = conferenceService.getFutureConferencesCount();
        Map<String, Integer> paginationAttributes =
                new PaginationUtil().getAttributes(request, conferenceCount);
        request.getSession().setAttribute("paginationAttributes", paginationAttributes);
        List<Conference> futureConferences = conferenceService.getFuturePaginatedConferences(
                paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
        request.getSession().setAttribute("conferences", futureConferences);

        request.getSession().setAttribute("afterPastFutureConferences", true);

        return "redirect:/" +
                request.getSession().getAttribute("role") +
                URL_BUNDLE.getString("url.redirect.conferences");
    }
}
