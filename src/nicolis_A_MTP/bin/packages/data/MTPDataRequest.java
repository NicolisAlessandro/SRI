package nicolis_A_MTP.bin.packages.data;

import nicolis_A_MTP.bin.PacketOperationCode;
import nicolis_A_MTP.bin.Utility;
import nicolis_A_MTP.bin.packages.MTPPacket;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MTPDataRequest extends MTPPacket {
    private final int uuid;


    public MTPDataRequest(PacketOperationCode operationCode, int uuid) {
        super(operationCode);
        this.uuid = uuid;
        super.bytePacket = getBytePacket();
    }

    public MTPDataRequest(byte[] bytePacket) {
        super(Utility.trim(bytePacket));
        uuid = new BigInteger(Arrays.copyOfRange(super.bytePacket, 1, 1 + Integer.BYTES)).intValue();
    }

    @Override
    public byte[] getBytePacket() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(ByteBuffer.allocate(Integer.BYTES).putInt(uuid).array());

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTSDataRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tuuid=" + uuid +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }
}
