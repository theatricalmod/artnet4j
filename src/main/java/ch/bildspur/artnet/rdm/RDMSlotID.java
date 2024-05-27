package ch.bildspur.artnet.rdm;

public enum RDMSlotID {

    SD_INTENSITY(0x0001),
    SD_INTENSITY_MASTER(0x0002),
    SD_PAN(0x0101),
    SD_TILT(0x0102),
    SD_COLOR_WHEEL(0x0201),
    SD_COLOR_SUB_CYAN(0x0202),
    SD_COLOR_SUB_YELLOW(0x0203),
    SD_COLOR_SUB_MAGENTA(0x0204),
    SD_COLOR_ADD_RED(0x0205),
    SD_COLOR_ADD_GREEN(0x0206),
    SD_COLOR_ADD_BLUE(0x0207),
    SD_COLOR_CORRECTION(0x0208),
    SD_COLOR_SCROLL(0x0209),
    SD_COLOR_SEMAPHORE(0x0210),
    SD_STATIC_GOBO_WHEEL(0x0301),
    SD_ROTO_GOBO_WHEEL(0x0302),
    SD_PRISM_WHEEL(0x0303),
    SD_EFFECTS_WHEEL(0x0304),
    SD_BEAM_SIZE_IRIS(0x0401),
    SD_EDGE(0x0402),
    SD_FROST(0x0403),
    SD_STROBE(0x0404),
    SD_ZOOM(0x0405),
    SD_FRAMING_SHUTTER(0x0406),
    SD_SHUTTER_ROTATE(0x0407),
    SD_DOUSER(0x0408),
    SD_BARN_DOOR(0x0409),
    SD_LAMP_CONTROL(0x0501),
    SD_FIXTURE_CONTROL(0x0502),
    SD_FIXTURE_SPEED(0x0503),
    SD_MACRO(0x0504),
    SD_UNDEFINED(0xFFFF);

    private final int id;
    RDMSlotID(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RDMSlotID byId(int id){
        for (RDMSlotID value : values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }
}
