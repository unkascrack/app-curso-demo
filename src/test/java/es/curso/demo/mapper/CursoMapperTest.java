package es.curso.demo.mapper;

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
public class CursoMapperTest {

    @Autowired
    private CursoMapper cursoMapper;

    @Test
    public void testCursoMapperNotNull() {
        Assert.assertNotNull(cursoMapper);
    }

    @Test
    public void testInsert() {
        final Curso curso = new Curso();
        curso.setActivo(false);
        curso.setTitulo("Mi Curso");
        curso.setNivel(Nivel.BASICO);
        curso.setHoras(10);
        curso.setTutor(new Tutor(1l));
        cursoMapper.insert(curso);
        Assert.assertNotNull(curso.getId());
    }
}
