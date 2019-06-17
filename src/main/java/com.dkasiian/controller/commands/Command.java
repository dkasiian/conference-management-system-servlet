package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public abstract class Command {

    static final ResourceBundle URL_BUNDLE = ResourceBundle.getBundle(ResourceName.URL_BUNDLE);

    public abstract String process(HttpServletRequest request);

}
