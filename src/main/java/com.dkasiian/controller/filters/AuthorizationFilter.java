package com.dkasiian.controller.filters;

import com.dkasiian.controller.commands.Command;
import com.dkasiian.controller.commands.CommandFactory;
import com.dkasiian.controller.utils.SecurityConfigUtil;
import com.dkasiian.model.exceptions.AccessDeniedException;
import com.dkasiian.model.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

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

        if (securityConfigUtil.isIgnore(uri) || securityConfigUtil.isAllowed(uri, userRole)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Optional<Command> commandFromRequest = CommandFactory.getCommandFromRequest(request);
        if (!commandFromRequest.isPresent()) {
            LOG.warn("Requested page Not Found.");
            throw new NotFoundException();
        } else {
            String login = (String) request.getSession().getAttribute("login");
            LOG.warn("Unauthorized access attempt. User login: " + (login != null ? login : "unknown (guest)"));
            throw new AccessDeniedException();
        }
    }

    @Override
    public void destroy() {}
}