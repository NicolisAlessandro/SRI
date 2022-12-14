package nicolis_A_MTP.bin.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionReceivedEvent {
    private final Socket accepted;

    public ConnectionReceivedEvent(Socket sock) {
        this.accepted = sock;
    }

    public Socket getSocket() {
        return accepted;
    }

    public OutputStream getOutput() throws IOException {
        return accepted.getOutputStream();
    }

    public InputStream getInput() throws IOException {
        return accepted.getInputStream();
    }

    public int getPort() {
        return accepted.getPort();
    }
}