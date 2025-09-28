import java.io.File;
import java.util.concurrent.BlockingQueue;

public class LogFileFinder {

    private File directorioBase;
    private BlockingQueue<File> colaTrabajo;

    public LogFileFinder(File dir, BlockingQueue<File> cola) {
        this.directorioBase = dir;
        this.colaTrabajo = cola;
    }

    public void findLogs() { }

    public void findLogsRecursively(File currentDir) { }

    public File getDirectorioBase() {
        return directorioBase;
    }
    public void setDirectorioBase(File directorioBase) {
        this.directorioBase = directorioBase;
    }

    public BlockingQueue<File> getColaTrabajo() {
        return colaTrabajo;
    }
    public void setColaTrabajo(BlockingQueue<File> colaTrabajo) {
        this.colaTrabajo = colaTrabajo;
    }
}
