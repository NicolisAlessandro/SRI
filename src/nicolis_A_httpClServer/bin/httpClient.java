package nicolis_A_httpClServer.bin;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class httpClient {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        URL url = new URL(args[0]);

        String hostname = url.getHost();
        int port = 80;

        Socket s = new Socket(hostname, port);

        System.out.println("Socket creata: " + s);

        OutputStream output = s.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        writer.println("HEAD " + url.getPath() + " HTTP/1.1");
        writer.println("Host: " + hostname);
        writer.println("User-Agent: Simple Http Client");
        writer.println("Accept: text/html");
        writer.println("Accept-Language: en-US");
        writer.println("Connection: close");
        writer.println();

        InputStream input = s.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}