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
                //case 5 -> obtenerPrimeros5Elementos();
                // case 6 -> convertirPalabrasALongitud();
                // case 7 -> concatenarNombres();
                // case 8 -> eliminarDuplicados();
                // case 9 -> obtenerTop3Numeros();
                // case 10 -> agruparPalabrasPorLongitud();
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
}
