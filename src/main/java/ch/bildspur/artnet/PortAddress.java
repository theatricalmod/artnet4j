package ch.bildspur.artnet;

public class PortAddress {

    private int net, subNet, universe;

    public PortAddress(int net, int subNet, int universe){
        this.net = net;
        this.subNet = subNet;
        this.universe = universe;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getSubNet() {
        return subNet;
    }

    public void setSubNet(int subNet) {
        this.subNet = subNet;
    }

    public int getUniverse() {
        return universe;
    }

    public void setUniverse(int universe) {
        this.universe = universe;
    }

    public int toPortAddress(){
        return (net & 0x7f << 8 | subNet & 0xF << 4 | universe & 0xF);
    }
}
