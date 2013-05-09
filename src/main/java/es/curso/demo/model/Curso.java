package es.curso.demo.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Curso implements Serializable {

    private static final long serialVersionUID = 7977276319395424712L;

    private Long id;

    private boolean activo = false;

    @NotNull
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
    private String nombreTemario;

    private byte[] contenidoTemario;

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

    public String getNombreTemario() {
        return nombreTemario;
    }

    public void setNombreTemario(final String nombreTemario) {
        this.nombreTemario = nombreTemario;
    }

    public byte[] getContenidoTemario() {
        return contenidoTemario;
    }

    public void setContenidoTemario(final byte[] contenidoTemario) {
        this.contenidoTemario = contenidoTemario;
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