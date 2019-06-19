package com.dkasiian.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class SetLanguageCommand implements Command {

    @Override
    public String process(HttpServletRequest request) {
        request.getSession().setAttribute("lang", request.getParameter("lang"));
        return "redirect:/";
    }

}
