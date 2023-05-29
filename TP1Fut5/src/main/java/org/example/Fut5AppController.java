package org.example;

import org.example.model.Jugador;
import org.example.model.Entrenador;
import org.example.model.Equipo;
import java.math.BigDecimal;
import java.util.*;

public class Fut5AppController {
    static final Scanner tecla = new Scanner(System.in);
    private static final List<Equipo> equipos = new ArrayList<>();
    private static final Service service = new Service();

    // EJECUTA EL MENU DE OPCIONES EN EL METODO PRINCIPAL
    public void run() {
        int opc;
        do {
            showMenu();
            opc = obtOpc();
            ejectOpc(opc);
        } while (opc != 9);
    }

    // SE MUESTRA EL MENU DE LA APP
    private static void showMenu() {
        System.out.println("*** MENU ***");
        System.out.println("1. CREAR EQUIPO");
        System.out.println("2. BUSCAR JUGADOR POR NOMBRE");
        System.out.println("3. BUSCAR EQUIPO POR NOMBRE (ordenado por nombre del jugador)");
        System.out.println("4. BUSCAR EQUIPO POR NOMBRE (ordenado por numero de camiseta)");
        System.out.println("5. BUSCAR EQUIPO POR NOMBRE (ordenador por nombre de jugador y por numero de camiseta)");
        System.out.println("6. ELIMINAR EQUIPO POR NOMBRE");
        System.out.println("7. IMPORTAR LISTA DE JUGADORES DE UN EQUIPO");
        System.out.println("8. EXPORTAR LISTA DE JUGADORES DE UN EQUIPO");
        System.out.println("9. SALIR");
    }

    // SE INGRESA EL NUMERO DESEADO PARA CADA OPCION DEL MENU
    private static int obtOpc() {
        System.out.println("Seleccione que desea realizar: ");
        return tecla.nextInt();
    }

    // SWITCH PARA EJECUTAR LA OPCIÓN SELECCIONADA
    private static void ejectOpc(int opc) {
        switch (opc) {
            case 1 -> crearEquipo();
            case 2 -> buscarJugadorNombre();
            case 3 -> buscarEquipoNombre();
            case 4 -> buscarEquipoCamiseta();
            case 5 -> buscarEquipoPosicion();
            case 6 -> eliminarEquipo();
            case 7 -> importarJugadores();
            case 8 -> exportarJugadores();
            case 9 -> System.out.println("Adios!");
            default -> System.out.println("Opcion invalida.");
        }
    }

    // CREADOR DEL EQUIPO Y TAMBIEN DEL ENTRENADOR
    private static void crearEquipo() {
        System.out.println("** CREAR EQUIPO **");

        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();
        System.out.print("Fecha de creacion del equipo: ");
        String fechaCreacion = tecla.next();

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

        boolean agregarPlayers = true;
        while (agregarPlayers) {
            Jugador jugador = crearJugador();
            if (jugador != null) {
                equipo.getPlayers().add(jugador);
            }
            System.out.print("Desea agregar otro jugador?");
            String resp = tecla.next();
            agregarPlayers = resp.equalsIgnoreCase("s");
        }


        System.out.println("** EQUIPO CREADO **");
    }

    // CREADOR DE JUGADORES Y DONDE SE AGREGAN SU INFORMACION
    private static Jugador crearJugador() {
        System.out.println("** CREAR JUGADOR ** ");

        System.out.print("Nombre del jugador: ");
        String nombre = tecla.next();

        System.out.print("Apellido del jugador: ");
        String apellido = tecla.next();

        System.out.print("Altura del jugador: ");
        BigDecimal altura = tecla.nextBigDecimal();

        String posicion = null;
        boolean posicionValida = false;
        while (!posicionValida) {
            System.out.print("Posicion del jugador (arquero/defensa/mediocampista/delantero): ");
            posicion = tecla.next();
            posicion = posicion.toUpperCase();
            // VALIDACION DE LA POSICION DEL JUGADOR
            switch (posicion) {
                case "ARQUERO", "DEFENSA", "MEDIOCAMPISTA", "DELANTERO" -> posicionValida = true;
                default -> System.out.println("Posicion invalida, intente nuevamente!");
            }
        }

        System.out.print("Cantidad de goles del jugador: ");
        int cantGoles = tecla.nextInt();

        System.out.print("Cantidad de partidos jugados: ");
        int cantPart = tecla.nextInt();

        System.out.print("Es capitán? (s/n): ");
        String esCapitanStr = tecla.next();
        boolean esCapi;

        if (esCapitanStr.equalsIgnoreCase("s")) {
            esCapi = true;
        } else if (esCapitanStr.equalsIgnoreCase("n")) {
            esCapi = false;
        } else {
            System.out.println("Opción inválida. Se asumirá que no es capitán.");
            esCapi = false;
        }

        System.out.print("Numero de la camiseta del jugador: ");
        int numCami = tecla.nextInt();

        Equipo equipo;
        boolean equipoExist = false;

        System.out.print("Ingrese nombre del equipo al que pertenece el jugador: ");
        String nombreEquipo = tecla.next();
        equipo = buscarEquipoNombre(nombreEquipo);

        if (equipo == null) {
            equipo = new Equipo(nombreEquipo, "Fecha desconocida", null);
        } else {
            equipoExist = true;
        }

        if (equipoExist && ((esCapi && tieneCapitan(equipo)) || tieneNumCamiseta(equipo, numCami))) {
            System.out.println("Ya existe un capitan en este equipo o un jugador con el mismo numero de camiseta." +
                    " Seleccione otra opcion.");
            return null;
        }

        String id = generarIdJugador();

        Jugador jugador = new Jugador(id, nombre, apellido, altura, posicion,
                cantGoles, cantPart, esCapi, numCami);

        System.out.println(" ** JUGADOR CREADO **");

        return jugador;
    }

    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR COMO CAPITAN
    private static boolean tieneCapitan(Equipo equipo) {
        for (Jugador jugador : equipo.getPlayers()) {
            if (jugador.isEsCapi()) {
                return true;
            }
        }
        return false;
    }

