package domain.exercise1;
import java.net.URL;
import java.io.*;

public class UrlReader {

    public void readUrl(String url) throws Exception{
        
        URL urlVar = new URL(url);
        System.out.println("Reading from URL: " + url);
        System.out.println("Authority: " + urlVar.getAuthority());
        System.out.println("Protocol: " + urlVar.getProtocol());
        System.out.println("Host: " + urlVar.getHost());
        System.out.println("Port: " + urlVar.getPort());
        System.out.println("Path: " + urlVar.getPath());
        
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(urlVar.openStream()))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }
    
}
