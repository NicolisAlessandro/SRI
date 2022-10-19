package nicolis_A_TPTF_01.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class TPTF_01Server {
        public static void main(String[] args) {
                try {
                        DatagramSocket sck = new DatagramSocket(69);
                        DatagramPacket p_send, p_rece;
                        byte[] recBuf = new byte[512];
                        p_rece = new DatagramPacket(recBuf, recBuf.length);

                        while (true){
                                sck.receive(p_rece);
                                p_send = new DatagramPacket(p_rece.getData(),
                                        p_rece.getLength(),
                                        p_rece.getSocketAddress());

                                sck.send(p_send);
                        }

                } catch (IOException e) {
                        throw new RuntimeException(e);
                }

        }
}