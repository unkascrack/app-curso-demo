package es.curso.demo.web.jackson;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.model.Curso;
import es.curso.demo.service.CursoService;

@RequestMapping("/jackson/cursos")
@Controller
public class CursoJacksonController {

    static final Logger logger = LoggerFactory.getLogger(CursoJacksonController.class);

    @Autowired
    private transient CursoService cursoService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Curso> list(final Curso curso) {
        return cursoService.findByCurso(curso);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Curso getById(@PathVariable final Long id) {
        return cursoService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Curso create(@RequestBody @Valid final Curso curso, final BindingResult results) throws IOException {
        if (results.hasErrors()) {
            logger.warn("Errors " + results.getAllErrors());
        } else {
            cursoService.insert(curso);
        }
        return curso;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PUT, RequestMethod.POST }, produces = "application/json")
    @ResponseBody
    public Curso update(@PathVariable final Long id, @RequestBody @Valid final Curso curso, final BindingResult results)
            throws IOException {
        if (results.hasErrors()) {
            logger.warn("Errors " + results.getAllErrors());
        } else {
            curso.setId(id);
            cursoService.update(curso);
        }
        return curso;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String delete(@PathVariable final Long id) {
        cursoService.delete(id);
        return "OK";
    }
}
