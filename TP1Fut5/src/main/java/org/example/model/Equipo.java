package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private final String nombre;
    private final int fechaCreacion;
    private final Entrenador entrenador;
    private final List<Jugador> players;

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
