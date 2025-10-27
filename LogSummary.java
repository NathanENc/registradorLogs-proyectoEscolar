import java.util.HashMap;
import java.util.Map;


public class LogSummary {

   
    private String nombreArchivo;
    private int totalCoincidencias;
    
  
    private Map<String, Integer> coincidenciasPorPatron;

   
    public LogSummary(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.totalCoincidencias = 0;
        this.coincidenciasPorPatron = new HashMap<String, Integer>();
    }

   
    public void registrarCoincidencia(String patron, int n) {
       
        this.totalCoincidencias = this.totalCoincidencias + n;
        
      
        int conteoAnterior = this.coincidenciasPorPatron.getOrDefault(patron, 0);
        this.coincidenciasPorPatron.put(patron, conteoAnterior + n);
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public int getTotalCoincidencias() {
        return totalCoincidencias;
    }

    public Map<String, Integer> getCoincidenciasPorPatron() {
        return coincidenciasPorPatron;
    }
}