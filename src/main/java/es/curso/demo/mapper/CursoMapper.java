package es.curso.demo.mapper;

import java.util.List;

import es.curso.demo.model.Curso;

public interface CursoMapper {

    /**
     * @return
     */
    Curso selectById(Long idCurso);

    /**
     * @return
     */
    Integer selectCountByCurso(Curso curso);

    /**
     * @param curso
     * @return
     */
    List<Curso> selectByCurso(Curso curso);

    /**
     * @param curso
     */
    void insert(Curso curso);

    /**
     * @param curso
     */
    void update(Curso curso);

    /**
     * @param curso
     */
    void delete(Curso curso);
}
