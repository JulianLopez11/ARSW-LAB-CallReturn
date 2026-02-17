package domain.exercise2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class Browser {
    
    public void htmlStorage(String urlToRead) throws Exception {
        URL ulr = new URL(urlToRead); 
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ulr.openStream()));
            PrintWriter writer = new PrintWriter(new FileWriter("resultado.html"))) { 
            
            String inputLine = null; 
            while ((inputLine = reader.readLine()) != null) { 
                writer.println(inputLine);
            }
        } catch (IOException x) { 
            System.err.println(x); 
        } 
    } 
}