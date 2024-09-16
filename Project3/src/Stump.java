import processing.core.PImage;

import java.util.List;

public class Stump extends Entity {
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;


    public Stump(Point position, List<PImage> images, int imageIndex, String id, String kind) {
        super(position, images, imageIndex, id, kind);
    }
}
