import processing.core.PImage;

import java.util.List;

public class Obstacle extends HasAnimation {
    public static final String OBSTACLE_KEY = "obstacle";
    public static final int OBSTACLE_ANIMATION_PERIOD = 0;
    public static final int OBSTACLE_NUM_PROPERTIES = 1;

    public Obstacle(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod) {
        super(position, images, imageIndex, id, kind, animationPeriod);
    }

}
