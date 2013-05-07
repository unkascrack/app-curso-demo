package es.curso.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.curso.demo.mapper.TutorMapper;
import es.curso.demo.model.Tutor;

@RequestMapping("/tutores")
@Controller
public class TutorController {

    @Autowired
    private transient TutorMapper tutorMapper;

    /**
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Tutor> list() {
        return tutorMapper.selectAll();
    }
}
