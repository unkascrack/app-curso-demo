package es.curso.demo.mapper;

import java.util.List;

import es.curso.demo.model.Tutor;

public interface TutorMapper {

	/**
	 * @return
	 */
	List<Tutor> selectAll();
}
