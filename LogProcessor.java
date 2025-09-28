import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class LogProcessor {

    private String directorioBusqueda;
    private List<String> patronesBusqueda;
    private int maxHilos;
    private BlockingQueue<File> colaTrabajo;
    private ExecutorService executorService;
    private List<LogSummary> resultados;
    private String archivoSalida;

    public LogProcessor(String dir, List<String> patrones, int hilos, String salida) {
        this.directorioBusqueda = dir;
        this.patronesBusqueda = patrones;
        this.maxHilos = hilos;
        this.archivoSalida = salida;
    }

    public void iniciarProcesamiento() { }

    public void detenerProcesamiento() { }

    public String getDirectorioBusqueda() { 
        return directorioBusqueda; 
    }
    public void setDirectorioBusqueda(String directorioBusqueda) { 
        this.directorioBusqueda = directorioBusqueda; 
    }

    public List<String> getPatronesBusqueda() { 
        return patronesBusqueda; 
    }
    public void setPatronesBusqueda(List<String> patronesBusqueda) { 
        this.patronesBusqueda = patronesBusqueda; 
    }

    public int getMaxHilos() { 
        return maxHilos; 
    }
    public void setMaxHilos(int maxHilos) { 
        this.maxHilos = maxHilos; 
    }

    public BlockingQueue<File> getColaTrabajo() { 
        return colaTrabajo; 
    }
    public void setColaTrabajo(BlockingQueue<File> colaTrabajo) { 
        this.colaTrabajo = colaTrabajo; 
    }

    public ExecutorService getExecutorService() { 
        return executorService; 
    }
    public void setExecutorService(ExecutorService executorService) { 
        this.executorService = executorService; 
    }

    public List<LogSummary> getResultados() { 
        return resultados; 
    }
    public void setResultados(List<LogSummary> resultados) { 
        this.resultados = resultados; 
    }

    public String getArchivoSalida() { 
        return archivoSalida; 
    }
    public void setArchivoSalida(String archivoSalida) { 
        this.archivoSalida = archivoSalida; 
    }
}
