package domain.exercise2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Browser browser = new Browser();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa la URL a leer: ");
        String url = scanner.nextLine();


        browser.htmlStorage(url);

        System.out.println("Abra el archivo resultado.html para ver el contenido de lo que ingreso");
        scanner.close();
    }
}
