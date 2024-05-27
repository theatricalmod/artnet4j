package ch.bildspur.artnet.rdm;

import ch.bildspur.artnet.packets.ByteUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class RDMDeviceId {

    private final short manufacturerId;
    private final byte[] deviceId;

    public RDMDeviceId(short manufacturerId, byte[] deviceId){
        this.manufacturerId = manufacturerId;
        this.deviceId = deviceId;
    }

    public RDMDeviceId(byte[] bytes){
        ByteUtils byteUtils = new ByteUtils(bytes);
        this.manufacturerId = (short) byteUtils.getInt16(0);
        deviceId = new byte[4];
        byteUtils.getByteChunk(deviceId, 2, 4);
    }

    public byte[] toBytes(){
        ByteBuffer wrap = ByteBuffer.wrap(new byte[6]);
        wrap.putShort(manufacturerId);
        if(deviceId != null) {
            wrap.put(deviceId);
        }
        return wrap.array();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RDMDeviceId deviceId1 = (RDMDeviceId) o;
        return manufacturerId == deviceId1.manufacturerId && Arrays.equals(deviceId, deviceId1.deviceId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(manufacturerId);
        result = 31 * result + Arrays.hashCode(deviceId);
        return result;
    }
}
