package domain.sockets.secondPart;

import java.net.*;
import java.io.*;

public class FunSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Servidor listo. Esperando conexión...");
        } catch (IOException e) {
            System.err.println("No se pudo escuchar en el puerto: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Fallo al aceptar la conexión.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        String currentFunction = "cos";

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Cliente 'expresa': " + inputLine);

            if (inputLine.startsWith("fun:")) {
                String nuevaFun = inputLine.substring(4).toLowerCase();
                if (nuevaFun.equals("sin") || nuevaFun.equals("cos") || nuevaFun.equals("tan")) {
                    currentFunction = nuevaFun;
                    outputLine = "Cambiado a función: " + currentFunction;
                } else {
                    outputLine = "Error: Función no soportada. Usa sin, cos o tan.";
                }
            } 
            else {
                try {
                    double number = Double.parseDouble(inputLine);
                    double result = 0;

                    switch (currentFunction) {
                        case "sin": result = Math.sin(number); break;
                        case "cos": result = Math.cos(number); break;
                        case "tan": result = Math.tan(number); break;
                    }
                    outputLine = "Resultado (" + currentFunction + "): " + result;
                } catch (NumberFormatException e) {
                    if (inputLine.equalsIgnoreCase("Adiosito.")) break;
                    outputLine = "Error: Envíe un número válido o 'fun:nombre_funcion'";
                }
            }

            out.println(outputLine);
            if (inputLine.equalsIgnoreCase("Adiosito.")) break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}