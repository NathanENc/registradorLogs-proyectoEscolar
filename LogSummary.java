import java.util.Map;

public class LogSummary {

    private String nombreArchivo;
    private int totalCoincidencias;
    private Map<String, Integer> coincidenciasPorPatron;
    private Map<String, Integer> reportePorFecha;
    private boolean esCorrupto;

    public LogSummary(String nombre) {
        this.nombreArchivo = nombre;
    }

    public void incrementarCoincidencia(String patron, int cantidad) { }

    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getTotalCoincidencias() {
        return totalCoincidencias;
    }
    public void setTotalCoincidencias(int totalCoincidencias) {
        this.totalCoincidencias = totalCoincidencias;
    }

    public Map<String, Integer> getCoincidenciasPorPatron() {
        return coincidenciasPorPatron;
    }
    public void setCoincidenciasPorPatron(Map<String, Integer> coincidenciasPorPatron) {
        this.coincidenciasPorPatron = coincidenciasPorPatron;
    }

    public Map<String, Integer> getReportePorFecha() {
        return reportePorFecha;
    }
    public void setReportePorFecha(Map<String, Integer> reportePorFecha) {
        this.reportePorFecha = reportePorFecha;
    }

    public boolean isEsCorrupto() {
        return esCorrupto;
    }
    public void setEsCorrupto(boolean esCorrupto) {
        this.esCorrupto = esCorrupto;
    }
}
