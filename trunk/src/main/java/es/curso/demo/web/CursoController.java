package es.curso.demo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.mapper.CursoMapper;
import es.curso.demo.model.Curso;

@RequestMapping("cursos")
@Controller
public class CursoController {

    @Autowired
    private transient CursoMapper cursoMapper;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Curso> list(@RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size,
            @RequestParam(defaultValue = "titulo") final String orderBy,
            @RequestParam(defaultValue = "false") final Boolean orderType) {
        return cursoMapper.selectByActivo(page, size, orderBy, orderType);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Curso getById(@PathVariable final Long id) {
        return cursoMapper.selectById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String create(@Valid final Curso curso, final BindingResult bindingResult) {
        String result = null;
        if (bindingResult.hasErrors()) {
            result = "ERROR";
        } else {
            cursoMapper.insert(curso);
            result = "OK";
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = "application/json")
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

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
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
