package org.example.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
    private final String nombre;
    private final int fechaCreacion;
    private final Entrenador entrenador;
    private final List<Jugador> players;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipo equipo = (Equipo) o;

        if (fechaCreacion != equipo.fechaCreacion) return false;
        if (!Objects.equals(nombre, equipo.nombre)) return false;
        if (!Objects.equals(entrenador, equipo.entrenador)) return false;
        return players.equals(equipo.players);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", entrenador=" + entrenador +
                ", players=" + players +
                '}';
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + fechaCreacion;
        result = 31 * result + (entrenador != null ? entrenador.hashCode() : 0);
        result = 31 * result + players.hashCode();
        return result;
    }

    public Equipo(String nombre, int fechaCreacion, Entrenador entrenador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.entrenador = entrenador;
        this.players = new ArrayList<>();
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public int getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Jugador> getPlayers() {
        return players;
    }

    public Object getNombreEquipo() {
        return nombre;
    }
}