package org.example.service;

import org.example.model.Entrenador;
import org.example.model.Equipo;
import org.example.model.Jugador;
import java.util.*;

public class TeamService {
    private final List<Equipo> equipos;
    private final PlayerService playerService = new PlayerService();
    private final List<Jugador> jugadoresImportados;

    public TeamService() {
        this.equipos = new ArrayList<>();
        this.jugadoresImportados = new ArrayList<>();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void crearEquipo() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("** CREAR EQUIPO **");

        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();

        if (buscarEquipoNombre(nombreEquipo) != null) {
            System.out.println("El equipo ya existe. No se puede crear.");
            return;
        }

        System.out.print("Fecha de creación del equipo: ");
        int fechaCreacion = tecla.nextInt();

        System.out.println("** DATOS DEL ENTRENADOR **");

        System.out.print("Nombre del entrenador: ");
        String nombEntrenador = tecla.next();

        System.out.print("Apellido del entrenador: ");
        String apeEntrenador = tecla.next();

        System.out.print("Edad del entrenador: ");
        int edadEntrenador = tecla.nextInt();

        Entrenador entrenador = new Entrenador(nombEntrenador, apeEntrenador, edadEntrenador);
        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);
        equipos.add(equipo);

        int maxPlayers = 5;
        boolean agregarPlayers = true;
        int jugadoresAgregados = 0;

        System.out.println("¿Desea utilizar jugadores importados? (s/n): ");
        String resp = tecla.next();
        if (resp.equalsIgnoreCase("s")) {
            // Agregar jugadores importados al equipo
            for (Jugador jugador : jugadoresImportados) {
                if (equipo.getPlayers().size() >= maxPlayers) {
                    System.out.println("Se alcanzó el máximo permitido de jugadores.");
                    break;
                }

                equipo.getPlayers().add(jugador);
                jugadoresAgregados++;
            }
        } else {
            // Crear jugadores nuevos
            while (agregarPlayers && equipo.getPlayers().size() < maxPlayers) {
                Jugador jugador = playerService.crearJugador();
                if (jugador != null) {
                    equipo.getPlayers().add(jugador);
                    jugadoresAgregados++;
                }
                if (equipo.getPlayers().size() >= maxPlayers) {
                    System.out.println("Se alcanzó el máximo permitido de jugadores.");
                    break;
                }

                System.out.print("¿Desea agregar otro jugador? (s/n): ");
                resp = tecla.next();
                agregarPlayers = resp.equalsIgnoreCase("s");
            }
        }

        System.out.println("Se agregaron " + jugadoresAgregados + " jugadores.");
        System.out.println("** EQUIPO CREADO **");
    }

    public Equipo buscarEquipoNombre(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombreEquipo().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }
}