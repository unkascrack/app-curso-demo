package es.curso.demo.web.jackson;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.curso.demo.model.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class TutorJacksonControllerTest {

    @Autowired
    private TutorJacksonController controller;

    @Test
    public void testTutorControllerNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void testListNotNull() {
        final List<Tutor> tutores = controller.list();
        Assert.assertFalse(tutores.isEmpty());
    }
}
