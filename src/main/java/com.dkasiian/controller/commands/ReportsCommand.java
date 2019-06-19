package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.controller.utils.SecurityConfigUtil;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.entities.Report;
import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.ConferenceService;
import com.dkasiian.model.services.ReportService;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReportsCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(ConferencesCommand.class);
    private ConferenceService conferenceService = new ConferenceService();
    private UserService userService = new UserService();
    private ReportService reportService = new ReportService();

    @Override
    public String process(HttpServletRequest request){

        long conferenceId = 0;
        if (request.getParameter("conferenceId") == null){
            String[] words = request.getRequestURI().split("/");
            for (int i = 0; i < words.length; i++)
                if (words[i].equalsIgnoreCase("conferences"))
                    conferenceId = Long.valueOf(words[i + 1]);
        } else {
            conferenceId = Long.valueOf(request.getParameter("conferenceId"));
        }

        String login = (String) request.getSession().getAttribute("login");
        Locale locale = (Locale) request.getSession().getAttribute("locale");

        int reportCount = reportService.getReportsCountLinkedToConference(conferenceId);
        Map<String, Integer> paginationAttributes =
                new PaginationUtil().getAttributes(request, reportCount);
        request.setAttribute("paginationAttributes", paginationAttributes);
        List<Report> reports = reportService.getPaginatedReportsLinkedToConference(
                conferenceId,
                paginationAttributes.get("begin"),
                paginationAttributes.get("recordsPerPage"),
                locale.toString()
        );
        request.setAttribute("reports", reports);

        List<User> speakers = new ArrayList<>();
        for (Report report : reports)
            speakers.add(userService.getById(report.getSpeakerId(), locale.toString()));
        request.setAttribute("speakers", speakers);

        if (!SecurityConfigUtil.isGuest(request)) {
            long userId = userService.getUserId(login, locale.toString());
            request.setAttribute("userId", userId);
        }

        request.setAttribute("conferenceId", conferenceId);

        Conference conference = conferenceService.getConferenceById(conferenceId, locale.toString());
        if (conference.getDateTime().isBefore(LocalDateTime.now()))
            request.setAttribute("isAddReportButtonDisable", true);

        return URL_BUNDLE.getString("url.forward.reports");
    }
}
