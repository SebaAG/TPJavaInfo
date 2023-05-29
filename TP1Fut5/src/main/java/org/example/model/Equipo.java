package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private final String nombre;
    private final String fechaCreacion;
    private final Entrenador entrenador;
    private final List<Jugador> players;

    public Equipo(String nombre, String fechaCreacion, Entrenador entrenador) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.fechaCreacion = fechaCreacion;
        this.players = new ArrayList<>();
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Jugador> getPlayers() {
        return players;
    }
}
