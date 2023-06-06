package org.example.model;

import java.math.BigDecimal;

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