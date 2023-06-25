package org.example.model;

public class Entrenador extends Persona {
    private final int edadEntrenador;

    public Entrenador(String nombre, String apellido, int edadEntrenador) {
        super(nombre, apellido);
        this.edadEntrenador = edadEntrenador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Entrenador that = (Entrenador) o;

        return edadEntrenador == that.edadEntrenador;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + edadEntrenador;
        return result;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "edadEntrenador=" + edadEntrenador +
                '}';
    }

    // getters
    public int getEdad() {
        return edadEntrenador;
    }
}