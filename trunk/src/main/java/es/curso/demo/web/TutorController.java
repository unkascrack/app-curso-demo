package es.curso.demo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.mapper.TutorMapper;
import es.curso.demo.model.Tutor;
import es.curso.demo.service.JSONService;

@RequestMapping("tutores")
@Controller
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TutorController {

    static final Logger logger = LoggerFactory.getLogger(TutorController.class);

    @Autowired
    private transient TutorMapper tutorMapper;

    @Autowired
    private transient JSONService jsonService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list() {
        final List<Tutor> tutores = tutorMapper.selectAll();
        return jsonService.serialize("tutores", tutores);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Tutor tutor = tutorMapper.selectById(id);
        return jsonService.serialize("tutor", tutor);
    }
}
