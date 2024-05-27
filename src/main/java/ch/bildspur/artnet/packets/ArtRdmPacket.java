package ch.bildspur.artnet.packets;

import ch.bildspur.artnet.rdm.RDMPacket;
public class ArtRdmPacket extends ArtNetPacket{

    private static final int ARTRDM_LENGTH = 17;

    private RDMPacket rdmPacket;
    private int net, address;

    public ArtRdmPacket() {
        super(PacketType.ART_RDM);
        setData(new byte[ARTRDM_LENGTH]);
        setHeader();
        setProtocol();
    }

    @Override
    public boolean parse(byte[] raw) {
        setData(raw);
        this.net = data.getInt8(21);
        this.address = data.getInt8(23);
        byte[] rdmPacketRaw = new byte[raw.length - 25];
        data.getByteChunk(rdmPacketRaw, 23, rdmPacketRaw.length);
        this.rdmPacket = new RDMPacket();
        this.rdmPacket.parse(rdmPacketRaw);
        return true;
    }

    public void write(){
        byte[] rdmPacketData = new byte[0];
        if(this.rdmPacket != null) {
            this.rdmPacket.write();
            rdmPacketData = this.rdmPacket.getData();
        }
        setData(new byte[24 + rdmPacketData.length]);
        setHeader();
        setProtocol();
        data.setInt8(0x01, 12);
        data.setInt8(net, 21);
        data.setInt8(0x00, 22);
        data.setInt8(address, 23);
        data.setByteChunk(rdmPacketData, 24);
    }

    public RDMPacket getRdmPacket() {
        return rdmPacket;
    }

    public void setRdmPacket(RDMPacket rdmPacket) {
        this.rdmPacket = rdmPacket;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}
