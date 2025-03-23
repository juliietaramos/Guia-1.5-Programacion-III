import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final List<Producto> listaProductos = Producto.cargarProductos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== MENÚ DE EJERCICIOS STREAMS Y LAMBDAS =====");
            System.out.println("1. Filtrado y transformacion");
            System.out.println("2. Reduccion de datos");
            System.out.println("3. Producto más caro");
            System.out.println("4. Uso de optionals");
            System.out.println("5. Producto más barato");
            System.out.println("6. Productos en stock ordenados alfabeticamente");
            System.out.println("7. Calculo de stock total");
            System.out.println("8. Stock por categoria");
            System.out.println("9. Aplicar descuento");
            System.out.println("10. Ganancia total inventario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> filtradoYTransformacion().forEach(System.out::println);
                case 2 ->
                        System.out.println("Precio promedio de los productos de la categoria Hogar: " + reduccionDeDatos());
                case 3 -> productoMasCaro().values().forEach(System.out::println);
                case 4 -> System.out.println(usoDeOptionals().orElse("Producto inexistente"));
                case 5 ->
                        System.out.println("Producto mas barato: " + productoMasBarato().orElseThrow(NoSuchElementException::new));
                case 6 -> producosOrdenadosAlfabeticamente().forEach(System.out::println);
                case 7 -> System.out.println("Stock total: " + calculoDeStockTotal());
                case 8 -> System.out.println(stockPorCategoria());
                case 9 -> System.out.println(aplicarDescuento());
                case 10 -> System.out.println("Ganancia total: " + gananciaTotalInventario());
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        }
        while (opcion != 0);
        scanner.close();
    }

    private static List<Producto> filtradoYTransformacion() {
        return listaProductos
                .stream()
                .filter(p1 -> p1.getCategoria().equals("Electrónica") && p1.getPrecio() > 1000)
                .sorted(Comparator.comparing(Producto::getPrecio)
                        .reversed()).toList();
    }

    private static Double reduccionDeDatos() {
        return listaProductos
                .stream()
                .filter(p -> p.getCategoria().equals("Hogar") && p.getStock() > 0)
                .collect(Collectors.averagingDouble(Producto::getPrecio));
    }

    private static Map<String, Producto> productoMasCaro() {
        return listaProductos
                .stream()
                .collect(Collectors
                        .toMap(Producto::getCategoria, Function.identity(), (p1, p2) -> p1.getPrecio() > p2.getPrecio() ? p1 : p2));
    }

    private static Optional<String> usoDeOptionals() {
        return listaProductos
                .stream()
                .filter(p -> p.getCategoria()
                        .equals("Deportes") && p.getStock() > 10)
                .map(p -> p.getNombre().toLowerCase()).findFirst();
    }

    private static Optional<Producto> productoMasBarato() {
        /*Double precioBarato=listaProductos
                .stream()
                .mapToDouble(n -> n.getStock()*n.getPrecio())
                .min().orElse(0);
        return listaProductos
                .stream()
                .filter(Producto::getPrecio*Producto::getStock -> precioBarato);*/
        return listaProductos
                .stream()
                .min(Comparator.comparingDouble(p -> p.getStock() * p.getPrecio()));
    }

    private static List<String> producosOrdenadosAlfabeticamente() {
        return listaProductos
                .stream()
                .filter(p -> p.getStock() > 0 && p.getNombre().length() >= 5)
                .map(p -> p.getNombre())
                .sorted()
                .toList();
    }

    private static Integer calculoDeStockTotal() {
        Double precioPromedio = listaProductos
                .stream()
                .collect(Collectors
                        .averagingDouble(p -> p.getPrecio()));
        return listaProductos
                .stream()
                .filter(p->p.getPrecio()>precioPromedio)
                .mapToInt(p -> p.getStock())
                //.sum();
                .reduce(0,(p1 , p2) -> p1 + p2);
    }

    private static Map<String , Integer> stockPorCategoria(){
        Map<String,Long> conteoPorCategoria = listaProductos
                .stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.counting()));

        return listaProductos
                .stream()
                .filter(producto -> conteoPorCategoria.get(producto.getCategoria()) > 3)
                .collect(Collectors.groupingBy(Producto::getCategoria,Collectors.summingInt(Producto::getStock)));
    }

    private static List<Producto> aplicarDescuento(){
        return listaProductos
                .stream()
                .filter(p -> p.getStock()>20)
                .peek(p -> p.setPrecio(p.getPrecio()*0.85))
                .toList();
    }

    private static Double gananciaTotalInventario(){
        return listaProductos
                .stream()
                .mapToDouble(p -> p.getCategoria().equals("Electronica") ? p.getPrecio()*0.35 : p.getPrecio()*0.55)
                .sum();
    }
}
