package es.curso.demo.web;

import java.util.List;

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
import flexjson.JSONSerializer;

@RequestMapping("tutores")
@Controller
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TutorController {

    @Autowired
    private transient TutorMapper tutorMapper;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list() {
        final List<Tutor> tutores = tutorMapper.selectAll();
        return new JSONSerializer().rootName("tutores").exclude("*.class").serialize(tutores);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Tutor tutor = tutorMapper.selectById(id);
        return tutor != null ? new JSONSerializer().rootName("tutor").exclude("*.class").serialize(tutor) : null;
    }
}
