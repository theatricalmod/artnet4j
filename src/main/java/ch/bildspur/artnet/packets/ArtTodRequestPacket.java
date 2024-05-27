package ch.bildspur.artnet.packets;

public class ArtTodRequestPacket extends ArtNetPacket{
    private static int ARTRODREQUEST_LENGTH = 18;
    private int subnetID;
    private int universeID;

    public ArtTodRequestPacket() {
        super(PacketType.ART_TOD_REQUEST);
        setData(new byte[ARTRODREQUEST_LENGTH]);
        setHeader();
        setProtocol();
    }

    @Override
    public boolean parse(byte[] raw) {
        setData(raw, ARTRODREQUEST_LENGTH);
        int portAddress = data.getInt8(17);
        universeID = portAddress & 0xF;
        subnetID = (portAddress >> 4) & 0xF;
        return true;
    }

    public int getSubnetID() {
        return subnetID;
    }

    public int getUniverseID() {
        return universeID;
    }
}
