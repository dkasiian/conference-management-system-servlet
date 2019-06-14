package com.dkasiian.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Locale;

public class LocaleListener implements HttpSessionAttributeListener {

    private final static Logger LOG = LogManager.getLogger(LocaleListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) { }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) { }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals("lang")) {
            String lang = (String) event.getSession().getAttribute("lang");
            if (lang.equals("en_US")) {
                LOG.info("LocaleListener :: attributeReplaced :: Locale is set to 'en_US'");
                event.getSession().setAttribute("locale", new Locale("en", "US"));
            }
            else if (lang.equals("uk_UA")) {
                LOG.info("LocaleListener :: attributeReplaced :: Locale is set to 'uk_UA'");
                event.getSession().setAttribute("locale", new Locale("uk", "UA"));
            }
        }
    }
}