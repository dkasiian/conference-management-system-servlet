package com.dkasiian.controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class FormValidationUtil {

    public static boolean isFormDataValid(HttpServletRequest request, ResourceBundle regexBundle, ResourceBundle messageBundle){
        boolean isValid = true;

        if (!request.getParameterNames().hasMoreElements())
            return true;

        for (String parameter : new ArrayList<>(request.getParameterMap().keySet())) {
            if (parameter.contains("Id") || parameter.contains("speaker") || parameter.contains("submitted"))
                continue;
            if (!validateParameter(request.getParameter(parameter), regexBundle.getString(parameter + ".regexp"))) {
                request.setAttribute("incorrect_" + parameter, messageBundle.getString("html.validation.incorrect." + parameter));
                isValid = false;
            } else {
                request.setAttribute(parameter, request.getParameter(parameter));
            }
        }
        return !isValid;
    }

    private static boolean validateParameter(String parameterData, String regexp) {
        return Pattern.compile(regexp).matcher(parameterData).matches();
    }
}
