package es.curso.demo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.mapper.CursoMapper;
import es.curso.demo.model.Curso;
import es.curso.demo.service.JSONService;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@RequestMapping("cursos")
@Controller
public class CursoController {

    static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private transient CursoMapper cursoMapper;

    @Autowired
    private transient JSONService jsonService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list(@RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size,
            @RequestParam(defaultValue = "titulo") final String orderBy,
            @RequestParam(defaultValue = "false") final Boolean orderType) {
        final List<Curso> cursos = cursoMapper.selectByActivo(page, size, orderBy, orderType);
        return jsonService.serialize("cursos", cursos);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Curso curso = cursoMapper.selectById(id);
        return jsonService.serialize("curso", curso);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String create(@RequestBody final String json) {
        final Curso curso = jsonService.deserialize(Curso.class, "curso", json);
        cursoMapper.insert(curso);
        return jsonService.serialize("curso", curso);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String update(@PathVariable final Long id, @RequestBody final String json) {
        final Curso curso = jsonService.deserialize(Curso.class, "curso", json);
        curso.setId(id);
        cursoMapper.update(curso);
        return jsonService.serialize("curso", curso);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String delete(@PathVariable final Long id) {
        String result = null;
        final Curso curso = cursoMapper.selectById(id);
        if (curso == null) {
            result = "ERROR";
        } else {
            cursoMapper.delete(curso);
            result = "OK";
        }
        return result;
    }
}
