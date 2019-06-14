//package com.dkasiian.controller.commands;
//
//import com.dkasiian.controller.utils.PaginationUtil;
//import com.dkasiian.model.entities.Conference;
//import com.dkasiian.model.services.ConferenceService;
//import com.dkasiian.model.services.UserService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class RegisterForConferenceCommand extends Command {
//
//    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
//    private ConferenceService conferenceService = new ConferenceService();
//    private UserService userService = new UserService();
//
//    @Override
//    public String process(HttpServletRequest request) throws ServletException {
//
//        Locale locale = (Locale) request.getSession().getAttribute("locale");
//        String login = (String) request.getSession().getAttribute("login");
//
//        Pattern pattern = Pattern.compile("(past-conferences|future-conferences|conferences)");
//        Matcher matcher = pattern.matcher(request.getParameter("conferencesLink"));
//        if (matcher.find())
//            request.getSession().setAttribute("conferencesLink", matcher.group(1));
//
//        int conferenceCount = conferenceService.getConferencesCount();
//        Map<String, Integer> paginationAttributes =
//                new PaginationUtil().getAttributes(request, conferenceCount);
//        request.getSession().setAttribute("paginationAttributes", paginationAttributes);
//        List<Conference> conferences = conferenceService.getPaginatedConferences(
//                paginationAttributes.get("begin"), paginationAttributes.get("recordsPerPage"), locale.toString());
//        request.getSession().setAttribute("conferences", conferences);
//
//        long conferenceId = Long.valueOf(request.getParameter("conferenceId"));
//        long userId = userService.getUserId(login, locale.toString());
//
//        userService.registerForConference(userId, conferenceId);
//
//        return "redirect:/" +
//                request.getSession().getAttribute("role") +
//                URL_BUNDLE.getString("url.redirect.conferences");
//    }
//}
