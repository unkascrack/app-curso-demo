package es.curso.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

    @Autowired
    private MyAuthenticationProvider authManager;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        logger.info("AuthenticationTokenProcessingFilter.doFilter()");

        final String token = ((HttpServletRequest) request).getHeader("token");
        logger.info("token: " + token);

        if (token != null) {
            final AbstractAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
            SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(auth));
        }

        chain.doFilter(request, response);
    }
}
