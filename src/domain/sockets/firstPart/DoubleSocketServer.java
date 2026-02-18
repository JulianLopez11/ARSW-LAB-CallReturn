package domain.sockets.firstPart;

import java.net.*;
import java.io.*;

public class DoubleSocketServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("No se pudo escuchar: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Fallo aceptar lo siento.");
            System.exit(1);
        }
        
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje Mandado Por el grandisimo cliente: " + inputLine);
            try {
                double number = Double.parseDouble(inputLine);
                double square = number * number;
                outputLine = "Respuesta: " + square;
            } catch (NumberFormatException e) {
                outputLine = "Mmmm, numero invalido";
            }
            out.println(outputLine);
            if (inputLine.equals("Adiosito."))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}