public class ErrorLogger {
    private String[] errores;
    private String archivoRegistro;

    public ErrorLogger(String archivoRegistro) {
        this.archivoRegistro = archivoRegistro;
    }

    public void registrarError(String nombreArchivo, String mensaje) { }

    public void guardarRegistro() { }

    public String[] getErrores() {
        return errores;
    }

    public String getArchivoRegistro() {
        return archivoRegistro;
    }
}
