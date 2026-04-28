package com.bibliotech;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.*;
import com.bibliotech.repository.*;
import com.bibliotech.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Inyección de dependencias
        LibroRepository libroRepository = new LibroRepositoryImpl();
        SocioRepository socioRepository = new SocioRepositoryImpl();

        LibroService libroService = new LibroServiceImpl(libroRepository);
        SocioService socioService = new SocioServiceImpl(socioRepository);
        PrestamoService prestamoService = new PrestamoServiceImpl();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== BiblioTech =====");
            System.out.println("1. Registrar socio");
            System.out.println("2. Agregar libro");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Devolver libro");
            System.out.println("5. Consultar historial");
            System.out.println("0. Salir");
            System.out.print("Seleccioná una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarSocio(scanner, socioService);
                case 2 -> agregarLibro(scanner, libroService);
                case 3 -> realizarPrestamo(scanner, socioService, libroService, prestamoService);
                case 4 -> devolverLibro(scanner, socioService, libroService, prestamoService);
                case 5 -> consultarHistorial(scanner, socioService, prestamoService);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarSocio(Scanner scanner, SocioService socioService) {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Tipo (1=Estudiante, 2=Docente): ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            Socio socio = tipo == 1
                ? new Estudiante(1, nombre, dni, email)
                : new Docente(1, nombre, dni, email);

            socioService.registrarSocio(socio);
            System.out.println("Socio registrado correctamente.");
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarLibro(Scanner scanner, LibroService libroService) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        libroService.registrarLibro(new Libro(isbn, titulo, autor, anio, categoria));
        System.out.println("Libro agregado correctamente.");
    }

    private static void realizarPrestamo(Scanner scanner, SocioService socioService,
                                         LibroService libroService, PrestamoService prestamoService) {
        try {
            System.out.print("DNI del socio: ");
            String dni = scanner.nextLine();
            System.out.print("Título del libro: ");
            String isbn = scanner.nextLine();

            Socio socio = socioService.buscarPorDni(dni);
            Libro libro = (Libro) libroService.buscarPorTitulo(isbn).get(0);

            prestamoService.prestar(socio, libro);
            System.out.println("Préstamo realizado correctamente.");
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void devolverLibro(Scanner scanner, SocioService socioService,
                                      LibroService libroService, PrestamoService prestamoService) {
        try {
            System.out.print("DNI del socio: ");
            String dni = scanner.nextLine();
            System.out.print("Título del libro: ");
            String isbn = scanner.nextLine();

            Socio socio = socioService.buscarPorDni(dni);
            Libro libro = libroService.buscarPorTitulo(isbn).get(0);

            prestamoService.devolver(socio, libro);
            System.out.println("Devolución registrada correctamente.");
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void consultarHistorial(Scanner scanner, SocioService socioService,
                                           PrestamoService prestamoService) {
        try {
            System.out.print("DNI del socio: ");
            String dni = scanner.nextLine();

            Socio socio = socioService.buscarPorDni(dni);
            List<Prestamo> historial = prestamoService.historial(socio);

            if (historial.isEmpty()) {
                System.out.println("El socio no tiene préstamos registrados.");
            } else {
                historial.forEach(p -> System.out.println(
                    "- " + p.recurso().titulo() +
                        " | Prestado: " + p.fechaPrestamo() +
                        " | Devuelto: " + (p.fechaDevolucion() != null ? p.fechaDevolucion() : "En curso")
                ));
            }
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
