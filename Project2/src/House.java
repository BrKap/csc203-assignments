import processing.core.PImage;

import java.util.List;

public class House extends Entity {
    public static final String HOUSE_KEY = "house";
    public static final int HOUSE_NUM_PROPERTIES = 0;


    public House(Point position, List<PImage> images, int imageIndex, String id, String kind) {
        super(position, images, imageIndex, id, kind);
    }
}
