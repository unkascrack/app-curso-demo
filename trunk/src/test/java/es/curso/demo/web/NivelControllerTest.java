package es.curso.demo.web;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class NivelControllerTest {

    @Autowired
    private NivelController nivelController;

    @Test
    public void testNivelControllerNotNull() {
        Assert.assertNotNull(nivelController);
    }

    @Test
    public void testListNotNull() {
        final String response = nivelController.list();
        Assert.assertNotNull(response);
    }
}
