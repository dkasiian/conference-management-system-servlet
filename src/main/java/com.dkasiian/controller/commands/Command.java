package com.dkasiian.controller.commands;

import com.dkasiian.model.ResourceName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface Command {

    ResourceBundle URL_BUNDLE = ResourceBundle.getBundle(ResourceName.URL_BUNDLE);

    String process(HttpServletRequest request);

}
