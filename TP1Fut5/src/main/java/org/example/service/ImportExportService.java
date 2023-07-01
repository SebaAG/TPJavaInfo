package org.example.service;

import org.example.model.Jugador;
import org.example.model.Equipo;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ImportExportService {

    public  Set<Jugador> importarJugadores() {
        Set<Jugador> jugadoresImportados = new HashSet<>();
        String ARCHIVO_JUGADORES = "F:/Trabajo Practico 1 INFORMATORIO/TP1Fut5/src/" +
                "main/resources/jugadores.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_JUGADORES))) {
            String linea;
            int cont = 0;
            
            while ((linea = br.readLine()) != null) {
                linea = linea.replace("\"", "");
                String[] datos = linea.split(",");
                if (datos.length == 9) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String apellido = datos[2].trim();
                    BigDecimal altura = new BigDecimal(datos[3].trim());
                    String posicion = datos[4].trim();
                    int cantGoles = Integer.parseInt(datos[5].trim());
                    int cantPart = Integer.parseInt(datos[6].trim());
                    boolean esCapi = Boolean.parseBoolean(datos[7].trim());
                    int numCami = Integer.parseInt(datos[8].trim());

                    Jugador jugador = new Jugador(id, nombre, apellido, altura, posicion, cantGoles,
                            cantPart, esCapi, numCami);
                    jugadoresImportados.add(jugador);

                    cont++;
                }
            }
            if (cont > 0) {
                System.out.println("Haz importado " + cont + " jugadores de forma correcta!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadoresImportados;
    }
    public void exportarJugadores(List<Equipo> equipos) {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados. No se pueden exportar jugadores.");
            return;
        }

        String rutaExportacion = "F:/Trabajo Practico 1 INFORMATORIO/TP1Fut5/src/main/resources/";

        for (Equipo equipo : equipos) {
            String nombreArchivo = equipo.getNombre() + "_jugadores.txt";
            String rutaArchivo = rutaExportacion + nombreArchivo;
            File archivoExport = new File(rutaArchivo);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoExport))) {
                for (Jugador jugador : equipo.getPlayers()) {
                    String linea = jugador.getId() + "," + jugador.getNombre() + "," + jugador.getApellido() + ","
                            + jugador.getAltura() + "," + jugador.getPosicion() + "," + jugador.getCantGoles() + ","
                            + jugador.getCantPart() + "," + jugador.isEsCapi() + "," + jugador.getNumCami()  + ","
                            + equipo.getNombre();
                    bw.write(linea);
                    bw.newLine();
                }
                System.out.println("Exportación exitosa para el equipo " + equipo.getNombre() +
                        ". Se ha guardado el archivo de jugadores en: " + rutaArchivo);
            } catch (IOException e) {
                System.out.println("Error al exportar los jugadores del equipo " + equipo.getNombre() +
                        ": " + e.getMessage());
            }
        }
    }
}