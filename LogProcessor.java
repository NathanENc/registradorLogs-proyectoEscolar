import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map; 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;   
import java.io.PrintWriter;  
import java.io.IOException;

public class LogProcessor {

    private final int maxHilos;
    private final BlockingQueue<File> colaTrabajo;
    private final ExecutorService executorService;
    private final List<LogSummary> resultados; 
    private final List<String> patronesBusqueda;
    private final String directorioBusqueda; 
    private final String archivoSalida; 
    private boolean pausado;
    private final Object cerrojo;
    private ErrorLogger errorLogger;

    public LogProcessor(String dir, List<String> patrones, int hilos, String salida) {
        this.directorioBusqueda = dir;
        this.patronesBusqueda = patrones;
        this.maxHilos = hilos;
        this.archivoSalida = salida;

        this.colaTrabajo = new LinkedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(this.maxHilos); 
        this.resultados = Collections.synchronizedList(new ArrayList<>());
        this.pausado = false;
        this.cerrojo = new Object();
        this.errorLogger = new ErrorLogger("errores.txt");
    }

    public void iniciarProcesamiento() {
        try {
            while (true) { 
                synchronized (cerrojo) {
                    while (pausado) {
                        System.out.println("Procesamiento pausado. Esperando...");
                        cerrojo.wait();
                    }
                }
                
                File fileToProcess = colaTrabajo.take(); 
                
                if (fileToProcess.getName().equals("END_OF_QUEUE")) {
                    break; 
                }

                LogFileTask task = new LogFileTask(fileToProcess, patronesBusqueda, resultados, errorLogger); 
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
        
        
        escribirReporteConsolidado();
        
    }
    public void pausar() {
        synchronized (cerrojo) {
            pausado = true;
            System.out.println("Procesamiento pausado.");
        }
    }

    public void continuar() {
        synchronized (cerrojo) {
            pausado = false;
            cerrojo.notifyAll();
            System.out.println("Procesamiento reanudado.");
        }
    }

    public boolean estaPausado() {
        synchronized (cerrojo) {
            return pausado;
        }
    }

    public String getEstadisticas() {
        int archivosPendientes = colaTrabajo.size();
        int archivosProcesados = resultados.size();
        int hilosConfigurados = maxHilos;

        String reporte =
            "\n ESTADO DEL PROCESADOR" +
            "\n  Archivos en cola (pendientes): " + archivosPendientes +
            "\n  Archivos procesados: " + archivosProcesados +
            "\n  Hilos configurados: " + hilosConfigurados +
            "\n  Estado: " + (estaPausado() ? "PAUSADO" : "ACTIVO");

        return reporte;
    }

   
    private void escribirReporteConsolidado() {
        System.out.println("Procesamiento finalizado. Escribiendo reporte en: " + this.archivoSalida);

       
        try (FileWriter fw = new FileWriter(this.archivoSalida);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("REPORTE CONSOLIDADO DE LOGS");
            pw.println("Directorio analizado: " + this.directorioBusqueda);
            pw.println("Patrones buscados: " + this.patronesBusqueda.toString());

            
            for (LogSummary summary : this.resultados) {
                pw.println("Archivo: " + summary.getNombreArchivo());
                pw.println("Total de coincidencias: " + summary.getTotalCoincidencias());
                
                
                pw.println("Coincidencias por patrón:");
                if (summary.getCoincidenciasPorPatron().isEmpty()) {
                    pw.println("  (Ninguna)");
                } else {
                    for (Map.Entry<String, Integer> entry : summary.getCoincidenciasPorPatron().entrySet()) {
                        pw.println("  - " + entry.getKey() + ": " + entry.getValue());
                    }
                }
                pw.println(); 
            }
            
            pw.println("FIN DEL REPORTE");
            System.out.println("Reporte consolidado generado exitosamente.");

        } catch (IOException e) {
            
            System.out.println("¡Error! No se pudo escribir el archivo de salida: " + e.getMessage());
        }
    }
    

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
