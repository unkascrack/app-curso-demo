package es.curso.demo.web.flexjson;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.model.Tutor;
import es.curso.demo.service.JSONService;
import es.curso.demo.service.TutorService;

@RequestMapping("/flexjson/tutores")
@Controller
public class TutorFlexJSONController {

    static final Logger logger = LoggerFactory.getLogger(TutorFlexJSONController.class);

    @Autowired
    private transient TutorService tutorService;

    @Autowired
    private transient JSONService jsonService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list() {
        final List<Tutor> tutores = tutorService.findAll();
        return jsonService.serialize("tutores", tutores);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Tutor tutor = tutorService.findById(id);
        return jsonService.serialize("tutor", tutor);
    }
}
