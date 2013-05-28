package es.curso.demo.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private static final Map<String, String> SESSIONS = new HashMap<String, String>();

    public String create(final String username) {
        final String token = getToken();
        SESSIONS.put(token, username);
        return token;
    }

    public boolean isSession(final String token) {
        return SESSIONS.containsKey(token);
    }

    public void destroy(final String token) {
        SESSIONS.remove(token);
    }

    private static final String SHA1_PRNG = "SHA1PRNG";
    private static String PASSWORD_KEY = "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom SECURERANDOM;
    static {
        try {
            SECURERANDOM = SecureRandom.getInstance(SHA1_PRNG);
        } catch (final NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

    private final String getToken() {
        final int length = 20;
        final char alphabet[] = PASSWORD_KEY.toCharArray();
        final StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(alphabet[SECURERANDOM.nextInt(alphabet.length)]);
        }
        return builder.toString();
    }
}
