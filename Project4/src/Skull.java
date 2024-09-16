import processing.core.PImage;

import java.util.List;

public class Skull extends Entity {
    public static final String SKULL_KEY = "skull";
    public static final int SKULL_NUM_PROPERTIES = 0;

    private Dude dudeData;
    public Skull(Point position, List<PImage> images, int imageIndex, String id, String kind) {
        super(position, images, imageIndex, id, kind);
    }

    public Dude getDudeData() {
        return this.dudeData;
    }
    public void setDudeData(Dude dudeData) {
        this.dudeData = dudeData;
    }
}
