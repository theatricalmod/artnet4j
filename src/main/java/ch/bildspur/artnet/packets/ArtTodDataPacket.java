package ch.bildspur.artnet.packets;

import java.util.ArrayList;
import java.util.List;

public class ArtTodDataPacket extends ArtNetPacket{
    private static int ARTTODDATA_LENGTH = 28;

    private List<byte[]> uids;
    private int totalDevices = 0;
    private int packetIndex = 0;
    private int subnetID;
    private int universeID;

    public ArtTodDataPacket() {
        super(PacketType.ART_TOD_DATA);
        setData(new byte[ARTTODDATA_LENGTH]);
        setHeader();
        setProtocol();
        uids = new ArrayList<>();
        refreshData();
    }

    @Override
    public boolean parse(byte[] raw) {
        setData(raw, ARTTODDATA_LENGTH);
        return false;
    }

    public void addDevice(byte[] uid){
        uids.add(uid);
        refreshData();
    }
    public void setUniverse(int subnetID, int universeID) {
        this.subnetID = subnetID & 0x0f;
        this.universeID = universeID & 0x0f;
        data.setInt8(subnetID << 4 | universeID, 16);
        logger.finer("universe ID set to: subnet: "
                + ByteUtils.hex(subnetID, 2) + "/"
                + ByteUtils.hex(universeID, 2));
    }
    public void refreshData(){
        setData(new byte[ARTTODDATA_LENGTH + (uids.size() * 6)]);
        setHeader();
        setProtocol();
        data.setInt8(0x01, 12);
        data.setInt8(0x01, 13);
        data.setInt8(0x00, 20);
        data.setInt8(0x00, 21);
        data.setInt8(0x00, 22);
        data.setInt8(subnetID << 4 | universeID, 23);
        data.setInt8(totalDevices >> 8, 24);
        data.setInt8(totalDevices & 0xFF, 25);
        data.setInt8(packetIndex, 26);
        data.setInt8(uids.size(), 27);
        for(int i = 0; i < uids.size(); i++){
            byte[] bytes = uids.get(i);
            data.setByteChunk(bytes, (28 + (i * 6)), bytes.length);
        }
    }

    public void setTotalDevices(int totalDevices) {
        this.totalDevices = totalDevices;
    }
}
