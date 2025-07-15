import javax.swing.*;
import java.awt.*;
import java.net.*;

public class UDPClient extends JFrame {
    private JTextArea chatArea;
    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private final int SERVER_PORT = 9876;
    private String clientID;

    public UDPClient() {
        setTitle("Cliente UDP");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        setVisible(true);

        // Solicitar identificador
        clientID = JOptionPane.showInputDialog(this, "Ingrese el identificador del cliente (ej: Cliente01):");
        if (clientID == null || clientID.trim().isEmpty()) {
            clientID = "ClienteDesconocido";
        }

        iniciarCliente();
    }

    private void iniciarCliente() {
        new Thread(() -> {
            try {
                clientSocket = new DatagramSocket();
                serverAddress = InetAddress.getByName("127.0.0.1");

                for (int i = 1; i <= 5; i++) {
                    String mensaje = clientID + " - Mensaje #" + i;
                    byte[] sendBuffer = mensaje.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, SERVER_PORT);
                    clientSocket.send(sendPacket);

                    chatArea.append("Enviado: " + mensaje + "\n");
                    Thread.sleep(2000);
                }

                // Si el cliente es Cliente02, envía mensaje FIN
                if (clientID.equalsIgnoreCase("Cliente02")) {
                    String mensajeFin = "FIN";
                    byte[] finBuffer = mensajeFin.getBytes();
                    DatagramPacket finPacket = new DatagramPacket(finBuffer, finBuffer.length, serverAddress, SERVER_PORT);
                    clientSocket.send(finPacket);
                    chatArea.append("Enviado: " + mensajeFin + "\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
                chatArea.append("Error en el cliente: " + e.getMessage() + "\n");
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UDPClient::new);
    }
}
