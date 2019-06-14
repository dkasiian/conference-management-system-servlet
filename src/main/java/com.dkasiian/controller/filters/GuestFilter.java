package com.dkasiian.controller.filters;

import com.dkasiian.model.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GuestFilter implements Filter {

    private final static Logger LOG =  LogManager.getLogger(GuestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("role") == null) {
            LOG.debug("GuestFilter :: doFilter :: Request URI :: " + httpServletRequest.getRequestURI());
            session.setAttribute("role", Role.GUEST.name().toLowerCase());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
