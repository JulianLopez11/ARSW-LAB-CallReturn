package domain.sockets.firstPart;

import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Y el host????.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener el  I/O para "
                    + "la conexion.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        System.out.println("Escibe el Numero Que Quieres Al Cuadrado tilino: ");
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("RESPUESTA AL CUADRADO DEL NUMEROOO: " + in.readLine());
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}