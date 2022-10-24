package nicolis_A_MTP.bin.packages.registration;

import nicolis_A_MTP.bin.PacketOperationCode;
import nicolis_A_MTP.bin.Utility;
import nicolis_A_MTP.bin.packages.MTPPacket;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTPRegistrationRequest extends MTPPacket {
    private final String name;

    public MTPRegistrationRequest(String name) {
        super(PacketOperationCode.REQ_REGISTRAZIONE);
        this.name = name;
        super.bytePacket = getBytePacket();
    }

    public MTPRegistrationRequest(byte[] bytePacket) {
        super(Utility.trim(bytePacket));
        name = new String(Arrays.copyOfRange(super.bytePacket, 1, super.bytePacket.length));
    }

    @Override
    public byte[] getBytePacket() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(name.getBytes());

        return outputStream.toByteArray();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MTSRegistrationRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tname='" + name + '\'' +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }
}
