import java.io.File;
import java.util.List;

public class LogFileTask implements Runnable {

    private File archivoLog;
    private List<String> patronesBusqueda;
    private LogSummary resumenGenerado;
    private ErrorLogger errorLogger;

    public LogFileTask(File archivo, List<String> patrones, ErrorLogger logger) {
        this.archivoLog = archivo;
        this.patronesBusqueda = patrones;
        this.errorLogger = logger;
    }

    @Override
    public void run() { }

    public void leerYProcesar() { }

    public void contarCoincidencias(String linea) { }

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
