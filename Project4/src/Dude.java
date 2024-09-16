import processing.core.PImage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Dude extends CanMove implements  Transform {
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD = 0;
    public static final int DUDE_ANIMATION_PERIOD = 1;
    public static final int DUDE_LIMIT = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;

    public static final PathingStrategy DUDE_STRATEGY = new AStarPathingStrategy();
//    public static final PathingStrategy DUDE_STRATEGY = new SingleStepPathingStrategy();

    private final int resourceLimit;

    public Dude(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, int resourceLimit, PathingStrategy strategy) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, strategy);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit() {
        return this.resourceLimit;
    }


    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        List<Point> points;
        points = getStrategy().computePath(this.getPosition(), destPos,
                p ->  world.withinBounds(p) && (world.getOccupant(p).equals(Optional.empty()) || world.getOccupancyCell(p).getKind().equals(Stump.STUMP_KEY)),
                (p1, p2) -> neighbors(p1,p2),
                PathingStrategy.CARDINAL_NEIGHBORS);

        return points.getFirst();
    }
}
