package com.dkasiian.controller.commands;

import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.services.ConferenceService;
import com.dkasiian.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseCommand implements Command {

    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();
    private static final int DAYS_TO_ANNOUNCEMENT_BY_DEFAULT = 3;

    @Override
    public String process(HttpServletRequest request){

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        if (login == null || login.isEmpty())
            return URL_BUNDLE.getString("url.redirect.index");

        int daysToAnnouncement;
        if (request.getParameter("daysToAnnouncement") == null)
            daysToAnnouncement = DAYS_TO_ANNOUNCEMENT_BY_DEFAULT;
        else
            daysToAnnouncement = Integer.parseInt(request.getParameter("daysToAnnouncement"));

        long userId = userService.getUserId(login, locale.toString());
        List<Long> conferencesIds = userService.getConferencesIdsForWhichUserHasRegistration(userId);

        List<Conference> conferences = new ArrayList<>();
        for (Long conferenceId : conferencesIds) {
            Conference conference = conferenceService.getConferenceById(conferenceId, locale.toString());
            if (conference.getDateTime().isAfter(LocalDateTime.now()) &&
                    conference.getDateTime().isBefore(LocalDateTime.now().plusDays(daysToAnnouncement)))
                conferences.add(conference);
        }

        List<String> remainingTimes = new ArrayList<>();
        LocalDateTime toDateTime = LocalDateTime.now();
        for (Conference conference : conferences){
            LocalDateTime tempDateTime = conference.getDateTime();
            long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
            tempDateTime = tempDateTime.plusDays(days);
            long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plusHours(hours);
            long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
            remainingTimes.add(Math.abs(days) + " : " + Math.abs(hours) + " : " + Math.abs(minutes));
        }

        request.setAttribute("conferences", conferences);
        request.setAttribute("remainingTimes", remainingTimes);
        request.setAttribute("daysToAnnouncement", daysToAnnouncement);

        return URL_BUNDLE.getString("url.redirect.index");
    }
}
