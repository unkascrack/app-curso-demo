package es.curso.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private SessionService session;

    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("admin", "admin");
        USERS.put("user", "user");
    }

    public String login(final String username, final String password) {
        String result = null;
        if (password != null && password.equals(USERS.get(username))) {
            result = session.create(username);
        }
        return result;
    }

    public void logout(final String token) {
        session.destroy(token);
    }
}
