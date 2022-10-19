package nicolis_A_TFTP_2.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestSocket {
    public static void main(String[] args) {
        try {
            String hostname = "172.16.1.99";
            int port = 3232;
            Socket cs = new Socket(hostname, port);
            System.out.println(cs.getLocalSocketAddress() + "/" + cs.getRemoteSocketAddress());
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String l = sc.nextLine();
            while (l.length() > 0) {
                out.println(l);
                System.out.println("-->" + in.readLine());
                l = sc.nextLine();
            }
            cs.close();

        } catch (IOException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
    }
}
