import java.util.List;

public class ErrorLogger {

    private List<String> errores;
    private String archivoRegistro;

    public ErrorLogger(String archivoRegistro) {
        this.archivoRegistro = archivoRegistro;
    }

    public void registrarError(String nombreArchivo, String mensaje) { }

    public void guardarRegistro() { }

    public List<String> getErrores() {
        return errores;
    }
    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public String getArchivoRegistro() {
        return archivoRegistro;
    }
    public void setArchivoRegistro(String archivoRegistro) {
        this.archivoRegistro = archivoRegistro;
    }
}
