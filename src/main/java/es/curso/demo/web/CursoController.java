package es.curso.demo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.mapper.CursoMapper;
import es.curso.demo.model.Curso;
import es.curso.demo.model.JSONResponse;
import flexjson.JSONSerializer;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@RequestMapping("cursos")
@Controller
public class CursoController {

    @Autowired
    private transient CursoMapper cursoMapper;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list(@RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size,
            @RequestParam(defaultValue = "titulo") final String orderBy,
            @RequestParam(defaultValue = "false") final Boolean orderType) {
        final List<Curso> cursos = cursoMapper.selectByActivo(page, size, orderBy, orderType);
        return new JSONSerializer().rootName("cursos").exclude("*.class").serialize(cursos);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getById(@PathVariable final Long id) {
        final Curso curso = cursoMapper.selectById(id);
        return curso != null ? new JSONSerializer().rootName("curso").exclude("*.class").serialize(curso) : null;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JSONResponse createReturnJSONResponse(@Valid final Curso curso, final BindingResult bindingResult) {
        final JSONResponse response = new JSONResponse();
        if (bindingResult.hasErrors()) {
            response.setStatus("ERROR");
        } else {
            cursoMapper.insert(curso);
            response.setStatus("OK");
        }
        response.setResult(curso);
        return response;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Curso createReturnCurso(final Curso curso, final BindingResult bindingResult) {
        cursoMapper.insert(curso);
        return curso;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String update(@Valid final Curso curso, final BindingResult bindingResult) {
        String result = null;
        if (bindingResult.hasErrors() || curso.getId() == null) {
            result = "ERROR";
        } else {
            cursoMapper.update(curso);
            result = "OK";
        }
        return result;
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
