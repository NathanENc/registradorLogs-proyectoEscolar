public class LogFileTask {
    private String archivoLog;
    private String[] patronesBusqueda;
    private LogSummary resumenGenerado;
    private ErrorLogger errorLogger;

    public LogFileTask(String archivoLog, String[] patronesBusqueda, ErrorLogger errorLogger) {
        this.archivoLog = archivoLog;
        this.patronesBusqueda = patronesBusqueda;
        this.errorLogger = errorLogger;
    }

    public void run() { }

    public void leerYProcesar() { }

    public void contarCoincidencias(String linea) { }

    public LogSummary getResumenGenerado() {
        return resumenGenerado;
    }
}
