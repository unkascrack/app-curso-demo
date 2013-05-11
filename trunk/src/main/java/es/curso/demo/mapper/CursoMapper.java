package es.curso.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.curso.demo.model.Curso;

public interface CursoMapper {

    /**
     * @return
     */
    Curso selectById(Long idCurso);

    /**
     * @param idCurso
     * @return
     */
    Curso selectTemarioById(Long idCurso);

    /**
     * @return
     */
    Integer selectCountByActivo();

    /**
     * @param page
     * @param size
     * @param orderBy
     * @param orderType
     * @return
     */
    List<Curso> selectByActivo(@Param("page") Integer page, @Param("size") Integer size,
            @Param("orderBy") String orderBy, @Param("orderType") Boolean orderType);

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
