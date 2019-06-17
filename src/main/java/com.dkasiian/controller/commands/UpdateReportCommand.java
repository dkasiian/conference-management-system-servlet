package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.services.ReportService;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateReportCommand extends Command {

    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private ReportService reportService = new ReportService();
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request){

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String role = (String) request.getSession().getAttribute("role");
        String login = (String) request.getSession().getAttribute("login");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        if (request.getParameter("reportId") == null || request.getParameter("conferenceId") == null)
            return "redirect:/" +
                request.getSession().getAttribute("role") +
                URL_BUNDLE.getString("url.redirect.conferences") +
                "/" + request.getParameter("conferenceId") +
                URL_BUNDLE.getString("url.redirect.reports");

        ReportDto report = reportService.getReportDtoById(Long.valueOf(request.getParameter("reportId")));

        if (report == null)
            throw new RuntimeException((messages.getString("error.report.not.found")));

        request.setAttribute("conferenceId", request.getParameter("conferenceId"));
        request.setAttribute("reportId", report.getId());
        request.setAttribute("reportThemeEn", report.getThemeEn());
        request.setAttribute("reportThemeUa", report.getThemeUa());
        request.setAttribute("datetime", report.getDateTime());
        request.setAttribute("speakerId", report.getSpeakerId());

        if (role.equalsIgnoreCase(Role.ADMIN.name()))
            request.setAttribute("speakers", userService.getAllSpeakers(locale.toString()));
        else
            request.setAttribute("speakers", userService.getById(
                    userService.getUserId(login, locale.toString()),
                    locale.toString())
            );

        return URL_BUNDLE.getString("url.forward.add.report");
    }
}
