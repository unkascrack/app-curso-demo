package es.curso.demo.web.flexjson;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.curso.demo.model.Curso;
import es.curso.demo.model.Nivel;
import es.curso.demo.model.Tutor;
import es.curso.demo.service.JSONService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class CursoFlexJSONControllerTest {

    @Autowired
    private CursoFlexJSONController controller;

    @Autowired
    private JSONService jsonService;

    @Test
    public void testCursoControllerNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void testListNotNull() {
        final String response = controller.list(null);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void testGetByIdNull() {
        final String response = controller.getById(null);
        Assert.assertNull(response);
    }

    @Test
    public void testGetByIdNotFound() {
        final String response = controller.getById(-1l);
        Assert.assertNull(response);
    }

    @Test
    public void testGetByIdFound() {
        final String response = controller.getById(1l);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains("\"id\":1"));
    }

    @Test(expected = Exception.class)
    public void testCreateEmpty() throws IOException {
        controller.create("");
    }

    @Test
    public void testCreateOk() throws IOException {
        final Curso curso = new Curso();
        curso.setTitulo("titulo");
        curso.setActivo(true);
        curso.setHoras(10);
        curso.setNivel(Nivel.BASICO);
        curso.setTutor(new Tutor(1l));
        controller.create(jsonService.serialize("curso", curso));
    }
}
