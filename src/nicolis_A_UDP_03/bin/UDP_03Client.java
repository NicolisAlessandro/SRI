package nicolis_A_UDP_03.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_03Client {
    public static void main(String[] args) throws IOException {

        Scanner text = new Scanner(System.in);
        DatagramPacket p_send, p_rece;
        String mex;
        boolean fine = true;
        InetAddress ip = InetAddress.getByName("172.16.20.14");
        DatagramSocket socket = new DatagramSocket();
        System.out.println("socket = " + socket.getInetAddress());
        System.out.println(1);

        do {
            mex = text.nextLine();

            byte[] b = mex.getBytes();
            p_send = new DatagramPacket(
                    b,
                    b.length,
                    ip,
                    2000);

            socket.send(p_send);
            p_rece = new DatagramPacket(b, b.length);
            socket.receive(p_rece);

            if (mex.equalsIgnoreCase("***")) {
                fine = false;
            }
            System.out.println("fine = " + fine);
        } while (fine);
    }
}
