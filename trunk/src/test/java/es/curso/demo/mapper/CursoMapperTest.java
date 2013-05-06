package es.curso.demo.mapper;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class CursoMapperTest {

    @Autowired
    private CursoMapper cursoMapper;

    @Test
    public void testCursoMapperNotNull() {
        Assert.assertNotNull(cursoMapper);
    }
}
