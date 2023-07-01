package org.example.service;

import org.example.model.Entrenador;

import java.util.Scanner;

public class CoachService {
    private final Scanner tecla;

    public CoachService() {
        tecla = new Scanner(System.in);
    }

    public Entrenador crearEntrenador() {
        System.out.println("** DATOS DEL ENTRENADOR **");

        System.out.print("Nombre del entrenador: ");
        String nombEntrenador = tecla.next();

        System.out.print("Apellido del entrenador: ");
        String apeEntrenador = tecla.next();

        System.out.print("Edad del entrenador: ");
        int edadEntrenador = tecla.nextInt();

        return new Entrenador(nombEntrenador, apeEntrenador, edadEntrenador);
    }
}