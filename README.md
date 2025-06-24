Taller#4 AlePD

Simulación de Parque de Atracciones con Concurrencia en Java

Descripción general
Este taller implementa una simulación de un parque de atracciones utilizando concurrencia en Java. Cada visitante es un hilo que intenta subirse a diferentes atracciones, las cuales tienen una capacidad limitada. Para evitar condiciones de carrera y asegurar un acceso controlado, se utiliza la clase ReentrantLock junto con tryLock().

Estructura del código
Atraccion.java: Clase que representa una atracción. Usa ReentrantLock para controlar el acceso y verificar la capacidad máxima.

Visitante.java: Cada visitante es un hilo que intenta ingresar a una atracción al azar. Si no puede, espera o reintenta.

ParqueDeAtracciones.java: Clase principal que crea las atracciones, los visitantes e inicia la simulación.

Decisiones de diseño
Se eligió ReentrantLock en lugar de synchronized para tener mayor control sobre el acceso y permitir el uso de tryLock() con tiempo de espera.

Se implementó un timeout en tryLock() para que los hilos no se bloqueen indefinidamente.

Cada visitante reintenta varias veces si no logra entrar, lo que simula un comportamiento más realista.

Pruebas
El sistema fue probado ejecutando la simulación varias veces. Se verificó que:

Las atracciones no sobrepasaran su capacidad.

Los hilos finalizaran correctamente.

No ocurrieran errores de concurrencia ni bloqueos indefinidos.

Conclusión
El taller demuestra cómo aplicar mecanismos de sincronización en Java para gestionar el acceso concurrente a recursos compartidos. El uso de ReentrantLock permitió controlar de forma segura la entrada de los visitantes y reflejar un flujo ordenado dentro de la simulación.
