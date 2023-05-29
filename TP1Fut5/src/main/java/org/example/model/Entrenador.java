package org.example.model;

public class Entrenador {
    private final String nombre;
    private final String apellido;
    private final int edadEntrenador;

    public Entrenador(String nombre, String apellido, int edadEntrenador) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edadEntrenador = edadEntrenador;
    }
    // getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edadEntrenador;
    }
}
