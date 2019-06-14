package com.dkasiian.controller.commands;

import com.dkasiian.model.services.ReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DeleteReportCommand extends Command {

    private ReportService reportService = new ReportService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        String conferenceId = request.getParameter("conferenceId");
        request.setAttribute("conferenceId", conferenceId);

        if (request.getParameter("reportId") == null)
            return "redirect:/" +
                    request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences") +
                    "/" + request.getParameter("conferenceId") +
                    URL_BUNDLE.getString("url.redirect.reports");

        if (!reportService.delete(Long.valueOf(request.getParameter("reportId"))))
            throw new RuntimeException();

            return  "redirect:/" + request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences") +
                    "/" + conferenceId +
                    URL_BUNDLE.getString("url.redirect.reports");
    }
}
