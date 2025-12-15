import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

                case 2:
                    if (procesador == null) {
                        System.out.println("Error: Primero configura el procesador (opcion 1).");
                    } else {
                        final LogProcessor procesadorFinal = procesador;

                        hiloProcesador = new Thread(new Runnable() {
                            public void run() {
                                procesadorFinal.iniciarProcesamiento();
                            }
                        });
                        hiloProcesador.start();

                        LogFileFinder buscador = new LogFileFinder(
                                procesadorFinal.getDirectorioBusqueda(),
                                procesadorFinal.getColaTrabajo()
                        );
                        Thread hiloBuscador = new Thread(buscador);
                        hiloBuscador.start();

                        System.out.println("Procesamiento y búsqueda iniciados en segundo plano.");
                    }
                    break;


                case 3:
                    if (procesador == null) {
                        System.out.println("Error: No hay procesador configurado.");
                    } else {
                        procesador.pausar();
                    }
                    break;

                case 4:
                    if (procesador == null) {
                        System.out.println("Error: No hay procesador configurado.");
                    } else {
                        procesador.continuar();
                    }
                    break;

                case 5:
                    if (procesador == null) {
                        System.out.println("Error: No hay procesador configurado.");
                    } else {
                        System.out.println(procesador.getEstadisticas());
                    }
                    break;

                case 6:
                    if (procesador == null) {
                        System.out.println("Error: No hay procesador configurado.");
                    } else {
                        System.out.print("Ruta del archivo: ");
                        String rutaArchivo = scanner.nextLine();
                        File archivo = new File(rutaArchivo);

                        if (archivo.exists()) {
                            procesador.getColaTrabajo().add(archivo);
                            System.out.println("Archivo agregado a la cola.");
                        } else {
                            System.out.println("Error: El archivo no existe.");
                        }
                    }
                    break;

                case 7:
                    if (procesador == null) {
                        System.out.println("Error: No hay procesador configurado.");
                    } else {
                        procesador.getColaTrabajo().add(new File("END_OF_QUEUE"));
                        System.out.println("Señal de detencion enviada.");
                    }
                    break;

                case 8:
                    System.out.println("Saliendo del programa...");
                    if (procesador != null) {
                        procesador.getColaTrabajo().add(new File("END_OF_QUEUE"));
                    }
                    break;

                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }

        } while (opcion != 8);

        scanner.close();
    }
}