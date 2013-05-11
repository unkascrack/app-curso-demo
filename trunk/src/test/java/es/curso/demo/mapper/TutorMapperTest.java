package es.curso.demo.mapper;

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
public class TutorMapperTest {

    @Autowired
    private TutorMapper tutorMapper;

    @Test
    public void testTutorMapperNotNull() {
        Assert.assertNotNull(tutorMapper);
    }

    @Test
    public void testSelectAllNotNull() {
        final List<Tutor> tutores = tutorMapper.selectAll();
        Assert.assertFalse(tutores.isEmpty());
    }

    @Test
    public void testSelectByIdNull() {
        final Tutor tutor = tutorMapper.selectById(null);
        Assert.assertNull(tutor);
    }

    @Test
    public void testSelectByIdNotFound() {
        final Tutor tutor = tutorMapper.selectById(null);
        Assert.assertNull(tutor);
    }

    @Test
    public void testSelectByIdOk() {
        final Tutor tutor = tutorMapper.selectById(1l);
        Assert.assertNotNull(tutor);
        Assert.assertSame(1l, tutor.getId());
    }
}
