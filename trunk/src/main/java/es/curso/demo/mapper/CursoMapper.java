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
     * @return
     */
    Integer selectCountByActivo();

    /**
     * @param firstResult
     * @param maxResults
     * @param orderBy
     * @param orderType
     * @return
     */
    List<Curso> selectByActivo(@Param("firstResult") int firstResult, @Param("maxResults") int maxResults,
            @Param("orderBy") String orderBy, @Param("orderType") boolean orderType);

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
