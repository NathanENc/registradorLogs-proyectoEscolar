# Proyecto Integrador 2: Procesador de Archivos de Log

Este proyecto es una aplicación en Java diseñada para procesar archivos de log de manera concurrente. La aplicación busca archivos de log en un directorio específico, los procesa en paralelo utilizando un pool de hilos y genera un resumen de los hallazgos.

## Estado Actual del Proyecto

El proyecto se encuentra en la fase inicial de desarrollo. Se ha definido la estructura de clases principal, pero la lógica de negocio dentro de los métodos aún no ha sido implementada.

## Estructura del Proyecto

El proyecto está compuesto por las siguientes clases:

-   `Main.java`: Será el punto de entrada de la aplicación. Desde aquí se iniciará el procesador de logs.

-   `LogProcessor.java`: Es la clase orquestadora principal. Se encarga de:
    -   Gestionar un pool de hilos (`ExecutorService`) para procesar los archivos.
    -   Coordinar la búsqueda y el procesamiento de los archivos de log.
    -   Almacenar los resultados finales.

-   `LogFileFinder.java`: Su responsabilidad es buscar recursivamente los archivos de log (`.log`) dentro de un directorio base y añadirlos a una cola de trabajo (`BlockingQueue`) para que sean procesados por los hilos.

-   `LogFileTask.java`: Representa una tarea que procesa un único archivo de log. Cada tarea será ejecutada por un hilo del `ExecutorService`. Leerá el archivo, buscará patrones y generará un `LogSummary`.

-   `LogSummary.java`: Es una clase de datos que almacena el resumen del análisis de un archivo de log, incluyendo:
    -   Nombre del archivo.
    -   Total de coincidencias encontradas.
    -   Coincidencias por cada patrón de búsqueda.
    -   Reporte de coincidencias por fecha.

-   `ErrorLogger.java`: Una clase de utilidad para registrar cualquier error que pueda ocurrir durante la búsqueda o el procesamiento de los archivos.
