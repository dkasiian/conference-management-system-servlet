package com.dkasiian.controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class FormValidationUtil {

    public static boolean isFormDataValid(HttpServletRequest request, ResourceBundle regexBundle, ResourceBundle messageBundle){
        List<String> parameters = new ArrayList<>(request.getParameterMap().keySet());
        if (parameters.isEmpty())
            return false;

        boolean isValid = true;
        for (String parameter : parameters) {
            if (parameter.contains("Id") ||
                    parameter.contains("speaker") ||
                    parameter.contains("submitted") ||
                    parameter.contains("page") ||
                    parameter.contains("conferencesLink"))
                continue;
            if (!isParameterValid(request.getParameter(parameter), regexBundle.getString(parameter + ".regexp"))) {
                request.setAttribute("incorrect_" + parameter, messageBundle.getString("html.validation.incorrect." + parameter));
                isValid = false;
            } else {
                request.setAttribute(parameter, request.getParameter(parameter));
            }
        }
        return isValid;
    }

    private static boolean isParameterValid(String parameterData, String regexp) {
        return Pattern.compile(regexp).matcher(parameterData).matches();
    }
}
