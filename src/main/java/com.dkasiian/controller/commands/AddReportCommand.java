package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.FormValidationUtil;
import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.services.ConferenceService;
import com.dkasiian.model.services.ReportService;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddReportCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(AddReportCommand.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();
    private ReportService reportService = new ReportService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String role = (String) request.getSession().getAttribute("role");
        String login = (String) request.getSession().getAttribute("login");
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);
        ResourceBundle regexBundle = ResourceBundle.getBundle(ResourceName.REGEXP_BUNDLE, locale);

        if (request.getParameter("conferenceId") == null) {
            return "redirect:/" +
                    request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences");
        }

        if (role.equalsIgnoreCase(Role.ADMIN.name()))
            request.setAttribute("speakers", userService.getAllSpeakers(locale.toString()));
        else
            request.setAttribute("speakers", userService.getById(
                    userService.getUserId(login, locale.toString()),
                    locale.toString())
            );

        request.setAttribute("conferenceId", request.getParameter("conferenceId"));

        if (request.getParameter("submitted") == null) {
            LOG.trace("Not Submitted");
            return URL_BUNDLE.getString("url.forward.add.report");
        }

        if (FormValidationUtil.isFormDataValid(request, regexBundle, messageBundle))
            return URL_BUNDLE.getString("url.forward.add.report");

        Conference conference = conferenceService
                .getConferenceById(Long.parseLong(request.getParameter("conferenceId")), locale.toString());
        LocalDateTime reportDateTime = LocalDateTime.parse(
                request.getParameter("datetime").replace(" ", "T"));
        if (reportDateTime.compareTo(conference.getDateTime()) < 0) {
            request.setAttribute("reportThemeEn", request.getParameter("reportThemeEn"));
            request.setAttribute("reportThemeUa", request.getParameter("reportThemeUa"));
            request.setAttribute("datetime", request.getParameter("datetime"));
            request.setAttribute("incorrect_datetime", messageBundle.getString("html.report.date.before.conference"));
            return URL_BUNDLE.getString("url.forward.add.report");
        }

        // Update
        String reportId = request.getParameter("reportId");
        if (reportId != null && !reportId.isEmpty()) {
            reportService.update(new ReportDto.Builder()
                    .setId(Long.valueOf(reportId))
                    .setThemeEn(request.getParameter("reportThemeEn"))
                    .setThemeUa(request.getParameter("reportThemeUa"))
                    .setDateTime(reportDateTime)
                    .setSpeakerId(Long.valueOf(request.getParameter("speaker")))
                    .build()
            );
            return "redirect:/" +
                    request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences");
        }

        // Add new
        reportService.addReportToConference(conference.getId(), new ReportDto.Builder()
                .setThemeEn(request.getParameter("reportThemeEn"))
                .setThemeUa(request.getParameter("reportThemeUa"))
                .setDateTime(reportDateTime)
                .setSpeakerId(Long.parseLong(request.getParameter("speaker")))
                .build());

        return "redirect:/"
                + request.getSession().getAttribute("role")
                + URL_BUNDLE.getString("url.redirect.conferences");
    }

}
