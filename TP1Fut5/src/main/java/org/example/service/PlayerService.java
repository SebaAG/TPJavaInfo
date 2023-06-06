package org.example.service;

import org.example.model.Equipo;
import org.example.model.Jugador;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class PlayerService {
    private static final Scanner tecla = new Scanner(System.in);

    // CREADOR DE JUGADORES Y DONDE SE AGREGAN SU INFORMACION
    static Jugador crearJugador() {
        System.out.println("** CREAR JUGADOR ** ");

        System.out.print("Nombre del jugador: ");
        String nombre = tecla.next();

        System.out.print("Apellido del jugador: ");
        String apellido = tecla.next();

        System.out.print("Altura del jugador: ");
        BigDecimal altura = tecla.nextBigDecimal();

        String posicion = obtenerPosicionValid();

        System.out.print("Cantidad de goles del jugador: ");
        int cantGoles = tecla.nextInt();

        System.out.print("Cantidad de partidos jugados: ");
        int cantPart = tecla.nextInt();

        boolean esCapi = esCapitan();

        System.out.print("Numero de la camiseta del jugador: ");
        int numCami = tecla.nextInt();

        String nombEquip = obtenerNombreEquipo();

        Equipo equipo = buscarEquipoNombre(nombEquip);

        boolean equipoExist = false;
        if (equipo == null) {
            equipo = new Equipo(nombEquip, -1, null);
        } else {
            equipoExist = true;
        }

        if (equipoExist && ((esCapi && tieneCapitan(equipo.getPlayers())) ||
                tieneNumCamiseta(equipo.getPlayers(), numCami))) {
            System.out.println("Ya existe un capitan en este equipo o un jugador con el mismo numero de camiseta." +
                    " Seleccione otra opcion.");
            return null;
        }

        String id = generarIdJugador();

        Jugador jugador = new Jugador(id, nombre, apellido, altura, posicion, cantGoles, cantPart, esCapi, numCami);

        System.out.println(" ** JUGADOR CREADO **");

        return jugador;
    }

    private static String obtenerNombreEquipo() {
        System.out.println("Ingrese nombre del equipo al que pertenece: ");
        return tecla.next();
    }

    private static String obtenerPosicionValid() {
        String pos = null;
        boolean posValid = false;
        while (!posValid) {
            System.out.print("Posicion del jugador (arquero/defensa/mediocampista/delantero): ");
            pos = tecla.next();
            pos = pos.toUpperCase();

            switch (pos) {
                case "ARQUERO", "DEFENSA", "MEDIOCAMPISTA", "DELANTERO" -> posValid = true;
                default -> System.out.println("Posicion invalida");
            }
        }
        return pos;
    }

    private static boolean esCapitan() {
        System.out.print("Es capitan? (s/n): ");
        String esCapis = tecla.next();
        boolean esCapi;

        if (esCapis.equalsIgnoreCase("s")) {
            esCapi = true;
        } else if (esCapis.equalsIgnoreCase("n")) {
            esCapi = false;
        } else {
            System.out.println("Ingreso incorrecto, se asumira como NO capitan");
            esCapi = false;
        }
        return esCapi;
    }
    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR COMO CAPITAN
    private static boolean tieneCapitan(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.isEsCapi()) {
                return true;
            }
        }
        return false;
    }
    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR CON EL NUMERO DE CAMISETA ESPECIFICADO
    private static boolean tieneNumCamiseta(List<Jugador> jugadores, int numCami) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumCami() == numCami) {
                return true;
            }
        }
        return false;
    }
    // GENERA UN IDENTIFICADOR UNICO UTILIZANDO UUID
    private static String generarIdJugador() {
        return UUID.randomUUID().toString();
    }

    private static Equipo buscarEquipoNombre(String nombreEquipo) {
        for (Equipo equipo : TeamService.equipos) {
            if (equipo.getNombreEquipo().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }
}