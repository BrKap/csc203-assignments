import processing.core.PImage;

import java.util.*;

public class Fairy extends HasAction implements CanMove {
    public static final String FAIRY_KEY = "fairy";
    public static final int FAIRY_ANIMATION_PERIOD = 0;
    public static final int FAIRY_ACTION_PERIOD = 1;
    public static final int FAIRY_NUM_PROPERTIES = 2;

    public Fairy(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod);
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Stump.STUMP_KEY)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (moveTo(world, fairyTarget.get(), scheduler)) {

                Sapling sapling = new Sapling(tgtPos, imageStore.getImageList(Sapling.SAPLING_KEY), 0,Sapling.SAPLING_KEY + "_" + fairyTarget.get().getId(), Sapling.SAPLING_KEY,
                        Sapling.SAPLING_ACTION_ANIMATION_PERIOD, Sapling.SAPLING_ACTION_ANIMATION_PERIOD, Sapling.SAPLING_HEALTH);

                world.addEntity(sapling);
                sapling.scheduleAction(scheduler, world, imageStore);
            }
        }
        Activity activity = new Activity(this, world, imageStore);
        scheduler.scheduleEvent(this, activity, this.getActionPeriod());
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.getPosition();
            }
        }
        return newPos;
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity( scheduler,target);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }


}
