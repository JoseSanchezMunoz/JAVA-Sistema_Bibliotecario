package model;

public class Libro {
    private static int contador = 0;
    private final int id;
    private String nombre;
    private String autor;
    private int stock;

    public Libro(String nombre, String autor, int stock) {
        this.id = ++contador;
        this.nombre = nombre;
        this.autor = autor;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getStock() { return stock; }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo");
        this.stock = stock;
    }

    @Override
    public String toString() {
        return id + ". " + nombre + " | Autor: " + autor + " | Stock: " + stock;
    }
}
