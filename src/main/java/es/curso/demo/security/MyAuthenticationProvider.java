package es.curso.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import es.curso.demo.service.SessionService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Autowired
    private SessionService session;

    @Override
    public Authentication authenticate(final Authentication auth) throws AuthenticationException {
        logger.info("MyAuthenticationProvider.authenticate(): " + auth.toString());

        final String token = auth.getName();
        if (!session.isSession(token)) {
            throw new SessionAuthenticationException("Session not initialized!!!");
        }

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_REST"));
        return new UsernamePasswordAuthenticationToken(auth.getName(), null, authorities);
    }

    @Override
    public boolean supports(final Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

}
