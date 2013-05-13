package es.curso.demo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.model.Nivel;
import es.curso.demo.service.JSONService;

@RequestMapping("niveles")
@Controller
public class NivelController {

    @Autowired
    private transient JSONService jsonService;

    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list() {
        final List<Nivel> niveles = Arrays.asList(Nivel.values());
        return jsonService.serialize("niveles", niveles);
    }
}
