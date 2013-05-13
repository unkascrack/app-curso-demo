package es.curso.demo.web;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class TutorControllerTest {

    @Autowired
    private TutorController controller;

    @Test
    public void testTutorControllerNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void testListNotNull() {
        final String respone = controller.list();
        Assert.assertNotNull(respone);
    }
}
