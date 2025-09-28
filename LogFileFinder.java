public class LogFileFinder {
    private String directorioBase;
    private String[] colaTrabajo;

    public LogFileFinder(String directorioBase, String[] colaTrabajo) {
        this.directorioBase = directorioBase;
        this.colaTrabajo = colaTrabajo;
    }

    public void findLogs() { }

    public String getDirectorioBase() {
        return directorioBase;
    }

    public String[] getColaTrabajo() {
        return colaTrabajo;
    }
}
