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

    // http://code.google.com/p/swe-574-group2/source/browse/trunk/service/src/main/java/com/swe/filters/AuthenticationTokenProcessingFilter.java?spec=svn112&r=112

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        logger.info("AuthenticationTokenProcessingFilter.doFilter()");

        final String token = ((HttpServletRequest) request).getHeader("token");
        logger.info("token: " + token);

        if (token == null) {
            final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("admin", "admin");
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
            SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(auth));
        }

        // if (parms.containsKey("token")) {
        // final String token = parms.get("token")[0]; // grab the first "token" parameter
        // validate the token
        // if (tokenUtils.validate(token)) {
        // // determine the user based on the (already validated) token
        // UserDetails userDetails = tokenUtils.getUserFromToken(token);
        // // build an Authentication object with the user's info
        // UsernamePasswordAuthenticationToken authentication =
        // new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest)
        // request));
        // // set the authentication into the SecurityContext
        // SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));
        // }
        // }

        // determine the user based on the (already validated) token
        // final UserDetails userDetails = tokenUtils.getUserFromToken(token);
        // final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        // userDetails.getUsername(), userDetails.getPassword());
        // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));

        // final AnonymousAuthenticationToken authentication = new AnonymousAuthenticationToken("test", null, null);
        // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
        //
        // // set the authentication into the SecurityContext
        // SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));

        // continue thru the filter chain
        chain.doFilter(request, response);
    }

    // public interface TokenUtils {
    // String getToken(UserDetails userDetails);
    // String getToken(UserDetails userDetails, Long expiration);
    // boolean validate(String token);
    // UserDetails getUserFromToken(String token);
    // }
}
