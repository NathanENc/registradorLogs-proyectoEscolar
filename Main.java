import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LogProcessor procesador = null;
        Thread hiloProcesador = null;
        int opcion;

        final String DIRECTORIO_PRUEBA = "D:\\LogsPrueba";
        final List<String> PATRONES_PRUEBA = Arrays.asList("ERROR", "WARNING");
        final int HILOS_PRUEBA = 4;
        final String SALIDA_PRUEBA = "reporte_final.txt";

        do {
            System.out.println("\n MENU PRINCIPAL");
            System.out.println("1. Configurar procesador");
            System.out.println("2. Iniciar procesamiento");
            System.out.println("3. Pausar");
            System.out.println("4. Continuar");
            System.out.println("5. Ver estadisticas");
            System.out.println("6. Agregar archivo a la cola");
            System.out.println("7. Detener procesamiento");
            System.out.println("8. Salir");
            System.out.print("Elige una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {


                case 1:
                    String directorio = DIRECTORIO_PRUEBA;
                    List<String> patrones = PATRONES_PRUEBA;
                    int hilos = HILOS_PRUEBA;
                    String archivoSalida = SALIDA_PRUEBA;

                    System.out.print("Numero de hilos (Enter = " + HILOS_PRUEBA + "): ");
                    String hilosInput = scanner.nextLine().trim();
                    if (!hilosInput.isEmpty()) {
                        try {
                            hilos = Integer.parseInt(hilosInput);
                        } catch (NumberFormatException ex) {
                            System.out.println("Entrada invalida, se usa valor por defecto: " + HILOS_PRUEBA);
                            hilos = HILOS_PRUEBA;
                        }
                    }

                    procesador = new LogProcessor(directorio, patrones, hilos, archivoSalida);
                    System.out.println("Procesador configurado a valores por defecto:");
                    System.out.println(" - Directorio: " + DIRECTORIO_PRUEBA);
                    System.out.println(" - Patrones: " + PATRONES_PRUEBA);
                    System.out.println(" - Hilos: " + hilos);
                    System.out.println(" - Salida: " + SALIDA_PRUEBA);
                    break;
