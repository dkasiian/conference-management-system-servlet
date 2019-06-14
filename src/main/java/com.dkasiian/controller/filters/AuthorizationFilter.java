package com.dkasiian.controller.filters;

import com.dkasiian.controller.utils.SecurityConfigUtil;
import com.dkasiian.model.exceptions.AccessDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String userRole = (String) session.getAttribute("role");
        SecurityConfigUtil securityConfigUtil = new SecurityConfigUtil();

        if (securityConfigUtil.isIgnore(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (securityConfigUtil.isAllowed(uri, userRole)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            String login = (String) request.getSession().getAttribute("login");
            LOG.warn("Unauthorized access attempt. User login: " + (login != null ? login : "unknown (guest)"));
            throw new AccessDeniedException();
        }
    }

    @Override
    public void destroy() {}
}