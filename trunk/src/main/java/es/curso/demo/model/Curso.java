package es.curso.demo.model;

import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;

import flexjson.JSON;

public final class Curso extends FiltroBusqueda {

    private static final long serialVersionUID = 7977276319395424712L;

    @SuppressWarnings("unchecked")
    private static Map<String, String> ATTR_TO_COLUMN = new CaseInsensitiveMap();
    static {
        ATTR_TO_COLUMN.put("id", "id_curso");
        ATTR_TO_COLUMN.put("activo", "lg_activo");
        ATTR_TO_COLUMN.put("titulo", "de_titulo");
        ATTR_TO_COLUMN.put("nivel", "de_nivel");
        ATTR_TO_COLUMN.put("horas", "nu_horas");
        ATTR_TO_COLUMN.put("temario", "de_temario");
    }

    private Long id;

    private Boolean activo;

    @JSON(include = true)
    private Tutor tutor;

    @NotNull
    @Size(max = 100)
    private String titulo;

    @NotNull
    private Nivel nivel;

    @NotNull
    @Min(0)
    @Max(1000)
    private Integer horas;

    @Size(max = 250)
    private String temario;

    @JSON(include = false)
    private String attachment;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(final Boolean activo) {
        this.activo = activo;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @JSON(include = false, name = "tutor_id")
    public Long getIdTutor() {
        return tutor != null ? tutor.getId() : null;
    }

    public void setTutor(final Tutor tutor) {
        this.tutor = tutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = StringUtils.stripToNull(titulo);
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(final Nivel nivel) {
        this.nivel = nivel;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(final Integer horas) {
        this.horas = horas;
    }

    public String getTemario() {
        return temario;
    }

    public void setTemario(final String temario) {
        this.temario = temario;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(final String attachment) {
        this.attachment = attachment;
    }

    @JSON(include = false)
    @Override
    Map<String, String> getAttrToColumnConversion() {
        return ATTR_TO_COLUMN;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso [activo=" + activo + ", tutor=" + tutor + ", titulo=" + titulo + ", nivel=" + nivel + ", horas="
                + horas + ", temario=" + temario + "]";
    }

}
