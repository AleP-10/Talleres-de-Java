import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulacionPedidos {
    public static void main(String[] args) {
        final int TOTAL_PEDIDOS = 10;
        BlockingQueue<Pedido> colaPedidos = new LinkedBlockingQueue<>();
        AtomicInteger contadorProcesados = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new Productor("Cliente1", colaPedidos, TOTAL_PEDIDOS));
        executor.execute(new Productor("Cliente2", colaPedidos, TOTAL_PEDIDOS));
        executor.execute(new Consumidor("Operario1", colaPedidos, contadorProcesados, TOTAL_PEDIDOS));
        executor.execute(new Consumidor("Operario2", colaPedidos, contadorProcesados, TOTAL_PEDIDOS));

        executor.shutdown();
    }
}