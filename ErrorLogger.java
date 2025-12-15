import java.util.List;
import java.util.ArrayList;

public class ErrorLogger {

    private List<String> errores;
    private String archivoRegistro;

    public ErrorLogger(String archivoRegistro) {
        this.archivoRegistro = archivoRegistro;
        this.errores = new ArrayList<>();
    }

    public synchronized void registrarError(String nombreArchivo, String mensaje) {
        if (this.errores == null) {
            this.errores = new ArrayList<>();
        }
        String entrada = nombreArchivo + mensaje;
        this.errores.add(entrada);
    }

    public synchronized void guardarRegistro() {
        if (archivoRegistro == null || archivoRegistro.trim().isEmpty()) {
            return;
        }

        if (errores != null) {
            errores.clear();
        }
    }
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
