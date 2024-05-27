package ch.bildspur.artnet.rdm;

import ch.bildspur.artnet.packets.ArtNetPacket;
import ch.bildspur.artnet.packets.ByteUtils;
import ch.bildspur.artnet.packets.PacketType;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Logger;

public class RDMPacket {

    public static final Logger logger =
            Logger.getLogger(ArtNetPacket.class.getClass().getName());

    protected ByteUtils data;
    private int messageLength, transactionID, portID, messageCount, commandClassId, parameterDataLength, checksumHigh, checksumLow;
    private short subDevice, parameterID;
    private byte[] parameterData, destinationID, sourceID;
    private RDMCommandClass commandClass;
    private RDMParameter parameter;

    public byte[] getData() {
        return data.getBytes();
    }

    /**
     * Returns the actually used length of the data buffer.
     *
     * @return Length of the data buffer.
     */
    public int getLength() {
        return data.length;
    }

    /**
     * Parses the given byte array into semantic values and populates type
     * specific fields for each packet type. Implementing classes do not need to
     * check the packet header anymore since this has already been done at this
     * stage.
     *
     * @param raw Raw data.
     * @return true, if there were no parse errors
     */
    public boolean parse(byte[] raw) {
        setData(raw);
        messageLength = data.getInt8(2);
        destinationID = new byte[6];
        data.getByteChunk(destinationID, 3, 6);
        sourceID = new byte[6];
        data.getByteChunk(sourceID, 9, 6);
        transactionID = data.getInt8(15);
        portID = data.getInt8(16);
        messageCount = data.getInt8(17);
        subDevice = (short) data.getInt16(18);
        commandClassId = data.getInt8(20);
        commandClass = RDMCommandClass.byId(commandClassId);
        parameterID = (short) data.getInt16(21);
        parameter = RDMParameter.byId(parameterID);
        parameterDataLength = data.getInt8(23);
        parameterData = new byte[parameterDataLength];
        data.getByteChunk(parameterData, 24, parameterDataLength);
        checksumHigh = data.getInt8(messageLength);
        checksumLow = data.getInt8(messageLength + 1);
        return true;
    }

    public void write(){
        messageLength = 24 + parameterDataLength;
        setData(new byte[messageLength + 2]);
//        data.setInt8(0xCC, 0);
        data.setInt8(0x01, 0);
        data.setInt8(messageLength, 1);
        data.setByteChunk(destinationID, 2, 6);
        data.setByteChunk(sourceID, 8, 6);
        data.setInt8(transactionID, 14);
        data.setInt8(portID, 15);
        data.setInt8(messageCount, 16);
        data.setInt16(subDevice,17);
        data.setInt8(commandClassId, 19);
        data.setInt16(parameterID, 20);
        data.setInt8(parameterDataLength, 22);
        if(parameterData == null){
            parameterData = new byte[parameterDataLength];
        }
        data.setByteChunk(parameterData, 23, parameterDataLength);
        int checksum = 0;
        for (int i = 0; i < messageLength; i++) {
            checksum += (data.getInt8(i) & 0xFF); // ensure b is treated as an unsigned byte
        }
        checksum += 0xCC;
        short checkSum = (short) (checksum & 0xFFFF);
        checksumHigh = (checksum >> 8) & 0xFF;
        checksumLow = checkSum & 0xFF;
        data.setInt8(checksumHigh, messageLength - 1);
        data.setInt8(checksumLow, messageLength);
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(byte[] data) {
        this.data = new ByteUtils(data);
    }

    public void setData(byte[] raw, int maxLength) {
        if (raw.length > maxLength) {
            byte[] raw2 = new byte[maxLength];
            System.arraycopy(raw, 0, raw2, 0, maxLength);
            raw = raw2;
        }
        setData(raw);
    }

    public int getMessageLength() {
        return messageLength;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getPortID() {
        return portID;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public int getCommandClassId() {
        return commandClassId;
    }

    public int getParameterDataLength() {
        return parameterDataLength;
    }

    public int getChecksumHigh() {
        return checksumHigh;
    }

    public int getChecksumLow() {
        return checksumLow;
    }

    public short getSubDevice() {
        return subDevice;
    }

    public short getParameterID() {
        return parameterID;
    }

    public byte[] getDestinationID() {
        return destinationID;
    }

    public byte[] getSourceID() {
        return sourceID;
    }

    public byte[] getParameterData() {
        return parameterData;
    }

    public RDMCommandClass getCommandClass() {
        return commandClass;
    }

    public RDMParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "ArtRdmPacket{" +
                "messageLength=" + messageLength +
                ", transactionID=" + transactionID +
                ", portID=" + portID +
                ", messageCount=" + messageCount +
                ", commandClassId=" + commandClassId +
                ", parameterDataLength=" + parameterDataLength +
                ", checksumHigh=" + checksumHigh +
                ", checksumLow=" + checksumLow +
                ", subDevice=" + subDevice +
                ", parameterID=" + parameterID +
                ", destinationID=" + Arrays.toString(destinationID) +
                ", sourceID=" + Arrays.toString(sourceID) +
                ", parameterData=" + Arrays.toString(parameterData) +
                ", commandClass=" + commandClass +
                ", parameter=" + parameter +
                '}';
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setPortID(int portID) {
        this.portID = portID;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
    public void setParameterDataLength(int parameterDataLength) {
        this.parameterDataLength = parameterDataLength;
    }

    public void setChecksumHigh(int checksumHigh) {
        this.checksumHigh = checksumHigh;
    }

    public void setChecksumLow(int checksumLow) {
        this.checksumLow = checksumLow;
    }

    public void setSubDevice(short subDevice) {
        this.subDevice = subDevice;
    }
    public void setDestinationID(byte[] destinationID) {
        this.destinationID = destinationID;
    }

    public void setSourceID(byte[] sourceID) {
        this.sourceID = sourceID;
    }

    public void setParameterData(byte[] parameterData) {
        this.parameterData = parameterData;
    }

    public void setCommandClass(RDMCommandClass commandClass) {
        this.commandClass = commandClass;
        this.commandClassId = commandClass.getId();
    }

    public void setParameter(RDMParameter parameter) {
        this.parameter = parameter;
        this.parameterID = (short) parameter.getId();
    }
}
