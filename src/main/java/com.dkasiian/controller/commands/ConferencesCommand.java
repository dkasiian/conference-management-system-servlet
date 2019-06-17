package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.controller.utils.SecurityConfigUtil;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.services.ConferenceService;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferencesCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(ConferencesCommand.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        List<Conference> conferences;
        Map<String, Integer> paginationAttributes;
        if (request.getSession().getAttribute("isRedirect") != null){
            request.setAttribute("conferencesLink", request.getSession().getAttribute("conferencesLink"));
            paginationAttributes = (HashMap<String, Integer>) request.getSession().getAttribute("paginationAttributes");
            conferences = conferenceService.getPaginatedConferences(
                    paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
            request.getSession().removeAttribute("conferencesLink");
            request.getSession().removeAttribute("paginationAttributes");
            request.getSession().removeAttribute("isRedirect");
        } else if (request.getSession().getAttribute("afterPastFutureConferences") != null){
            request.setAttribute("conferencesLink", request.getSession().getAttribute("conferencesLink"));
            paginationAttributes = (HashMap<String, Integer>) request.getSession().getAttribute("paginationAttributes");
            conferences = (ArrayList<Conference>) request.getSession().getAttribute("conferences");
            request.getSession().removeAttribute("conferencesLink");
            request.getSession().removeAttribute("paginationAttributes");
            request.getSession().removeAttribute("afterPastFutureConferences");
        } else {
            Pattern pattern = Pattern.compile("(past-conferences|future-conferences|conferences)");
            Matcher matcher = pattern.matcher(request.getRequestURI());
            if (matcher.find())
                request.setAttribute("conferencesLink", matcher.group(1));

            int conferenceCount = conferenceService.getConferencesCount();
            paginationAttributes = new PaginationUtil().getAttributes(request, conferenceCount);
            conferences = conferenceService.getPaginatedConferences(
                    paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
        }

        request.setAttribute("paginationAttributes", paginationAttributes);
        request.setAttribute("conferences", conferences);

        request.setAttribute("now", LocalDateTime.now());

        if (SecurityConfigUtil.isGuest(request))
            return URL_BUNDLE.getString("url.forward.conferences");

        long userId = userService.getUserId(login, locale.toString());
        List<Long> conferencesIds = userService.getConferencesIdsForWhichUserHasRegistration(userId);

        List<Boolean> isRegister = new ArrayList<>();
        for (Conference conference : conferences){
            if (conferencesIds.contains(conference.getId()))
                isRegister.add(true);
            else
                isRegister.add(false);
        }

        request.setAttribute("isRegister", isRegister);

        request.setAttribute("userId", userId);

        return URL_BUNDLE.getString("url.forward.conferences");
    }

}
