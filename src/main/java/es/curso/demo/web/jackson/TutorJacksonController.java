package es.curso.demo.web.jackson;

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
import es.curso.demo.service.TutorService;

@RequestMapping("/secured/jackson/tutores")
@Controller
public class TutorJacksonController {

    static final Logger logger = LoggerFactory.getLogger(TutorJacksonController.class);

    @Autowired
    private transient TutorService tutorService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Tutor> list() {
        return tutorService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Tutor getById(@PathVariable final Long id) {
        return tutorService.findById(id);
    }
}
