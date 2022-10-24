package nicolis_A_MTP.bin.connections;

import java.util.EventListener;

@FunctionalInterface
public interface ConnectionReceivedListener extends EventListener {
    void onConnectionReceived(ConnectionReceivedEvent event);
}