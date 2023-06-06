package org.example;

import org.example.model.Equipo;
import org.example.service.DeleteService;
import org.example.service.ImportExportService;
import org.example.service.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fut5AppMenu {
    private static final Scanner tecla = new Scanner(System.in);
    private static final List<Equipo> equipos = new ArrayList<>();
    private static final ImportExportService service = new ImportExportService();

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

    private static void crearEquipo() {
        org.example.service.TeamService.crearEquipo();
    }

    private static void buscarJugadorNombre() {
        org.example.service.SearchService.buscarJugadorNombre(TeamService.equipos);
    }

    private static void buscarEquipoNombre() {
        org.example.service.SearchService.buscarEquipoNombre(TeamService.equipos);
    }

    private static void buscarEquipoCamiseta() {
        org.example.service.SearchService.buscarEquipoCamiseta(TeamService.equipos);
    }

    private static void buscarEquipoPosicion() {
        org.example.service.SearchService.buscarEquipoPosicion(TeamService.equipos);
    }

    private static void eliminarEquipo() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Ingrese el nombre del equipo a eliminar: ");
        String nombreEquipo = tecla.next();

        DeleteService.eliminarEquipo(TeamService.equipos, nombreEquipo);
    }

    private static void importarJugadores() {
        service.importarJugadores();
    }
    // EXPORTA JUGADORES A UN ARCHIVO .TXT
    private static void exportarJugadores() {
        service.exportarJugadores(equipos);
    }
}