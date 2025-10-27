import java.io.File;
import java.util.concurrent.BlockingQueue;

public class LogFileFinder implements Runnable {

    private final String directorioBase;
    private final BlockingQueue<File> colaTrabajo;

    public LogFileFinder(String dirBase, BlockingQueue<File> cola) {
        this.directorioBase = dirBase;
        this.colaTrabajo = cola;
    }

    @Override
    public void run() {
        System.out.println("[Productor] Iniciando búsqueda recursiva en: " + directorioBase);
        File dir = new File(directorioBase);
        
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("[Productor] Error: El directorio base no existe o no es válido: " + directorioBase);
        } else {
            try {
                findLogsRecursively(dir);
            } catch (InterruptedException e) {
                System.err.println("[Productor] Búsqueda interrumpida.");
                Thread.currentThread().interrupt(); 
            }
        }

        try {
            System.out.println("[Productor] Búsqueda terminada. Enviando señal de fin.");
            colaTrabajo.put(new File("END_OF_QUEUE"));
        } catch (InterruptedException e) {
            System.err.println("[Productor] Interrumpido al enviar señal de fin.");
            Thread.currentThread().interrupt();
        }
    }

    private void findLogsRecursively(File currentDir) throws InterruptedException {
        File[] archivos = currentDir.listFiles();

        if (archivos == null) {
            System.err.println("[Productor] No se pudo leer el directorio: " + currentDir.getAbsolutePath());
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                findLogsRecursively(archivo);
            } else {
                if (archivo.getName().toLowerCase().endsWith(".txt")) {
                    colaTrabajo.put(archivo);
                    System.out.println("[Productor] Archivo añadido a la cola: " + archivo.getName());
                }
            }
        }
    }
}