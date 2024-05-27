package ch.bildspur.artnet.rdm;

public enum RDMCommandClass {


    DISCOVERY_COMMAND(0x10, "DISCOVERY_COMMAND"),
    DISCOVERY_COMMAND_RESPONSE(0x11, "DISCOVERY_COMMAND_RESPONSE"),
    GET_COMMAND(0x20, "GET_COMMAND"),
    GET_COMMAND_RESPONSE(0x21, "GET_COMMAND_RESPONSE"),
    SET_COMMAND(0x30, "SET_COMMAND"),
    SET_COMMAND_RESPONSE(0x31, "SET_COMMAND_RESPONSE");

    private int id;
    private String name;
    RDMCommandClass(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RDMCommandClass byId(int id){
        for (RDMCommandClass value : values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }
}
