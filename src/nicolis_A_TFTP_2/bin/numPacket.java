package nicolis_A_TFTP_2.bin;

public enum numPacket {
    REQ_DOCENTE(1),
    REQ_AULA(2),
    REQ_CLASSE(3),
    REQ_DOCENTE_ADESSO(11),
    REQAULA_ADESSO(12),
    REQ_CLASSE_ADESSO(13),
    REPLY(21),
    DATA_ERROR(22),
    END_OF_DATA(23),
    REQ_REGISTRAZIONE(24),
    REG_SUCCESS(25),
    REG_ERROR(26);

    private final int operationCode;

    numPacket(int packetTypeInt) {
        operationCode = packetTypeInt;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public numPacket findByValue(int intType) {
        for (numPacket packetOperationCode : values()) {
            if (packetOperationCode.getOperationCode() == intType) {
                return packetOperationCode;
            }
        }
        return null;
    }

}
