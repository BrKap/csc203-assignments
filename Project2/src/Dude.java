import processing.core.PImage;

import java.util.List;

public abstract class Dude extends HasAction implements CanMove, Transform {
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD = 0;
    public static final int DUDE_ANIMATION_PERIOD = 1;
    public static final int DUDE_LIMIT = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;

    private int resourceLimit;

    public Dude(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, int resourceLimit) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }



    public int getResourceLimit() {
        return this.resourceLimit;
    }



    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos) && !world.getOccupancyCell(newPos).getKind().equals(Stump.STUMP_KEY)) {
            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos) && !world.getOccupancyCell(newPos).getKind().equals(Stump.STUMP_KEY)) {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }
}
