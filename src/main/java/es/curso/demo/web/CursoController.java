package es.curso.demo.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.model.Curso;
import es.curso.demo.model.FiltroBusqueda;
import es.curso.demo.service.CursoService;
import es.curso.demo.service.JSONService;

@RequestMapping("cursos")
@Controller
public class CursoController {

    static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private transient CursoService cursoService;

    @Autowired
    private transient JSONService jsonService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list(final Curso curso, final FiltroBusqueda filtro) {
        curso.setFiltro(filtro);
        final List<Curso> cursos = cursoService.findByCurso(curso);
        return jsonService.serialize("cursos", cursos);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Curso curso = cursoService.findById(id);
        return jsonService.serialize("curso", curso);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String create(@RequestBody final String json) throws IOException {
        final Curso curso = jsonService.deserialize(Curso.class, "curso", json);
        cursoService.insert(curso);
        return jsonService.serialize("curso", curso);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String update(@PathVariable final Long id, @RequestBody final String json) throws IOException {
        final Curso curso = jsonService.deserialize(Curso.class, "curso", json);
        curso.setId(id);
        cursoService.update(curso);
        return jsonService.serialize("curso", curso);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String delete(@PathVariable final Long id) {
        cursoService.delete(id);
        return "OK";
    }
}
