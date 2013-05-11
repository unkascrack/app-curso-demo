package es.curso.demo.web;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class CursoControllerTest {

    @Autowired
    private CursoController controller;

    @Test
    public void testCursoControllerNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void testListNotNull() {
        final String response = controller.list(null, null, null, null);
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
}
