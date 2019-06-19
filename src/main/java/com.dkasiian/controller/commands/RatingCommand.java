package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.model.entities.Report;
import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.ReportService;
import com.dkasiian.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RatingCommand implements Command {

    private UserService userService = new UserService();
    private ReportService reportService = new ReportService();

    @Override
    public String process(HttpServletRequest request){

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        List<User> speakers;
        Map<String, Integer> paginationAttributes;
        if (request.getSession().getAttribute("speakers") != null){
            speakers = (ArrayList<User>) request.getSession().getAttribute("speakers");
            paginationAttributes = (HashMap<String, Integer>) request.getSession().getAttribute("paginationAttributes");
            request.getSession().removeAttribute("speakers");
            request.getSession().removeAttribute("paginationAttributes");
        } else {
            int speakersCount = userService.getSpeakersIds().size();
            paginationAttributes = new PaginationUtil().getAttributes(request, speakersCount);
            speakers = userService.getPaginatedSpeakers(
                    paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
        }

        long userId = userService.getUserId(login, locale.toString());
        request.setAttribute("userId", userId);
        List<Long> conferencesIds = userService.getConferencesIdsForWhichUserHasRegistration(userId);
        List<Long> speakersIdsForRating;
        if (conferencesIds != null && !conferencesIds.isEmpty())
            speakersIdsForRating = userService.getSpeakersIdsForRating(conferencesIds);
        else
            speakersIdsForRating = new ArrayList<>();

        List<Long> allSpeakersIds = userService.getSpeakersIds();
        Map<Long, List<Report>> speakerIdToReports = reportService.getSpeakersReports(allSpeakersIds, locale.toString());

        Map<Long, Integer> speakerIdToRating = userService.getSpeakersRating(allSpeakersIds);
        Map<Long, Integer> speakerIdToBonuses = userService.getSpeakersBonuses(allSpeakersIds);
        Map<Long, Integer> speakerIdToUserRating = userService.getSpeakersRatingByUser(userId);

        request.setAttribute("speakers", speakers);
        request.setAttribute("paginationAttributes", paginationAttributes);
        request.setAttribute("speakersIdsForRating", speakersIdsForRating);
        request.setAttribute("speakerIdToReports", speakerIdToReports);
        request.setAttribute("speakerIdToRating", speakerIdToRating);
        request.setAttribute("speakerIdToBonuses", speakerIdToBonuses);
        request.setAttribute("speakerIdToUserRating", speakerIdToUserRating);

        return URL_BUNDLE.getString("url.forward.rating");
    }
}
