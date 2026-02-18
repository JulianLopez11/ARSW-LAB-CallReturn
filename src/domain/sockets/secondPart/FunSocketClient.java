package domain.sockets.secondPart;

import java.io.*;
import java.net.*;

public class FunSocketClient {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (Exception e) {
            System.err.println("Error de conexión.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Calculadora trigonometrica (Divertida)");
        System.out.println("La calculadora por defecto esta iniciando con coseno por si las moscas");
        System.out.println("Escribe un número (en radianes) o cambia la función con 'fun:sin', 'fun:cos' o 'fun:tan'");

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Servidor: " + in.readLine());
            
            if (userInput.equalsIgnoreCase("Adiosito.")) break;
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}