package nicolis_A_MTP.bin;

import nicolis_A_MTP.bin.packages.MTPPacket;

import java.io.IOException;
import java.net.InetAddress;

public class MTPClient extends MTP {

    public MTPClient(int serverPort) {
        super(serverPort);
    }

    @Override
    public void connect(InetAddress targetAddress, int targetPort) {
        super.connect(targetAddress, targetPort);
        try {
            setUpStreams(activeConnectionSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPacket(MTPPacket packet) {
        if (isConnected) {
            System.out.println("MTPClient.sendPacket");
            try {
                outputStream.write(packet.getBytePacket());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not connection! Use .connect() before send packet");
        }
    }

}
