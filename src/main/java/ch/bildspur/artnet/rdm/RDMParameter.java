package ch.bildspur.artnet.rdm;

public enum RDMParameter {

    DISC_UNIQUE_BRANCH(0x0001),
    DISC_MUTE(0x0002),
    DISC_UNMUTE(0x0003),

    PROXIED_DEVICES(0x0010),
    PROXIED_DEVICES_COUNT(0x0011),
    QUEUED_MESSAGE(0x0020),
    SUPPORTED_PARAMETERS(0x0050),
    PARAMETER_DESCRIPTION(0x0051),
    DEVICE_INFO(0x0060),
    SOFTWARE_VERSION_LABEL(0x00C0),
    DMX_START_ADDRESS(0x00F0),
    IDENTIFY_DEVICE(0x1000),
    FACTORY_DEFAULTS(0x0090),
    LAMP_STATE(0x0403),
    LAMP_HOURS(0x0401),
    LAMP_STRIKES(0x0402),
    DEVICE_HOURS(0x0400),
    DMX_PERSONALITY_DESCRIPTION(0x00E1),
    DMX_PERSONALITY(0x00E0),
    DEVICE_MODEL_DESCRIPTION(0x0080),
    MANUFACTURER_LABEL(0x0081),
    SLOT_INFO(0x0120),
    SLOT_DESCRIPTION(0x0121),
    DEFAULT_SLOT_VALUE(0x0122),
    STATUS_MESSAGES(0x0030);
    private final int id;
    RDMParameter(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RDMParameter byId(int id){
        for (RDMParameter value : values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }
}
