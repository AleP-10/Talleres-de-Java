import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParqueDeAtracciones {
    public static void main (String[] args) {
        // 1. Crear las atracciones con su capacidad
        List<Atraccion> atracciones = Arrays.asList(
                new Atraccion("Montaña_Rusa", 2),
                new Atraccion("Carritos_Chocones", 3),
                new Atraccion("Carrusel", 4)
        );

        // 2. Crear visitantes (hilos)
        List<Visitante> visitantes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            visitantes.add(new Visitante("Visitante " + i, atracciones));
        }

        // 3. Iniciar todos los hilos
        visitantes.forEach(Thread::start);

        // 4. Esperar a que todos terminen
        visitantes.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 5. Finalizar
        System.out.println("¡Simulación terminada!");
    }
}