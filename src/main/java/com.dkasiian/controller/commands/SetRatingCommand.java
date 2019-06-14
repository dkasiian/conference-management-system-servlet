package com.dkasiian.controller.commands;

import com.dkasiian.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class SetRatingCommand extends Command {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) throws ServletException {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        long userId = userService.getUserId(login, locale.toString());
        long speakerId = Long.valueOf(request.getParameter("speakerId"));
        String rating = request.getParameter("rating");

        if (rating != null && !rating.isEmpty()){
            if (userService.checkIsUserAlreadySetRating(userId, speakerId))
                userService.updateRating(userId, speakerId, Integer.parseInt(rating));
            else
                userService.setRating(userId, speakerId, Integer.parseInt(rating));
        }

        if (rating == null)
            userService.deleteRating(userId, speakerId);

        return "redirect:/" +
                request.getSession().getAttribute("role") +
                URL_BUNDLE.getString("url.redirect.rating");
    }
}
