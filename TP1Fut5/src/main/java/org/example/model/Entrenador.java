package org.example.model;

public class Entrenador extends Persona {
    private final int edadEntrenador;

    public Entrenador(String nombre, String apellido, int edadEntrenador) {
        super(nombre, apellido);
        this.edadEntrenador = edadEntrenador;
    }
    // getters
    public int getEdad() {
        return edadEntrenador;
    }
}
