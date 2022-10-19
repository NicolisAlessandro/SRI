package nicolis_A_Udp_01.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

class Udp_01{
	public static void main(String[] args) throws IOException {

		InetAddress ip1 = InetAddress.getByName("172.16.1.99");
		int port;
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket p_send,p_rece;

		port=13;// 7 echo
		// 13 dayTime

		String mex="meprova";
		byte[] sendBuf = mex.getBytes();
		p_send = new DatagramPacket(sendBuf, sendBuf.length,ip1,port);
		byte[] recBuf = new byte[256];
		p_rece=new DatagramPacket(recBuf,recBuf.length);
		socket.send(p_send);
		socket.receive(p_rece);
		String rec=new String(recBuf,0,p_rece.getLength());

		Date d = new Date();

	}
}