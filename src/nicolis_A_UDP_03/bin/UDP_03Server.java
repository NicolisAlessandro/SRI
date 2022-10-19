package nicolis_A_UDP_03.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDP_03 {
    public static void main(String[] args) throws IOException {
        InetAddress ip;
        DatagramSocket sck = null;

        sck = new DatagramSocket(2000);
        ip = InetAddress.getByName("172.16.20.14");
        System.out.println("sck = " + sck.getInetAddress());
        DatagramPacket p_rece, p_send;

        byte[] recBuf = new byte[2048];

        p_rece = new DatagramPacket(
                recBuf,
                recBuf.length);

        while (true) {
            System.out.println(1);
            sck.receive(p_rece);
            System.out.println(2);
            System.out.println("p_rece = " + new String(p_rece.getData()));
            p_send = new DatagramPacket(
                    p_rece.getData(),
                    p_rece.getLength(),
                    p_rece.getSocketAddress());
            sck.send(p_send);
        }


    }
}