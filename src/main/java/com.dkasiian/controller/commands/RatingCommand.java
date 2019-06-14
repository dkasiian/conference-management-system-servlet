package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.entities.Report;
import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.ReportService;
import com.dkasiian.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RatingCommand extends Command {

    private UserService userService = new UserService();
    private ReportService reportService = new ReportService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        long userId = userService.getUserId(login, locale.toString());
        request.setAttribute("userId", userId);
        List<Long> conferencesIds = userService.getConferencesIdsForWhichUserHasRegistration(userId);
        List<Long> speakersIdsForRating;
        if (conferencesIds != null && !conferencesIds.isEmpty())
            speakersIdsForRating = userService.getSpeakersIdsForRating(conferencesIds);
        else
            speakersIdsForRating = new ArrayList<>();

        List<User> allSpeakers = userService.getAllSpeakers(locale.toString());

        List<Long> allSpeakersIds = userService.getSpeakersIds();
        Map<Long, List<Report>> speakerIdToReports = reportService.getSpeakersReports(allSpeakersIds, locale.toString());

        Map<Long, Integer> speakerIdToRating = userService.getSpeakersRating(allSpeakersIds);
        Map<Long, Integer> speakerIdToUserRating = userService.getSpeakersRatingByUser(userId);

        request.setAttribute("allSpeakers", allSpeakers);
        request.setAttribute("speakersIdsForRating", speakersIdsForRating);
        request.setAttribute("speakerIdToReports", speakerIdToReports);
        request.setAttribute("speakerIdToRating", speakerIdToRating);
        request.setAttribute("speakerIdToUserRating", speakerIdToUserRating);

        return URL_BUNDLE.getString("url.forward.rating");
    }
}
