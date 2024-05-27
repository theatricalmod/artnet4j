package ch.bildspur.artnet.rdm;

public enum RDMSlotType {

    ST_PRIMARY(0x00);

    private final int id;
    RDMSlotType(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RDMSlotType byId(int id){
        for (RDMSlotType value : values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }

}
