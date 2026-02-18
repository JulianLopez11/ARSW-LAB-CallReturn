package domain.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatagramTimeServer {
    DatagramSocket socket;
    
    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(35000);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer() {
        while (true) {
            try {
                byte[] buffer = new byte[256];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String dString = new Date().toString();
                byte[] responseBuffer = dString.getBytes();

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                DatagramPacket response =
                        new DatagramPacket(responseBuffer, responseBuffer.length, address, port);

                socket.send(response);

                System.out.println("Hora enviada a " + address + ":" + port);

            } catch (IOException ex) {
                Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        DatagramTimeServer server = new DatagramTimeServer();
        server.startServer();
    }

    
}
