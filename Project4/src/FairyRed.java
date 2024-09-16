import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FairyRed extends CanMove implements Transform {
    public static final String FAIRY_RED_KEY = "fairyred";
    public static final int FAIRY_ANIMATION_PERIOD = 0;
    public static final int FAIRY_ACTION_PERIOD = 1;
    public static final int FAIRY_NUM_PROPERTIES = 2;
    private Fairy fairyData;

    public static final PathingStrategy FAIRY_RED_STRATEGY = new AStarPathingStrategy();
//    public static final PathingStrategy FAIRY_RED_STRATEGY = new SingleStepPathingStrategy();

    public FairyRed(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, PathingStrategy strategy) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, strategy);
    }

    public void setFairyData(Fairy fairyData) {
        this.fairyData = fairyData;
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyRedTarget = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Zombie.ZOMBIE_KEY)));
        if (fairyRedTarget.isPresent()) {
            Point tgtPos = fairyRedTarget.get().getPosition();

            if (moveTo(world, fairyRedTarget.get(), scheduler)) {

                world.removeEntity(scheduler, fairyRedTarget.get());
                this.transform(world, scheduler, imageStore);
            }
        }
        Activity activity = new Activity(this, world, imageStore);
        scheduler.scheduleEvent(this, activity, this.getActionPeriod());
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        List<Point> points;
        points = getStrategy().computePath(this.getPosition(), destPos,
                p ->  world.withinBounds(p) && (world.getOccupant(p).equals(Optional.empty())),
                (p1, p2) -> neighbors(p1,p2),
                PathingStrategy.CARDINAL_NEIGHBORS);

        return points.getFirst();
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


    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Fairy fairy = fairyData;
        fairy.setPosition(this.getPosition());
        fairy.addId(Fairy.FAIRY_KEY);
        fairy.resetSaveDudeCounter();

        world.removeEntity(scheduler, this);
        world.addEntity(fairy);
        fairy.scheduleAction(scheduler, world, imageStore);




        return true;
    }
}
