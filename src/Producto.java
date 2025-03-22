import java.util.List;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private int stock; // Constructor, Getters y Setters

    public Producto(String nombre, double precio, String categoria, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre='" + nombre + '\'' + ", precio=" + precio + ", categoria='" + categoria + '\'' + ", stock=" + stock + '}';
    }

    public static List<Producto> cargarProductos() {
        return List.of(new Producto("Laptop", 1500, "Electrónica", 5),
                new Producto("Smartphone", 800, "Electrónica", 10),
                new Producto("Televisor", 1200, "Electrónica", 3),
                new Producto("Heladera", 2000, "Hogar", 2),
                new Producto("Microondas", 500, "Hogar", 8),
                new Producto("Silla", 150, "Muebles", 12),
                new Producto("Mesa", 300, "Muebles", 7),
                new Producto("Zapatillas", 100, "Deportes", 15),
                new Producto("Pelota", 50, "Deportes", 20),
                new Producto("Bicicleta", 500, "Deportes", 5),
                new Producto("Libro", 30, "Librería", 50),
                new Producto("Cuaderno", 10, "Librería", 100),
                new Producto("Lámpara", 80, "Hogar", 30),
                new Producto("Cafetera", 250, "Hogar", 6),
                new Producto("Auriculares", 120, "Electrónica", 14),
                new Producto("Teclado", 90, "Electrónica", 9),
                new Producto("Mouse", 60, "Electrónica", 18),
                new Producto("Monitor", 700, "Electrónica", 4),
                new Producto("Cama", 800, "Muebles", 2),
                new Producto("Sofá", 1000, "Muebles", 3),
                new Producto("Espejo", 120, "Hogar", 12),
                new Producto("Ventilador", 150, "Hogar", 7),
                new Producto("Patines", 180, "Deportes", 5),
                new Producto("Raqueta", 220, "Deportes", 6),
                new Producto("Taza", 15, "Hogar", 40));
    }
}