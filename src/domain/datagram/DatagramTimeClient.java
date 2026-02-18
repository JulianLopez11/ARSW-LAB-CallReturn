package domain.datagram;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {

    private DatagramSocket socket;
    private InetAddress address;
    private int port = 35000;
    private String lastTime = "Sin datos aún";

    public DatagramTimeClient(String host) {
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(2000); 
            address = InetAddress.getByName(host);
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startClient() {

        byte[] buffer = new byte[256];

        while (true) {
            try {
                // Enviar solicitud al servidor
                String message = "¿Hora?";
                buffer = message.getBytes();
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(request);

                // Preparar para recibir respuesta
                buffer = new byte[256];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);

                socket.receive(response); 

                String received = new String(response.getData(), 0, response.getLength());
                lastTime = received;

                System.out.println("Hora actualizada desde servidor: " + lastTime);

            } catch (SocketTimeoutException e) {
                System.out.println("Servidor no disponible. Manteniendo hora: " + lastTime);
            } catch (IOException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        DatagramTimeClient client = new DatagramTimeClient("localhost");
        client.startClient();
    }
}
