package es.curso.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.curso.demo.mapper.TutorMapper;
import es.curso.demo.model.Tutor;

@RequestMapping("/tutores")
@Controller
public class TutorController {

    @Autowired
    private transient TutorMapper tutorMapper;

    @RequestMapping
    public List<Tutor> list() {
        return tutorMapper.selectAll();
    }
}
