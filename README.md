# Proyecto Integrador Etapa 3: Procesador Concurrente de Archivos de Log

Este proyecto es una aplicación robusta en **Java** diseñada para el procesamiento concurrente de archivos de log. Utiliza el paradigma **Productor-Consumidor** y el framework `java.util.concurrent` para paralelizar la carga de trabajo, garantizando alta eficiencia y un control dinámico del flujo de procesamiento.

---

##  Estado Actual del Proyecto

> **Fase Final de Implementación (Etapa 3)**

Toda la lógica de negocio, la estructura de concurrencia, el manejo de errores y las funcionalidades de control en tiempo real (**Pausa/Continuar**) han sido implementadas y probadas exitosamente. El sistema se encuentra listo para la ejecución y la generación del reporte final consolidado.

---

##  Funcionalidades Clave Implementadas

El sistema soporta características avanzadas de concurrencia y control de flujo:

### 1. Arranque Concurrente 
El **Buscador** (`LogFileFinder`) y el **Procesador** (`LogProcessor`) operan en hilos separados bajo el modelo Productor y Consumidor. La comunicación entre ambos se realiza de manera segura a través de una `BlockingQueue`.

### 2. Procesamiento Paralelo 
Se utiliza un **Pool de Hilos** (`ExecutorService`) para distribuir el análisis de archivos (`LogFileTask`) entre múltiples hilos "obreros", maximizando el uso de la CPU y reduciendo tiempos de espera.

### 3. Control en Tiempo Real 
Implementación del patrón **Monitor Object** (mediante `wait()` y `notifyAll()`) dentro de `LogProcessor`. Esto permite:
* Pausar y Continuar el flujo de trabajo de forma segura.
* Evitar la *espera ocupada* (busy waiting), optimizando recursos.

### 4. Gestión de Concurrencia y Seguridad 
Los resultados finales y el registro de errores están protegidos contra **Condiciones de Carrera** mediante:
* `Collections.synchronizedList`
* Bloques y métodos `synchronized`.

### 5. Tablero de Estado 
La función de estadísticas (`getEstadisticas`) proporciona visibilidad en tiempo real del sistema:
* Archivos pendientes en cola.
* Archivos procesados.
* Estado actual del procesador: **ACTIVO** / **PAUSADO**.

### 6. Terminación Limpia 
El sistema permite una finalización ordenada y segura mediante:
* El envío de una **"píldora venenosa"** (`"END_OF_QUEUE"`) para detener consumidores.
* La gestión adecuada de la clausura del `ExecutorService` para liberar recursos.

---

##  Tecnologías y Conceptos

* **Lenguaje:** Java
* **Concurrencia:** `java.util.concurrent`, Threads, Runnable/Callable.
* **Sincronización:** `synchronized`, `wait()`, `notifyAll()`.
* **Patrones:** Producer-Consumer, Monitor Object.

---
_Proyecto desarrollado para la materia de Programación Concurrente._
