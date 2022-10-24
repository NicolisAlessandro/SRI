package nicolis_A_MTP.bin;

import nicolis_A_MTP.bin.connections.ConnectionReceivedEvent;
import nicolis_A_MTP.bin.packages.MTPPacket;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationError;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationRequest;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationSuccess;

import java.io.IOException;

public class MTPServer extends MTP {

    public MTPServer(int serverPort) {
        super(serverPort);
    }

    @Override
    protected void peerToPeer(ConnectionReceivedEvent event) {
        super.peerToPeer(event);
        isConnected = true;
        System.out.println(receivePacket().toString());
    }

    public MTPPacket receivePacket() {
        if (isConnected) {
            try {
                byte[] receiveBuff = inputStream.readAllBytes();
                PacketOperationCode type = PacketOperationCode.findByValue(receiveBuff[0]);
                if (type != null) {
                    return switch (type) {
                        case REQ_REGISTRAZIONE -> new MTPRegistrationRequest(receiveBuff);
                        case REG_SUCCESS -> new MTPRegistrationSuccess(receiveBuff);
                        case REG_ERROR -> new MTPRegistrationError(receiveBuff);
                        default -> null;
                    };
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
