import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Zombie extends CanMove {

    public static final String ZOMBIE_KEY = "zombie";
    public static final int ZOMBIE_ANIMATION_PERIOD = 0;
    public static final int ZOMBIE_ACTION_PERIOD = 1;
    public static final int ZOMBIE_NUM_PROPERTIES = 2;

    public static final int ZOMBIE_ANIMATION = 1;
    public static final double ZOMBIE_ACTION_MAX = 2.000;
    public static final double ZOMBIE_ACTION_MIN = 1.000;

    public static final PathingStrategy ZOMBIE_PATHING_STRATEGY = new AStarPathingStrategy();
    //    public static final PathingStrategy NEW_ENTITY_PATHING_STRATEGY = new SingleStepPathingStrategy();


    public Zombie(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, PathingStrategy strategy) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, strategy);
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
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> zombieTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Dude.DUDE_KEY)));

        if (zombieTarget.isPresent()) {
            Point tgtPos = zombieTarget.get().getPosition();

            if (moveTo(world, zombieTarget.get(), scheduler)) {

                Skull skull = new Skull(tgtPos, imageStore.getImageList(Skull.SKULL_KEY), 0,Skull.SKULL_KEY + "_" + zombieTarget.get().getId(), Skull.SKULL_KEY);
                skull.setDudeData((Dude)zombieTarget.get());
                world.addEntity(skull);

                fairyTransformToGreen(world, imageStore, scheduler);
            }
        }
        Activity activity = new Activity(this, world, imageStore);
        scheduler.scheduleEvent(this, activity, this.getActionPeriod());
    }

    public void fairyTransformToGreen(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> nearestFairy = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Fairy.FAIRY_KEY)));
        if (nearestFairy.isPresent()) {
            Point fairyPos = nearestFairy.get().getPosition();

            FairyGreen fairyGreen = new FairyGreen(fairyPos, imageStore.getImageList(FairyGreen.FAIRY_GREEN_KEY),
                    0,FairyGreen.FAIRY_GREEN_KEY + "_" + nearestFairy.get().getId(), FairyGreen.FAIRY_GREEN_KEY,
                    0.123, 0.5, FairyGreen.FAIRY_GREEN_STRATEGY);

            fairyGreen.setFairyData((Fairy)nearestFairy.get());

            world.removeEntity(scheduler, nearestFairy.get());
            world.addEntity(fairyGreen);

            fairyGreen.scheduleAction(scheduler, world, imageStore);
        }
    }
}
