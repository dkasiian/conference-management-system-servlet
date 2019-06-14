package com.dkasiian.controller.commands;

import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.services.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class StatisticsCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(AddConferenceCommand.class);
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");

        List<Conference> conferences = conferenceService.getAllConferences(locale.toString());
        Map<Long, Integer> conferenceIdSpeakersCount = conferenceService.getSpeakersNumber();
        Map<Long, Integer> conferenceIdReportsCount = conferenceService.getReportsNumber();
        Map<Long, Integer> conferenceIdUsersCount = conferenceService.getUsersNumber();
        Map<Long, Integer> conferenceIdVisitors = new HashMap<>();

        for (Conference conference : conferences){
            if (conferenceIdUsersCount.get(conference.getId()) == null)
                continue;
            conferenceIdVisitors.put(
                    conference.getId(),
                    new Random().nextInt(conferenceIdUsersCount.get(conference.getId())));
        }

        request.setAttribute("conferences", conferences);
        request.setAttribute("conferenceIdSpeakersCount", conferenceIdSpeakersCount);
        request.setAttribute("conferenceIdReportsCount", conferenceIdReportsCount);
        request.setAttribute("conferenceIdUsersCount", conferenceIdUsersCount);
        request.setAttribute("conferenceIdVisitors", conferenceIdVisitors);

        return URL_BUNDLE.getString("url.forward.statistics");
    }
}