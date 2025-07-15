import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer extends JFrame {
    private JTextArea chatArea;
    private final int PORT = 9876;
    private DatagramSocket serverSocket;

    public UDPServer() {
        setTitle("Servidor UDP");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        setVisible(true);
        iniciarServidor();
    }

    private void iniciarServidor() {
        new Thread(() -> {
            try {
                serverSocket = new DatagramSocket(PORT);
                byte[] receiveBuffer = new byte[1024];

                chatArea.append("Servidor escuchando en el puerto " + PORT + "...\n");

                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    serverSocket.receive(receivePacket);

                    String mensaje = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();

                    String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                    chatArea.append("[" + hora + "] [" + clientAddress + ":" + clientPort + "] " + mensaje + "\n");

                    if (mensaje.equalsIgnoreCase("FIN")) {
                        chatArea.append("Mensaje de cierre recibido. Servidor finalizando.\n");
                        break;
                    }
                }
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
                chatArea.append("Error en el servidor: " + e.getMessage() + "\n");
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UDPServer::new);
    }
}