    // VERIFICA SI EL EQUIPO TIENE AL MENOS UN JUGADOR CON EL NUMERO DE CAMISETA ESPECIFICADO
    private static boolean tieneNumCamiseta(Equipo equipo, int numCami) {
        for (Jugador jugador : equipo.getPlayers()) {
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

    // BUSCADOR DE JUGADORES DONDE SE MUESTRA SU INFORMACION
    private static void buscarJugadorNombre() {
        System.out.println("** BUSCAR JUGADOR POR NOMBRE **");

        System.out.print("Nombre del jugador: ");
        String nombreJugador = tecla.next();

        boolean encontrado = false;

        for (Equipo equipo : equipos) {
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
            System.out.println("No se encontro jugador!");
        }
    }

    // BUSCADOR DE EQUIPO DONDE SE MUESTRAN LOS JUGADORES ORDENADOS ALFABETICAMENTE
    private static void buscarEquipoNombre() {
        System.out.println(" ** BUSCAR EQUIPO POR NOMBRE **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creacion: " + equipo.getFechaCreacion());
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
            System.out.println("No se encontro equipo con ese nombre!");
        }
    }
    // OBTIENE EL NOMBRE DEL JUGADOR QUE ES CAPITAN DEL EQUIPO
    // SI EL EQUIPO NO TIENE CAPITAN ASIGNADO, DEVUELVE "NO HAY CAPITAN ASIGNADO"
    private static String obtenerNombreCapitan(Equipo equipo) {
        for (Jugador jugador : equipo.getPlayers()) {
            if (jugador.isEsCapi()) {
                return jugador.getNombre() + " " + jugador.getApellido();
            }
        }
        return "No hay capitan asignado!";
    }

    // BUSCA UN EQUIPO POR NOMBRE Y DEVUELVE EL EQUIPO ENCONTRADO.
    // SI NO SE ENCUENTRA NINGUN EQUIPO CON EL NOMBRE ESPECIFICADO, DEVUELVE NULL.
    private static Equipo buscarEquipoNombre(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    // BUSCADOR DE EQUIPOS DONDE SE ORDENA A LOS JUGADORES POR EL NUMERO DE CAMISETA
    private static void buscarEquipoCamiseta() {
        System.out.println(" ** BUSCAR EQUIPO ORDENADO POR Nº CAMISETA **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creacion: " + equipo.getFechaCreacion());
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
            System.out.println("No se encontro ningun equipo con ese nombre!");
        }
    }

    // BUSCADOR QUE MUESTRA EL EQUIPO, NUMERO DE CAMISETA Y LA POSICION DE LOS JUGADORES
    private static void buscarEquipoPosicion() {
        System.out.println(" ** BUSCAR EQUIPO ORDENADO POR Nº CAMISETA Y POSICION **");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = tecla.next();
        boolean encontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Fecha de creacion: " + equipo.getFechaCreacion());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombre() + " " +
                        equipo.getEntrenador().getApellido() + " Edad: " + equipo.getEntrenador().getEdad());
                System.out.println("Lista de jugadores ordenados por su numero de camiseta y por posicion: ");

                List<Jugador> jugadores = equipo.getPlayers();
                jugadores.sort(Comparator.comparing(Jugador::getPosicion).thenComparingInt(Jugador::getNumCami));

                for (Jugador jugador : jugadores) {
                    System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " -Camiseta: " +
                            jugador.getNumCami() + " -Posicion: " + jugador.getPosicion());
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontro ningun equipo con ese nombre!");
        }
    }

    // SE INGRESA NOMBRE DEL EQUIPO Y SE PROCEDE A ELIMINARLO
    private static void eliminarEquipo() {
        System.out.println(" ** ELIMINAR EQUIPO **");
        System.out.print("Nombre del equipo a eliminar: ");
        String nombreEquipo = tecla.next();
        boolean encontrado = false;

        Iterator<Equipo> iter = equipos.iterator();
        while (iter.hasNext()) {
            Equipo equipo = iter.next();
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                encontrado = true;
                iter.remove();
                System.out.println("El equipo " + equipo.getNombre() + " ha sido eliminado!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro ningun equipo con ese nombre!");
        }
    }
    // IMPORTA JUGADORES DESDE UN ARCHIVO .TXT
    private static void importarJugadores() {
        service.importarJugadores();
    }
    // EXPORTA JUGADORES A UN ARCHIVO .TXT
    private static void exportarJugadores() {
        service.exportarJugadores(equipos);
    }
}