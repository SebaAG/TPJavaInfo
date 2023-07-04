package org.example.service;

import org.example.model.Entrenador;
import org.example.model.Equipo;
import org.example.model.Jugador;
import java.util.*;

public class TeamService {
    private final List<Equipo> equipos;
    private final PlayerService playerService = new PlayerService(this);
    private final CoachService coachService = new CoachService();
    private final Set<Jugador> jugadoresImportados;

    public void setJugadoresImportados(Set<Jugador> jugadoresImportados) {
        this.jugadoresImportados.addAll(jugadoresImportados);
    }

    public TeamService() {
        this.equipos = new ArrayList<>();
        this.jugadoresImportados = new HashSet<>();
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

        Entrenador entrenador = coachService.crearEntrenador();

        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);
        equipos.add(equipo);

        int maxPlayers = 5;
        boolean agregarPlayers = true;
        int jugadoresAgregados = 0;

        System.out.println("Desea utilizar jugadores importados? (s/n): ");
        String resp = tecla.next();
        if (resp.equalsIgnoreCase("s")) {
            // Agregar jugadores importados al equipo
            for (Jugador jugador : jugadoresImportados) {
                if (equipo.getPlayers().size() >= maxPlayers) {
                    System.out.println("Se alcanzo el máximo permitido de jugadores.");
                    break;
                }
                if (verifJugImp(jugador)) {
                    System.out.println("El jugador importado " + jugador.getNombre() + jugador.getApellido()
                    + " ya esta en otro equipo!");
                    continue;
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
                    System.out.println("Se alcanzo el máximo permitido de jugadores.");
                    break;
                }

                System.out.print("Desea agregar otro jugador? (s/n): ");
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

    public boolean verifJugImp(Jugador jugador) {
        for (Equipo equipo : equipos) {
            if (equipo.getPlayers().contains(jugador)) {
                return true;
            }
        }
        return false;
    }

    public void buscarEquipoNombre() {
        Scanner tecla = new Scanner(System.in);
        System.out.println(" ** BUSCAR EQUIPO POR NOMBRE **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.nextLine();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creación: " + equipo.getFechaCreacion());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombre() + " " +
                        equipo.getEntrenador().getApellido() + " Edad: " + equipo.getEntrenador().getEdad());
                System.out.println("Capitán del equipo: " + obtenerNombreCapitan(equipo));
                System.out.println("Lista de jugadores ordenados por nombre: ");

                List<Jugador> jugadores = equipo.getPlayers();
                jugadores.sort(Comparator.comparing(Jugador::getNombre));

                for (Jugador jugador : jugadores) {
                    System.out.println(jugador.getNombre() + " " + jugador.getApellido());
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró equipo con ese nombre!");
        }
    }

    private String obtenerNombreCapitan(Equipo equipo) {
        for (Jugador jugador : equipo.getPlayers()) {
            if (jugador.isEsCapi()) {
                return jugador.getNombre() + " " + jugador.getApellido();
            }
        }
        return "No hay capitan asignado!";
    }

    public void buscarEquipoCamiseta() {
        Scanner tecla = new Scanner(System.in);
        System.out.println(" ** BUSCAR EQUIPO ORDENADO POR Nº CAMISETA **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.nextLine();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creación: " + equipo.getFechaCreacion());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombre() + " " +
                        equipo.getEntrenador().getApellido() + " Edad: " + equipo.getEntrenador().getEdad());
                System.out.println("Lista de jugadores ordenados por su numero de camiseta: ");

                List<Jugador> jugadores = equipo.getPlayers();
                jugadores.sort(Comparator.comparingInt(Jugador::getNumCami));

                for (Jugador jugador : jugadores) {
                    System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " " +
                            jugador.getNumCami());
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún equipo con ese nombre!");
        }
    }

    public void buscarEquipoPosicion() {
        Scanner tecla = new Scanner(System.in);
        System.out.println(" ** BUSCAR EQUIPO ORDENADO POR Nº CAMISETA Y POSICION **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.nextLine();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creación: " + equipo.getFechaCreacion());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombre() + " " +
                        equipo.getEntrenador().getApellido() + " Edad: " + equipo.getEntrenador().getEdad());
                System.out.println("Lista de jugadores ordenados por su numero de camiseta y por posición: ");

                List<Jugador> jugadores = equipo.getPlayers();
                jugadores.sort(Comparator.comparing(Jugador::getPosicion).thenComparingInt(Jugador::getNumCami));

                for (Jugador jugador : jugadores) {
                    System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " -Camiseta: " +
                            jugador.getNumCami() + " -Posición: " + jugador.getPosicion());
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún equipo con ese nombre!");
        }
    }
}