package es.curso.demo.web.jackson;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.model.Curso;
import es.curso.demo.service.CursoService;

@RequestMapping("/jackson/secured/cursos")
@Controller
public class CursoJacksonController {

    static final Logger logger = LoggerFactory.getLogger(CursoJacksonController.class);

    @Autowired
    private transient CursoService cursoService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object search(final Curso curso) {
        return curso != null && curso.isTotal() ? cursoService.findTotalByCurso(curso) : cursoService
                .findByCurso(curso);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Curso> getById(@PathVariable final Long id) {
        final Curso curso = cursoService.findById(id);
        final HttpStatus httpStatus = curso != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Curso>(curso, httpStatus);
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
