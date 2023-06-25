package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Jugador extends Persona {
    private final String id;
    private final BigDecimal altura;
    private final String posicion;
    private final int cantGoles;
    private final int cantPart;
    private final boolean esCapi;
    private final int numCami;

    public Jugador(String id, String nombre, String apellido, BigDecimal altura, String posicion,
                   int cantGoles, int cantPart, boolean esCapi, int numCami) {
        super(nombre, apellido);
        this.id = id;
        this.altura = altura;
        this.posicion = posicion;
        this.cantGoles = cantGoles;
        this.cantPart = cantPart;
        this.esCapi = esCapi;
        this.numCami = numCami;
    }


    // getters y setters

    public String getId() {
        return id;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public String getPosicion() {
        return posicion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id='" + id + '\'' +
                ", altura=" + altura +
                ", posicion='" + posicion + '\'' +
                ", cantGoles=" + cantGoles +
                ", cantPart=" + cantPart +
                ", esCapi=" + esCapi +
                ", numCami=" + numCami +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;

        if (cantGoles != jugador.cantGoles) return false;
        if (cantPart != jugador.cantPart) return false;
        if (esCapi != jugador.esCapi) return false;
        if (numCami != jugador.numCami) return false;
        if (!Objects.equals(id, jugador.id)) return false;
        if (!Objects.equals(altura, jugador.altura)) return false;
        return Objects.equals(posicion, jugador.posicion);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (altura != null ? altura.hashCode() : 0);
        result = 31 * result + (posicion != null ? posicion.hashCode() : 0);
        result = 31 * result + cantGoles;
        result = 31 * result + cantPart;
        result = 31 * result + (esCapi ? 1 : 0);
        result = 31 * result + numCami;
        return result;
    }

    public int getCantGoles() {
        return cantGoles;
    }

    public int getCantPart() {
        return cantPart;
    }


    public boolean isEsCapi() {
        return esCapi;
    }

    public int getNumCami() {
        return numCami;
    }

}