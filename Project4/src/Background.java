import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import processing.core.PImage;

/**
 * Represents a background for the 2D world.
 */
public final class Background {


    static final Function<Point, Stream<Point>> RandomSet1 =
            point ->
                    Stream.<Point>builder()
                            .add(new Point(point.getX(), point.getY() - 1))
                            .add(new Point(point.getX() + 1, point.getY() - 1))
                            .add(new Point(point.getX(), point.getY() + 1))
                            .add(new Point(point.getX() - 2, point.getY() + 2))
                            .add(new Point(point.getX() - 1, point.getY() + 1))
                            .add(new Point(point.getX() - 2, point.getY()))
                            .add(new Point(point.getX() - 1, point.getY()))
                            .add(new Point(point.getX(), point.getY()))
                            .build();
    private String id;
    private List<PImage> images;
    private int imageIndex;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }

    public PImage getCurrentImage() {
            return this.images.get(this.imageIndex);
      }

}
