package es.curso.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Override
    public Authentication authenticate(final Authentication auth) throws AuthenticationException {
        logger.info("MyAuthenticationProvider.authenticate(): " + auth.toString());

        // ResultadoAuthentication resultado = authenticationService.authenticate(authentication.getName(),
        // authentication.getCredentials(), "TEST");
        // List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // if (user.getUsername() == null){
        // throw new AuthenticationCredentialsNotFoundException("user not found");
        // }
        // if(!user.getPassword().equals(hashed)){
        // throw new BadCredentialsException("password is wrong");
        // }
        // String roleName = user.getUserType().getTitle();
        // if (roleName.equals("admin"))
        // authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // else
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (!StringUtils.equals("admin", auth.getName())) {
            throw new AuthenticationCredentialsNotFoundException("user not found");
        }
        if (!StringUtils.equals("admin", auth.getCredentials().toString())) {
            throw new BadCredentialsException("password is wrong");
        }

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_REST"));
        return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
    }

    @Override
    public boolean supports(final Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

}
