import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Atraccion {
    private final String nombre;
    private final int capacidadMaxima;
    private int visitantesActuales = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public Atraccion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean intentarSubir(String nombreVisitante) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    if (visitantesActuales < capacidadMaxima) {
                        visitantesActuales++;
                        System.out.println(nombreVisitante + " subió a " + nombre);
                        Thread.sleep(1000); // Disfruta la atracción
                        bajar(nombreVisitante);
                        return true;
                    } else {
                        System.out.println(nombreVisitante + " encontró " + nombre + " llena.");
                        return false;
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(nombreVisitante + " no pudo acceder a " + nombre + " (timeout).");
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void bajar(String nombreVisitante) {
        visitantesActuales--;
        System.out.println(nombreVisitante + " bajó de " + nombre);
    }

    public String getNombre() {
        return nombre;
    }
}
