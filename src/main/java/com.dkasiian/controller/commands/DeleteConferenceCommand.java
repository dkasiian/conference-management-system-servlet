package com.dkasiian.controller.commands;

import com.dkasiian.model.services.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class DeleteConferenceCommand extends Command {

    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String process(HttpServletRequest request) {

        if (request.getParameter("conferenceId") == null)
            return "redirect:/" + request.getSession().getAttribute("role") +
                    URL_BUNDLE.getString("url.redirect.conferences");

        if (!conferenceService.delete(Long.valueOf(request.getParameter("conferenceId"))))
            throw new RuntimeException();

        return "redirect:/" + request.getSession().getAttribute("role")
                + URL_BUNDLE.getString("url.redirect.conferences");

    }
}
