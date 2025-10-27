import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// Clases de compañeros (solo para que el código compile)


public class LogProcessor {

    private final int maxHilos;
    private final BlockingQueue<File> colaTrabajo;
    private final ExecutorService executorService;
    private final List<LogSummary> resultados; 
    private final List<String> patronesBusqueda;
    private final String directorioBusqueda; 
    private final String archivoSalida; 
    

    public LogProcessor(String dir, List<String> patrones, int hilos, String salida) {
        this.directorioBusqueda = dir;
        this.patronesBusqueda = patrones;
        this.maxHilos = hilos;
        this.archivoSalida = salida;

        // Inicialización del Pool y la Cola (RF4)
        this.colaTrabajo = new LinkedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(this.maxHilos); 
        this.resultados = new ArrayList<>(); 
    }

    public void iniciarProcesamiento() {
        try {
            while (true) { 
                File fileToProcess = colaTrabajo.take(); 
                
                if (fileToProcess.getName().equals("END_OF_QUEUE")) {
                    break; 
                }

                // Envía la tarea al pool (RF4)
                LogFileTask task = new LogFileTask(fileToProcess, patronesBusqueda, resultados);
                executorService.submit(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            detenerProcesamiento();
        }
    }

    public void detenerProcesamiento() {
        executorService.shutdown(); 
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow(); 
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // Getters necesarios para la comunicación con otras clases

    public BlockingQueue<File> getColaTrabajo() { 
        return colaTrabajo; 
    }

    public List<LogSummary> getResultados() {
        return resultados; 
    }
    
    public String getDirectorioBusqueda() { return directorioBusqueda; }
    public List<String> getPatronesBusqueda() { return patronesBusqueda; }
    public String getArchivoSalida() { return archivoSalida; }

}