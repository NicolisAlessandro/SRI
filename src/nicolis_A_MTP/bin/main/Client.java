package nicolis_A_MTP.bin.main;

import nicolis_A_MTP.bin.MTPClient;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationRequest;

import java.net.*;

public class Client {
    public static void main(String[] args) {

        System.out.println("Start");

        InetAddress ipTarget = null;
        try {
            ipTarget = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        MTPClient mtpClient = new MTPClient(12345);

        mtpClient.connect(ipTarget, 1234);
        mtpClient.sendPacket(new MTPRegistrationRequest("nicolis"));

        System.out.println("End");

    }
}
