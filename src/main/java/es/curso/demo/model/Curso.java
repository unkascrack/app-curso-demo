package es.curso.demo.model;

import java.io.Serializable;

public class Curso implements Serializable {

    private static final long serialVersionUID = 7977276319395424712L;

    private Long id;
    private boolean activo = false;
    private Tutor tutor;
    private String titulo;
    private Nivel nivel;
    private Integer horas;
    private byte[] temario;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(final boolean activo) {
        this.activo = activo;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(final Tutor tutor) {
        this.tutor = tutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
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

    public byte[] getTemario() {
        return temario;
    }

    public void setTemario(final byte[] temario) {
        this.temario = temario;
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
                + horas + "]";
    }
}
