package es.curso.demo.mapper;

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
public class CursoMapperTest {

    @Autowired
    private CursoMapper cursoMapper;

    @Test
    public void testCursoMapperNotNull() {
        Assert.assertNotNull(cursoMapper);
    }

    @Test
    public void testSelectCountByCursoNull() {
        final Integer nuCursos = cursoMapper.selectCountByCurso(null);
        Assert.assertTrue(nuCursos > 0);
    }

    @Test
    public void testSelectCountByCursoEmpty() {
        final Integer nuCursos = cursoMapper.selectCountByCurso(new Curso());
        Assert.assertTrue(nuCursos > 0);
    }

    @Test
    public void testSelectCountByCursoFilterNoActivo() {
        final Curso curso = new Curso();
        curso.setActivo(false);
        final Integer nuCursos = cursoMapper.selectCountByCurso(curso);
        Assert.assertTrue(nuCursos == 0);
    }

    @Test
    public void testSelectCountByCursoFilterActivo() {
        final Curso curso = new Curso();
        curso.setActivo(true);
        final Integer nuCursos = cursoMapper.selectCountByCurso(curso);
        Assert.assertTrue(nuCursos > 0);
    }

    @Test
    public void testSelectByCursoNull() {
        final List<Curso> cursos = cursoMapper.selectByCurso(null);
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testSelectByCursoEmpty() {
        final List<Curso> cursos = cursoMapper.selectByCurso(new Curso());
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testSelectByCursoFilterNoActivo() {
        final Curso curso = new Curso();
        curso.setActivo(false);
        final List<Curso> cursos = cursoMapper.selectByCurso(curso);
        Assert.assertTrue(cursos.isEmpty());
    }

    @Test
    public void testSelectByCursoFilterActivo() {
        final Curso curso = new Curso();
        curso.setActivo(true);
        final List<Curso> cursos = cursoMapper.selectByCurso(curso);
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testSelectByCursoFilterActivoOrder() {
        final Curso curso = new Curso();
        curso.setActivo(true);
        curso.setOrderBy("titulo");
        curso.setOrderSort(true);
        final List<Curso> cursos = cursoMapper.selectByCurso(curso);
        Assert.assertFalse(cursos.isEmpty());
    }

    @Test
    public void testSelectByCursoFilterPaginated() {
        final Curso curso = new Curso();
        curso.setPageSize(2);

        curso.setPage(1);
        final List<Curso> cursosPage1 = cursoMapper.selectByCurso(curso);
        Assert.assertTrue(cursosPage1.size() == 2);

        curso.setPage(2);
        final List<Curso> cursosPage2 = cursoMapper.selectByCurso(curso);
        Assert.assertTrue(cursosPage2.size() == 2);
    }

    @Test
    public void testSelectByCursoFilterActivoPaginated() {
        final Curso curso = new Curso();
        curso.setActivo(true);
        curso.setOrderBy("titulo");
        curso.setOrderSort(true);
        curso.setPage(1);
        curso.setPageSize(10);
        final List<Curso> cursos = cursoMapper.selectByCurso(curso);
        Assert.assertFalse(cursos.isEmpty());
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
