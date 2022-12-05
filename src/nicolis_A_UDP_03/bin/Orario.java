package nicolis_A_UDP_03.bin;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class Orario {
    public static void main (String[] args) throws IOException {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        while (cont) {
            numeroScelto();
            System.out.println("finito1");
            String controllo = sc.nextLine();
            if (controllo.equals("esci")){
                cont = false;
                System.out.println("finito2");
            }
        }
    }

    private static byte[] richiestaAccesso(byte num) throws IOException{
        Scanner sc = new Scanner(System.in);

        InetAddress ip1 = InetAddress.getByName("casadecarli.ddns.net");
        int port = 3232;
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket p_send, p_rece;

        System.out.print("Inserisci nome utente: ");
        String username=sc.nextLine();

        byte[] mex=username.getBytes();
        byte[] request = new byte[100];
        request[0]= num;
        int j=1;
        for (byte b : mex) {
            request[j++] = b;
        }

        byte[] recBuf = new byte[512];
        p_send = new DatagramPacket(request, j, ip1, port);
        p_rece = new DatagramPacket(recBuf, recBuf.length);

        socket.send(p_send);
        socket.receive(p_rece);

        recBuf = p_rece.getData();
        if (recBuf[0] == 25){
            System.out.println("giusto");
        }else if (recBuf[0] == 26){
            System.out.println("errato");
        }
        final byte[] cod = new byte[512];
        for (int i = 0; i < cod.length; i++) {
            cod[i] = recBuf[i];
        }
        return cod;
    }

    private static void invioDati(byte num, byte[] cod)throws IOException{
        Scanner sc = new Scanner(System.in);
        InetAddress ip1 = InetAddress.getByName("casadecarli.ddns.net");
        int port = 3232;
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket p_send, p_rece;

        byte [] fil = new byte[512];
        fil[0] = num;
        for (int i = 1; i < cod.length; i++) {
            fil[i] = cod[i];
        }
        String richiesta = sc.nextLine();
        byte [] ric = richiesta.getBytes();
        for (int i = cod.length; i < cod.length + ric.length ; i++) {
            fil[i] = ric[i];
        }

        p_send = new DatagramPacket(fil, fil.length, ip1, port);
        p_rece = new DatagramPacket(fil, fil.length);
        socket.send(p_send);

        do {
            socket.receive(p_rece);
            fil = p_rece.getData();
            for (byte b : fil) {
                System.out.println("contiene = " + b);
            }
        }while (fil[0] == 23);
    }

    private static void numeroScelto() throws IOException {
        Scanner sc = new Scanner(System.in);
        byte numScelto = sc.nextByte();
        byte[] codiceInt = new byte[5];
        switch (numScelto) {
            case 1, 2, 3 , 11, 12, 13 -> invioDati(numScelto, codiceInt);
            case 24 -> {
                codiceInt = richiestaAccesso(numScelto);
                System.out.println("codiceInt = " + Arrays.toString(codiceInt));
            }
            default -> System.out.println("numero errato");
        }

    }
}
/*
class UDPClient{
    public static final int REQ_DOCENTE = 1;
    public final int REQ_AULA = 2;
    public final int REQ_CLASSE = 3;
    public final int REQ_DOCENTE_ADESSO = 11;
    public final int REQ_AULA_ADESSO = 12;
    public final int REQ_CLASSE_ADESSO = 13;
    public static final int REPLY = 21;
    public final int DATA_ERROR = 22;
    public static final int END_OF_DATA = 23;
    public static final int REQ_REGISTRAZIONE = 24;
    public static final int REG_SUCCESS = 25;
    public static final int REG_ERROR = 26;

}*/