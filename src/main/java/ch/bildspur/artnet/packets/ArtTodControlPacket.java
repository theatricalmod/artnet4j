package ch.bildspur.artnet.packets;

public class ArtTodControlPacket extends ArtNetPacket{

    private boolean isFlush = false;
    public ArtTodControlPacket() {
        super(PacketType.ART_TOD_CONTROL);
    }
    private int subnetID;
    private int universeID;


    @Override
    public boolean parse(byte[] raw) {
        setData(raw);
        if(data.getInt8(22) == 0x01){
            isFlush = true;
        }
        int portAddress = data.getInt8(23);
        universeID = portAddress & 0xF;
        subnetID = (portAddress >> 4) & 0xF;
        return true;
    }

    public boolean isFlush() {
        return isFlush;
    }

    public int getSubnetID() {
        return subnetID;
    }

    public int getUniverseID() {
        return universeID;
    }
}
