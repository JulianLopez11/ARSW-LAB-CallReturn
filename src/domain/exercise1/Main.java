package domain.exercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        UrlReader reader = new UrlReader();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa la URL a leer: ");
        String url = scanner.nextLine();
        reader.readUrl(url);
        scanner.close();
    }
}
