public class LogSummary {
    private String nombreArchivo;
    private int totalCoincidencias;
    private String[] coincidenciasPorPatron;
    private boolean esCorrupto;

    public LogSummary(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void incrementarCoincidencia(String patron, int cantidad) { }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public int getTotalCoincidencias() {
        return totalCoincidencias;
    }

    public String[] getCoincidenciasPorPatron() {
        return coincidenciasPorPatron;
    }

    public boolean isEsCorrupto() {
        return esCorrupto;
    }

    public void setTotalCoincidencias(int totalCoincidencias) {
        this.totalCoincidencias = totalCoincidencias;
    }

    public void setEsCorrupto(boolean esCorrupto) {
        this.esCorrupto = esCorrupto;
    }
}
