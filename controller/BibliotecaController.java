package controller;

import model.Libro;
import model.Renta;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {
    private List<Libro> libros = new ArrayList<>();
    private List<Renta> rentas = new ArrayList<>();

    public List<Libro> getLibros() { return libros; }
    public List<Renta> getRentas() { return rentas; }

    public void agregarLibro(String nombre, String autor, int stock) {
        libros.add(new Libro(nombre, autor, stock));
    }

    public boolean editarLibro(int id, String nuevoNombre, String nuevoAutor, int nuevoStock) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libro.setNombre(nuevoNombre);
                libro.setAutor(nuevoAutor);
                libro.setStock(nuevoStock);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarLibro(int id) {
        Libro libroAEliminar = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroAEliminar = libro;
                break;
            }
        }
        if (libroAEliminar != null) {
            libros.remove(libroAEliminar);
            rentas.removeIf(r -> r.getLibro().getId() == id);
            return true;
        }
        return false;
    }

    public boolean rentarLibro(int id, int cantidad, String lector) {
        for (Libro libro : libros) {
            if (libro.getId() == id && libro.getStock() >= cantidad) {
                libro.setStock(libro.getStock() - cantidad);
                rentas.add(new Renta(libro, lector, cantidad));
                return true;
            }
        }
        return false;
    }

    public boolean devolverLibro(int indice) {
        if (indice >= 0 && indice < rentas.size()) {
            Renta renta = rentas.remove(indice);
            Libro libro = renta.getLibro();
            libro.setStock(libro.getStock() + renta.getCantidad());
            return true;
        }
        return false;
    }
}