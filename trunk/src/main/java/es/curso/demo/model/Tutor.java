package es.curso.demo.model;

import java.io.Serializable;

public class Tutor implements Serializable {

    private static final long serialVersionUID = -923931513142989933L;

    private Long id;
    private String nombre;
    private String apellidos;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(final String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreCompleto() {
        final StringBuilder builder = new StringBuilder();
        if (nombre != null) {
            builder.append(nombre).append(" ");
        }
        if (apellidos != null) {
            builder.append(apellidos);
        }
        return builder.toString().trim();
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
        final Tutor other = (Tutor) obj;
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
        return "Tutor [nombre=" + nombre + ", apellidos=" + apellidos + "]";
    }
}
