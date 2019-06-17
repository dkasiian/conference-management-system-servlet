package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.PaginationUtil;
import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SetRatingCommand extends Command {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request){

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String login = (String) request.getSession().getAttribute("login");

        int speakersCount = userService.getSpeakersIds().size();
        Map<String, Integer> paginationAttributes = new PaginationUtil().getAttributes(request, speakersCount);
        request.getSession().setAttribute("paginationAttributes", paginationAttributes);
        List<User> speakers = userService.getPaginatedSpeakers(
                paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
        request.getSession().setAttribute("paginationAttributes", paginationAttributes);
        request.getSession().setAttribute("speakers", speakers);

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
