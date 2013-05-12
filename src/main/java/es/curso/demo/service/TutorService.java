package es.curso.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.curso.demo.mapper.TutorMapper;
import es.curso.demo.model.Tutor;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TutorService {

    @Autowired
    private transient TutorMapper tutorMapper;

    /**
     * @return
     */
    public List<Tutor> findAll() {
        return tutorMapper.selectAll();
    }

    /**
     * @param idTutor
     * @return
     */
    public Tutor findById(final Long idTutor) {
        return tutorMapper.selectById(idTutor);
    }
}
