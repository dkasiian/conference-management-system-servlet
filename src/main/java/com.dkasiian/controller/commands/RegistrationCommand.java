package com.dkasiian.controller.commands;

import com.dkasiian.controller.utils.FormValidationUtil;
import com.dkasiian.controller.utils.SecurityConfigUtil;
import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dto.UserDto;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationCommand extends Command {

    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle regexBundle = ResourceBundle.getBundle(ResourceName.REGEXP_BUNDLE);
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceName.MESSAGE_BUNDLE, locale);

        LOG.debug("locale: " + locale.toString());

        if (!FormValidationUtil.isFormDataValid(request, regexBundle, messageBundle))
            return URL_BUNDLE.getString("url.forward.registration");

        if (userService.isUserExist(request.getParameter("login"))) {
            request.setAttribute("incorrect_login", messageBundle.getString("html.validation.user.already.exist"));
            return URL_BUNDLE.getString("url.forward.registration");
        }

        UserDto user = new UserDto.Builder()
                .setLogin(request.getParameter("login"))
                .setPassword(new SecurityConfigUtil().hashPassword(request.getParameter("password")))
                .setNameEn(request.getParameter("nameEn"))
                .setNameUa(request.getParameter("nameUa"))
                .setSurnameEn(request.getParameter("surnameEn"))
                .setSurnameUa(request.getParameter("surnameUa"))
                .setEmail(request.getParameter("email"))
                .setRole(Role.valueOf(request.getParameter("role")))
                .build();
        userService.register(user);

        LOG.info("New user registered");

        return "redirect:" + URL_BUNDLE.getString("url.redirect.guest.login");
    }
}
