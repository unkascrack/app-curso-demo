package es.curso.demo.web.jackson;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.curso.demo.model.Curso;
import es.curso.demo.model.Nivel;
import es.curso.demo.model.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class CursoJacksonControllerTest {

    @Autowired
    private CursoJacksonController controller;

    @Test
    public void testCursoControllerNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void testListNotNull() {
        final List<Curso> cursos = controller.list(null);
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testGetByIdNull() {
        final Curso curso = controller.getById(null);
        Assert.assertNull(curso);
    }

    @Test
    public void testGetByIdNotFound() {
        final Curso curso = controller.getById(-1l);
        Assert.assertNull(curso);
    }

    @Test
    public void testGetByIdFound() {
        final Curso curso = controller.getById(1l);
        Assert.assertNotNull(curso);
        Assert.assertTrue(curso.getId().equals(1l));
    }

    @Test(expected = Exception.class)
    public void testCreateEmpty() throws IOException {
        controller.create(new Curso());
    }

    @Test
    public void testCreateOk() throws IOException {
        final Curso curso = new Curso();
        curso.setTitulo("titulo");
        curso.setActivo(true);
        curso.setHoras(10);
        curso.setNivel(Nivel.BASICO);
        curso.setTutor(new Tutor(1l));
        controller.create(curso);
    }

}
