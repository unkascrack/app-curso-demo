package es.curso.demo.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.curso.demo.mapper.CursoMapper;
import es.curso.demo.model.Curso;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CursoService {

    static final Logger logger = LoggerFactory.getLogger(CursoService.class);

    private static final String BASE_PATH;
    static {
        final URL tempPath = Thread.currentThread().getContextClassLoader().getResource("log4j.properties");
        String path = null;
        if (tempPath != null && StringUtils.contains(tempPath.getPath(), "/WEB-INF/")) {
            path = StringUtils.substringBeforeLast(tempPath.getPath(), "/WEB-INF/");
        } else if (tempPath != null && StringUtils.contains(tempPath.getPath(), "/target/")) {
            path = StringUtils.substringBeforeLast(tempPath.getPath(), "/target/") + "/target";
        } else {
            path = System.getProperty("user.dir");
        }
        BASE_PATH = path + "/docs";
    }

    @Autowired
    private transient CursoMapper cursoMapper;

    /**
     * @param idCurso
     * @return
     */
    public Curso findById(final Long idCurso) {
        return cursoMapper.selectById(idCurso);
    }

    /**
     * @param curso
     * @return
     */
    public List<Curso> findByCurso(final Curso curso) {
        return cursoMapper.selectByCurso(curso);
    }

    /**
     * @param curso
     * @return
     */
    public Integer findTotalByCurso(final Curso curso) {
        return cursoMapper.selectCountByCurso(curso);
    }

    /**
     * @param curso
     * @throws IOException
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { IOException.class })
    public void insert(final Curso curso) throws IOException {
        cursoMapper.insert(curso);
        saveTemario(curso);
    }

    /**
     * @param curso
     * @throws IOException
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { IOException.class })
    public void update(final Curso curso) throws IOException {
        cursoMapper.update(curso);
        saveTemario(curso);
    }

    /**
     * @param curso
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(final Long idCurso) {
        final Curso curso = findById(idCurso);
        cursoMapper.delete(curso);
    }

    /**
     * @param curso
     * @throws IOException
     */
    private void saveTemario(final Curso curso) throws IOException {
        if (curso != null && curso.getTemario() != null && curso.getAttachment() != null) {
            final String path = BASE_PATH + "/" + curso.getId() + "/" + curso.getTemario();
            final byte[] attachment = Base64.decodeBase64(StringUtils.substringAfter(curso.getAttachment(), "base64,"));
            FileUtils.writeByteArrayToFile(new File(path), attachment, false);
        }
    }
}
