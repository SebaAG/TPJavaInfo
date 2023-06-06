package org.example.service;

import org.example.model.Entrenador;
import org.example.model.Equipo;
import org.example.model.Jugador;
import java.util.*;

public class TeamService {
    public static final List<Equipo> equipos = new ArrayList<>();

    public static void crearEquipo() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("** CREAR EQUIPO **");

        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();

        if (buscarEquipoNombre(nombreEquipo) != null) {
            System.out.println("El equipo ya existe. No se puede crear.");
            return;
        }

        System.out.print("Fecha de creacion del equipo: ");
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
        while (agregarPlayers && equipo.getPlayers().size() < maxPlayers) {
            Jugador jugador = PlayerService.crearJugador();
            if (jugador != null) {
                equipo.getPlayers().add(jugador);
            }
            if (equipo.getPlayers().size() >= maxPlayers) {
                System.out.println("Se alcanzo el maximo permitido de jugadores!");
                break;
            }

            System.out.print("Desea agregar otro jugador?");
            String resp = tecla.next();
            agregarPlayers = resp.equalsIgnoreCase("s");
        }


        System.out.println("** EQUIPO CREADO **");
    }

    private static Equipo buscarEquipoNombre(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombreEquipo().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }
}