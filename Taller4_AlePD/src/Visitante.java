import java.util.List;
import java.util.Random;

public class Visitante extends Thread {
    private final String nombre;
    private final List<Atraccion> atracciones;
    private final Random random = new Random();

    public Visitante(String nombre, List<Atraccion> atracciones) {
        this.nombre = nombre;
        this.atracciones = atracciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            Atraccion atraccion = atracciones.get(random.nextInt(atracciones.size()));
            if (!atraccion.intentarSubir(nombre)) {
                try {
                    Thread.sleep(500 + random.nextInt(500)); // Espera para intentar otra vez
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                break; // Si logra subirse, finaliza
            }
        }
    }
}
