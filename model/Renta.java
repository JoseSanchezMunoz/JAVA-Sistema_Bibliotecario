package model;

public class Renta {
    private model.Libro libro;
    private String lector;
    private int cantidad;

    public Renta(model.Libro libro, String lector, int cantidad) {
        this.libro = libro;
        this.lector = lector;
        this.cantidad = cantidad;
    }

    public model.Libro getLibro() { return libro; }
    public String getLector() { return lector; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return libro.getNombre() + " | Cantidad: " + cantidad + " | Lector: " + lector;
    }
}
