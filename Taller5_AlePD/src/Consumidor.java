import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumidor implements Runnable {
    private final String nombreOperario;
    private final BlockingQueue<Pedido> colaPedidos;
    private final AtomicInteger contadorProcesados;
    private final int totalPedidos;

    public Consumidor(String nombreOperario, BlockingQueue<Pedido> colaPedidos, AtomicInteger contadorProcesados, int totalPedidos) {
        this.nombreOperario = nombreOperario;
        this.colaPedidos = colaPedidos;
        this.contadorProcesados = contadorProcesados;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pedido pedido = colaPedidos.poll(5, TimeUnit.SECONDS);
                if (pedido == null) break;
                System.out.println("[PROCESADO] Pedido " + pedido.getId() + " por " + nombreOperario);
                int total = contadorProcesados.incrementAndGet();
                System.out.println("[CONTADOR] Total procesados: " + total);
                if (total >= totalPedidos) break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

