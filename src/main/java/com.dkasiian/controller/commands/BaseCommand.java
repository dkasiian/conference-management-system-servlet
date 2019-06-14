package com.dkasiian.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class BaseCommand extends Command {
    @Override
    public String process(HttpServletRequest request) throws ServletException {
        return URL_BUNDLE.getString("url.redirect.index");
    }
}
