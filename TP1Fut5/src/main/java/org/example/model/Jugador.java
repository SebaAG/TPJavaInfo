package org.example.model;

import java.math.BigDecimal;

public class Jugador {
    private final String id;
    private final String nombre;
    private final String apellido;
    private final BigDecimal altura;
    private final String posicion;
    private final int cantGoles;
    private final int cantPart;
    private final boolean esCapi;
    private final int numCami;

    public Jugador(String id, String nombre, String apellido, BigDecimal altura, String posicion,
                   int cantGoles, int cantPart, boolean esCapi, int numCami) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
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

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
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

