package es.curso.demo.web.jackson;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.service.AuthenticationService;

@RequestMapping("/jackson")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String login(final String username, final String password, final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        final String session = authService.login(username, password);
        if (session != null) {

        }
        return session;
    }
}
