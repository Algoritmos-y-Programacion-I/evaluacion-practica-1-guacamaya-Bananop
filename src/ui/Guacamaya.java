package ui;

import java.util.Scanner;

public class Guacamaya {

    // Scanner global para todo el programa
    public static Scanner reader;
    // Arreglos de precios y unidades para todo el programa
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    /**
     * Descripción: Este método se encarga de iniciar las variables globales.
     * Pre: El Scanner reader debe estar declarado.
     * Pos: El Scanner reader queda inicializado.
     */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripción: Este método se encarga de desplegar el menú al usuario y mostrar los mensajes de resultado para cada funcionalidad.
     * Pre: El Scanner reader debe estar inicializado.
     * Pre: El arreglo precios debe estar inicializado.
     */
    public static void menu() {
        System.out.println("Bienvenido a Guacamaya! :D espero que este teniendo un excelente dia ");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Ingresar cantidad de productos y sus precios");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de producto vendidos en el dia");
            System.out.println("4. Calcular las ventas totales del dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.print("\nDigite la opcion a ejecutar: ");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.print("\nDigite el limite mínimo de ventas a analizar: ");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, " + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios! :D ");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nOpción invalida, intenta nuevamente.");
                    break;
            }
        } while (!salir);
    }

    /**
     * Descripción: Este método se encarga de preguntar al usuario el número de referencias de producto diferentes
     * vendidas en el día e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades.
     * Pre: El Scanner reader debe estar inicializado.
     * Pre: Los arreglos precios y unidades deben estar declarados.
     * Pos: Los arreglos precios y unidades quedan inicializados.
     */
    public static void establecerCantidadVendida() {
        System.out.print("\nDigite el numero de productos diferentes vendidas durante el dia: ");
        int referencias = reader.nextInt();
        precios = new double[referencias];
        unidades = new int[referencias];
    }

    /**
     * Descripción: solicita al usuario los precios y cantidades de cada referencia de producto vendida en el día.
     * Pre: Los arreglos precios y unidades deben estar inicializados.
     * Pos: Se almacenan los valores ingresados en los arreglos.
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.print("\nIngrese el precio de la referencia " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            System.out.print("Ingrese la cantidad vendida de la referencia " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Descripción: Calcula la cantidad total de unidades vendidas durante el día.
     * Pre: Los arreglos precios y unidades deben estar inicializados.
     * @return Total de unidades vendidas.
     */
    public static int calcularTotalUnidadesVendidas() {
        int total = 0;
        for (int cantidad : unidades) {
            total += cantidad;
        }
        return total;
    }

    /**
     * Descripción: Calcula el precio promedio de las referencias de producto vendidas en el día.
     * Pre: Los arreglos precios y unidades deben estar inicializados y contener datos.
     * @return Precio promedio de los productos vendidos.
     */
    public static double calcularPrecioPromedio() {
        double sumaPrecios = 0;
        for (double precio : precios) {
            sumaPrecios += precio;
        }
        return precios.length > 0 ? sumaPrecios / precios.length : 0;
    }

    /**
     * Descripción: Calcula las ventas totales (dinero recaudado) durante el día.
     * Pre: Los arreglos precios y unidades deben estar inicializados y contener datos.
     * @return Ventas totales del día.
     */
    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    /**
     * Descripción: Consulta cuántas referencias superaron un límite mínimo de ventas proporcionado por el usuario.
     * Pre: Los arreglos precios y unidades deben estar inicializados y contener datos.
     * @param limite Límite mínimo de ventas a analizar.
     * @return Número de referencias que superaron el límite de ventas.
     */
    public static int consultarReferenciasSobreLimite(double limite) {
        int count = 0;
        for (int i = 0; i < precios.length; i++) {
            if (precios[i] * unidades[i] > limite) {
                count++;
            }
        }
        return count;
    }
}
