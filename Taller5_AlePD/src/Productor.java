import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Productor implements Runnable {
    private static final AtomicInteger idPedido = new AtomicInteger(1);
    private final String nombreCliente;
    private final BlockingQueue<Pedido> colaPedidos;
    private final int totalPedidos;

    public Productor(String nombreCliente, BlockingQueue<Pedido> colaPedidos, int totalPedidos) {
        this.nombreCliente = nombreCliente;
        this.colaPedidos = colaPedidos;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalPedidos / 2; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
                int id = idPedido.getAndIncrement();
                Pedido pedido = new Pedido(id, nombreCliente);
                colaPedidos.put(pedido);
                System.out.println("[CREADO] Pedido " + id + " por " + nombreCliente);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
