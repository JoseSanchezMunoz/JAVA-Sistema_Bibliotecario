package view;

import controller.BibliotecaController;
import model.Libro;
import model.Renta;

import java.util.Scanner;

public class ConsolaView {
    private BibliotecaController controller = new BibliotecaController();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Editar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Rentar libro");
            System.out.println("5. Mostrar libros rentados");
            System.out.println("6. Devolver libro");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> editarLibro();
                case 3 -> eliminarLibro();
                case 4 -> rentarLibro();
                case 5 -> mostrarRentados();
                case 6 -> devolverLibro();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarLibro() {
        System.out.print("Nombre del libro: ");
        String nombre = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        controller.agregarLibro(nombre, autor, stock);
        System.out.println("Libro agregado con éxito.");
    }

    private void editarLibro() {
        listarLibros();
        System.out.print("ID del libro a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevo autor: ");
        String nuevoAutor = scanner.nextLine();
        System.out.print("Nuevo stock: ");
        int nuevoStock = Integer.parseInt(scanner.nextLine());
        if (controller.editarLibro(id, nuevoNombre, nuevoAutor, nuevoStock)) {
            System.out.println("Libro editado.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void eliminarLibro() {
        listarLibros();
        System.out.print("ID del libro a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (controller.eliminarLibro(id)) {
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void rentarLibro() {
        listarLibros();
        System.out.print("ID del libro a rentar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Cantidad a rentar: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre del lector: ");
        String lector = scanner.nextLine();
        if (controller.rentarLibro(id, cantidad, lector)) {
            System.out.println("Libro rentado exitosamente.");
        } else {
            System.out.println("Error al rentar. Verifique el stock o ID.");
        }
    }

    private void mostrarRentados() {
        if (controller.getRentas().isEmpty()) {
            System.out.println("No hay libros rentados.");
            return;
        }
        System.out.println("--- Libros Rentados ---");
        int i = 0;
        for (Renta renta : controller.getRentas()) {
            System.out.println((i++) + ". " + renta);
        }
    }

    private void devolverLibro() {
        mostrarRentados();
        System.out.print("Número del libro a devolver: ");
        int indice = Integer.parseInt(scanner.nextLine());
        if (controller.devolverLibro(indice)) {
            System.out.println("Libro devuelto.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void listarLibros() {
        if (controller.getLibros().isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("--- Lista de Libros ---");
        for (Libro libro : controller.getLibros()) {
            System.out.println(libro);
        }
    }
}
