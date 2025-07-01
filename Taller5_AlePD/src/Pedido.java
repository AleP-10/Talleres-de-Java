// Clase Pedido
public class Pedido {
    private final int id;
    private final String cliente;

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
}

