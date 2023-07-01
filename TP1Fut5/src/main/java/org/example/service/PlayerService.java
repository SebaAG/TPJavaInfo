package org.example.service;

import org.example.model.Equipo;
import org.example.model.Jugador;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class PlayerService {

    private TeamService teamService;
    private Scanner tecla;

    public PlayerService(TeamService teamService) {
        this.teamService = teamService;
        this.tecla = new Scanner(System.in);
    }

    // CREADOR DE JUGADORES Y DONDE SE AGREGAN SU INFORMACION
    public Jugador crearJugador() {
        tecla = new Scanner(System.in);
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

        System.out.print("Número de la camiseta del jugador: ");
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
            System.out.println("Ya existe un capitán en este equipo o un jugador con el mismo número de camiseta." +
                    " Seleccione otra opción.");
            return null;
        }

        String id = generarIdJugador();

        Jugador jugador = new Jugador(id, nombre, apellido, altura, posicion, cantGoles, cantPart, esCapi, numCami);

        System.out.println(" ** JUGADOR CREADO **");

        return jugador;
    }

    private String obtenerNombreEquipo() {
        System.out.println("Ingrese nombre del equipo al que pertenece: ");
        return tecla.next();
    }

    private String obtenerPosicionValid() {
        String pos = null;
        boolean posValid = false;
        while (!posValid) {
            System.out.print("Posición del jugador (arquero/defensa/mediocampista/delantero): ");
            pos = tecla.next();
            pos = pos.toUpperCase();

            switch (pos) {
                case "ARQUERO", "DEFENSA", "MEDIOCAMPISTA", "DELANTERO" -> posValid = true;
                default -> System.out.println("Posición invalida");
            }
        }
        return pos;
    }

    private boolean esCapitan() {
        System.out.print("Es capitán? (s/n): ");
        String esCapis = tecla.next();
        boolean esCapi;

        if (esCapis.equalsIgnoreCase("s")) {
            esCapi = true;
        } else if (esCapis.equalsIgnoreCase("n")) {
            esCapi = false;
        } else {
            System.out.println("Ingreso incorrecto, se asumirá como NO capitán");
            esCapi = false;
        }
        return esCapi;
    }
    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR COMO CAPITAN
    private boolean tieneCapitan(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.isEsCapi()) {
                return true;
            }
        }
        return false;
    }
    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR CON EL NUMERO DE CAMISETA ESPECIFICADO
    private boolean tieneNumCamiseta(List<Jugador> jugadores, int numCami) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumCami() == numCami) {
                return true;
            }
        }
        return false;
    }
    // GENERA UN IDENTIFICADOR UNICO UTILIZANDO UUID
    private String generarIdJugador() {
        return UUID.randomUUID().toString();
    }

    private Equipo buscarEquipoNombre(String nombreEquipo) {
        TeamService teamService = new TeamService();
        List<Equipo> equipos = teamService.getEquipos();
        for (Equipo equipo : equipos) {
            if (equipo.getNombreEquipo().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    public void buscarJugadorNombre() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("** BUSCAR JUGADOR POR NOMBRE **");

        System.out.print("Nombre del jugador: ");
        String nombreJugador = tecla.nextLine();

        boolean encontrado = false;

        for (Equipo equipo : teamService.getEquipos()) {
            List<Jugador> jugadores = equipo.getPlayers();

            for (Jugador jugador : jugadores) {
                if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                    encontrado = true;
                    System.out.println("Nombre del jugador: " + jugador.getNombre() + " " + jugador.getApellido());
                    System.out.println("Equipo: " + equipo.getNombre());
                    System.out.println("Altura: " + jugador.getAltura());
                    System.out.println("Posicion: " + jugador.getPosicion());
                    System.out.println("Capitan: " + jugador.isEsCapi());
                    System.out.println("Numero de camiseta: " + jugador.getNumCami());
                    System.out.println("Cantidad de partidos jugados: " + jugador.getCantPart());
                    System.out.println("Cantidad de goles: " + jugador.getCantGoles());
                    System.out.println("ID: " + jugador.getId());
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró jugador!");
        }
    }
}