package es.curso.demo.mapper;

import java.util.List;

import es.curso.demo.model.Tutor;

public interface TutorMapper {

    /**
     * @return
     */
    List<Tutor> selectAll();

    /**
     * @param id
     * @return
     */
    Tutor selectById(Long idTutor);
}
