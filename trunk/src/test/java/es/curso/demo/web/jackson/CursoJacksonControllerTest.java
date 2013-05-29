package es.curso.demo.web.jackson;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

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
    public void testSearchNuCursosNotNull() {
        final Curso curso = new Curso();
        curso.setTotal(true);
        final Integer nuCursos = (Integer) controller.search(curso);
        Assert.assertTrue(nuCursos > 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSearchListCursosNotNull() {
        final List<Curso> cursos = (List<Curso>) controller.search(null);
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testGetByIdNull() {
        final ResponseEntity<Curso> response = controller.getById(null);
        Assert.assertNull(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetByIdNotFound() {
        final ResponseEntity<Curso> response = controller.getById(-1l);
        Assert.assertNull(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetByIdFound() {
        final ResponseEntity<Curso> response = controller.getById(1l);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = Exception.class)
    public void testCreateEmpty() throws IOException {
        controller.create(new Curso(), getBindingResult(true));
    }

    @Test
    public void testCreateOk() throws IOException {
        final Curso curso = new Curso();
        curso.setTitulo("titulo");
        curso.setActivo(true);
        curso.setHoras(10);
        curso.setNivel(Nivel.BASICO);
        curso.setTutor(new Tutor(1l));
        controller.create(curso, getBindingResult(false));
    }

    private BindingResult getBindingResult(final boolean hasErrors) {
        final BindingResult result = EasyMock.createMock(BindingResult.class);
        EasyMock.expect(result.hasErrors()).andReturn(hasErrors);
        return result;
    }
}
