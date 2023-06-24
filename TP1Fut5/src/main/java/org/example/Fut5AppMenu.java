package org.example;

import org.example.service.DeleteService;
import org.example.service.ImportExportService;
import org.example.service.TeamService;
import org.example.service.SearchService;

import java.util.Scanner;

public class Fut5AppMenu {
    private static final Scanner tecla = new Scanner(System.in);
    private final TeamService tm = new TeamService();
    private final ImportExportService service = new ImportExportService();
    private final DeleteService deleteService = new DeleteService();
    private final SearchService ss = new SearchService(tm);

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
    private void ejectOpc(int opc) {

        switch (opc) {
            case 1 -> crearEquipo();
            case 2 -> buscarJugadorNombre();
            case 3 -> buscarEquipoNombre();
            case 4 -> buscarEquipoCamiseta();
            case 5 -> buscarEquipoPosicion();
            case 6 -> eliminarEquipo();
            case 7 -> importarJugadores();
            case 8 -> exportarJugadores(tm);
            case 9 -> System.out.println("Adios!");
            default -> System.out.println("Opcion invalida.");
        }
    }

    private static void crearEquipo() {
        TeamService tm = new TeamService();
        tm.crearEquipo();
    }

    private void buscarJugadorNombre() {
        ss.buscarJugadorNombre();
    }

    private void buscarEquipoNombre() {
        ss.buscarEquipoNombre();
    }

    private void buscarEquipoCamiseta() {
        ss.buscarEquipoCamiseta();
    }

    private void buscarEquipoPosicion() {
        ss.buscarEquipoPosicion();
    }

    private void eliminarEquipo() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Ingrese el nombre del equipo a eliminar: ");
        String nombreEquipo = tecla.next();

        deleteService.eliminarEquipo(tm.getEquipos(), nombreEquipo);
    }

    private void importarJugadores() {
        service.importarJugadores();
    }
    // EXPORTA JUGADORES A UN ARCHIVO .TXT
    private void exportarJugadores(TeamService tm) {
        service.exportarJugadores(tm.getEquipos());
    }
}