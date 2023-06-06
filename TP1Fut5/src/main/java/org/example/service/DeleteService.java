package org.example.service;

import org.example.model.Equipo;

import java.util.Iterator;
import java.util.List;

public class DeleteService {
    public static void eliminarEquipo(List<Equipo> equipos, String nombreEquipo) {
        System.out.println(" ** ELIMINAR EQUIPO **");
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
            System.out.println("No se encontró un equipo con ese nombre!");
        }
    }
}

