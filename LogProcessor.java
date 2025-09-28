public class LogProcessor {
    private String directorioBusqueda;
    private String[] patronesBusqueda;
    private int maxHilos;
    private String[] colaTrabajo;
    private String[] resultados;
    private String archivoSalida;

    public LogProcessor(String directorioBusqueda, String[] patronesBusqueda, int maxHilos, String archivoSalida) {
        this.directorioBusqueda = directorioBusqueda;
        this.patronesBusqueda = patronesBusqueda;
        this.maxHilos = maxHilos;
        this.archivoSalida = archivoSalida;
    }

    public void iniciarProcesamiento() { }

    public void detenerProcesamiento() { }

    public String[] getColaTrabajo() {
        return colaTrabajo;
    }

    public String[] getPatronesBusqueda() {
        return patronesBusqueda;
    }
}
