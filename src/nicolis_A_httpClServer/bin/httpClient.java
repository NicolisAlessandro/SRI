package nicolis_A_httpClServer.bin;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class httpClient {

    public static void main(String[] args) throws IOException {

        URL url = new URL(args[0]);

        Socket s = null;
        DataInputStream is = null;
        try
        {
            s = new Socket("localhost", 11111);
            is = new DataInputStream(s.getInputStream());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Socket creata: " + s);
        try
        {
            String line;
            while( (line=is.readLine())!=null )
            {
                System.out.println("Ricevuto: " + line);
                if (line.equals("Stop"))
                    break;
            }
            is.close();
            s.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}