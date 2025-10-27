import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogFileTask implements Runnable {

    private File archivoLog;
    private List<String> patronesBusqueda;
    private LogSummary resumenGenerado;
    private ErrorLogger errorLogger;
    
    private List<LogSummary> listaResultados; 

    public LogFileTask(File archivo, List<String> patrones, List<LogSummary> resultados, ErrorLogger logger) {
        this.archivoLog = archivo;
        this.patronesBusqueda = patrones;
        this.listaResultados = resultados; 
        this.errorLogger = logger;
        
        this.resumenGenerado = new LogSummary(archivo.getName());
    }

    @Override
    public void run() {
        System.out.println("[Task " + Thread.currentThread().getId() + "] Procesando: " + archivoLog.getName());
        try {
            leerYProcesar();
            
            listaResultados.add(resumenGenerado);
            
        } catch (Exception e) {
            errorLogger.registrarError(archivoLog.getName(), "Error fatal inesperado: " + e.getMessage());
        }
        System.out.println("[Task " + Thread.currentThread().getId() + "] Terminado: " + archivoLog.getName());
    }


    public void leerYProcesar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoLog))) {
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                contarCoincidencias(linea);
            }
            
        } catch (IOException e) {
            errorLogger.registrarError(archivoLog.getName(), "Error de E/S al leer el archivo: " + e.getMessage());
        }
    }


    public void contarCoincidencias(String linea) {
        String lineaLower = linea.toLowerCase();

        for (String patron : patronesBusqueda) {
            String patronLower = patron.toLowerCase();
            int count = 0;
            int index = lineaLower.indexOf(patronLower);
            while (index != -1) {
                count++;
                index = lineaLower.indexOf(patronLower, index + patronLower.length());
            }

            if (count > 0) {
                this.resumenGenerado.registrarCoincidencia(patron, count);
            }
        }
    }

    public File getArchivoLog() {
        return archivoLog;
    }

    public void setArchivoLog(File archivoLog) {
        this.archivoLog = archivoLog;
    }

    public List<String> getPatronesBusqueda() {
        return patronesBusqueda;
    }

    public void setPatronesBusqueda(List<String> patronesBusqueda) {
        this.patronesBusqueda = patronesBusqueda;
    }

    public LogSummary getResumenGenerado() {
        return resumenGenerado;
    }

    public void setResumenGenerado(LogSummary resumenGenerado) {
        this.resumenGenerado = resumenGenerado;
    }

    public ErrorLogger getErrorLogger() {
        return errorLogger;
    }

    public void setErrorLogger(ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
    }
}